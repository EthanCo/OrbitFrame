package com.lib.utils.crash;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.TreeSet;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,有该类 来接管程序,并记录 发送错误报告.. 註冊方式
 * CrashHandler crashHandler = CrashHandler.getInstance(); //注册crashHandler
 * crashHandler.init(getApplicationContext()); //发送以前没发送的报告(可选)
 * crashHandler.sendPreviousReportsToServer();
 * <p>
 * 记录的异常在储存卡下的Android/包名/crash下
 */

/**
 * Description   程序异常崩溃记录类
 * Created by chenqiao on 2015/9/6.
 */
public class CrashHandler implements UncaughtExceptionHandler {
    /**
     * Debug Log tag
     */
    public static final String TAG = "CrashHandler";
    /**
     * 是否开启日志输出,在Debug状态下开启, 在Release状态下关闭以提示程序性能
     */
    public static final boolean DEBUG = true;
    private static final long MAX_CACHE_SIZE = 1014 * 1024 * 10; //以B为单位
    /**
     * 系统默认的UncaughtException处理类
     */
    private UncaughtExceptionHandler mDefaultHandler;
    /**
     * CrashHandler实例
     */
    private static CrashHandler INSTANCE;
    /**
     * 程序的Context对象
     */
    private Context mContext;

    /**
     * 使用Properties来保存设备的信息和错误堆栈信息
     */
    private Properties mDeviceCrashInfo = new Properties();
    private static final String VERSION_NAME = "versionName";
    private static final String VERSION_CODE = "versionCode";
    private static final String STACK_TRACE = "STACK_TRACE";
    /**
     * 错误报告文件的扩展名
     */
    private static final String CRASH_REPORTER_EXTENSION = ".cr";
    private CrashSharePreferences preferences;
    private CrashListener crashListener;

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CrashHandler();
        }
        return INSTANCE;
    }

    /**
     * 初始化,注册Context对象，崩溃重启后没有监听
     *
     * @param isDebug false:设置该CrashHandler为程序的默认的UncaughtException处理器
     *                true:使用系统默认的UncaughtException处理器
     * @param ctx     context
     */
    public void init(Boolean isDebug, Context ctx) {
        init(isDebug, ctx, null);
    }

    public void initRestart(Boolean isDebug, final Context ctx, final int restartInterval, final Intent explicitIntent) {
        init(isDebug, ctx, new CrashListener() {
            @Override
            public void onCrashRestarted() {
                Log.i("Z-CrashListener", "onCrashRestarted");
            }

            @Override
            public void onCrash(Thread thread, Throwable ex) {
                Log.e("Z-CrashListener", "onCrash:" + ex);
                //Intent explicitIntent = new Intent(ctx, HomePanel.class);
                explicitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                PendingIntent restartIntent = PendingIntent.getService(
                        ctx, 0, explicitIntent, PendingIntent.FLAG_ONE_SHOT);
                AlarmManager mgr = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
                //重启应用
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + restartInterval, restartIntent);
            }
        });
    }

    /**
     * 初始化,注册Context对象
     *
     * @param isDebug       false:设置该CrashHandler为程序的默认的UncaughtException处理器
     *                      true:使用系统默认的UncaughtException处理器，方便调试的时候看在IDE里看日志
     * @param ctx           context
     * @param crashListener 崩溃重启后的监听
     */
    public void init(Boolean isDebug, Context ctx, final CrashListener crashListener) {
        mContext = ctx;
        preferences = new CrashSharePreferences(ctx);
        this.crashListener = crashListener;
        Log.i(TAG, "init crashListener:" + crashListener);
        if (crashListener != null) {
            Log.i(TAG, "init isCrash:" + preferences.isCrash());
            if (preferences.isCrash()) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        SystemClock.sleep(2000);
                        crashListener.onCrashRestarted();
                    }
                }.start();
                preferences.setIsCrash(false);
            }
        }
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e(TAG, "on Crash:" + ex.getMessage());
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            if (preferences != null) {
                preferences.setIsCrash(true);
            }
            if (crashListener != null) {
                crashListener.onCrash(thread, ex);
            }

            // Sleep一会后结束程序
            //SystemClock.sleep(3000);
            Log.i(TAG, "exit");
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return true;
        }
        final String msg = ex.getLocalizedMessage();
        // 使用Toast来显示异常信息
       /* new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "程序出错:" + msg, Toast.LENGTH_LONG).show();
                Looper.loop();
            }

        }.start();*/

        //检查大小，若大于最大缓存，则删除
        checkSize();
        // 收集设备信息
        collectCrashDeviceInfo(mContext);
        // 保存错误报告文件
        String crashInfo = saveCrashInfoToFile(ex);
        Log.e(TAG, crashInfo);
        // 发送错误报告到服务器
        //sendPreviousReportsToServer();
        return true;
    }

    private void checkSize() {
        File dir = new File(getCrashDir(mContext));
        long dirSize = getDirSize(dir);
        if (dirSize > MAX_CACHE_SIZE) {
            deleteAll(dir);
        }
    }

    /**
     * 在程序启动时候, 可以调用该函数来发送以前没有发送的报告
     */
    public void sendPreviousReportsToServer() {
        sendCrashReportsToServer(mContext);
    }

    /**
     * 把错误报告发送给服务器,包含新产生的和以前没发送的.
     *
     * @param ctx
     */
    private void sendCrashReportsToServer(Context ctx) {
        String[] crFiles = getCrashReportFiles(ctx);
        if (crFiles != null && crFiles.length > 0) {
            TreeSet<String> sortedFiles = new TreeSet<>();
            sortedFiles.addAll(Arrays.asList(crFiles));

            for (String fileName : sortedFiles) {
                File cr = new File(getCrashDir(ctx), fileName);
                postReport(cr);
                cr.delete();// 删除已发送的报告
            }
        }
    }

    @NonNull
    private String getCrashDir(Context ctx) {
        return getFileDir(ctx, "Crash");
    }

    private String getFileDir(Context context, String folder) {
        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = context.getExternalFilesDir(folder).getPath();
        } else {
            path = context.getFilesDir().getPath() + File.separator + folder;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return path;
    }

    private void postReport(File file) {
        // 使用HTTP Post 发送错误报告到服务器
    }

    /**
     * 获取错误报告文件名
     *
     * @param ctx
     * @return
     */
    private String[] getCrashReportFiles(Context ctx) {
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(CRASH_REPORTER_EXTENSION);
            }
        };

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String filePath = getCrashDir(mContext);
            File dir = new File(filePath);
            return dir.list(filter);
        } else {
            File filesDir = ctx.getFilesDir();
            return filesDir.list(filter);
        }
    }

    public static long getDirSize(File file) {
        //判断文件是否存在
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                long size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {//如果是文件则直接返回其大小,以KB为单位
                long size = file.length();
                return size;
            }
        } else {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0;
        }
    }

    public static void deleteAll(File file) {
        if (file.isFile() || file.list().length == 0) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteAll(files[i]);
                files[i].delete();
            }

            //如果文件本身就是目录 ，就要删除目录
            if (file.exists())
                file.delete();
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return
     */
    private String saveCrashInfoToFile(Throwable ex) {
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        ex.printStackTrace(printWriter);

        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        String result = info.toString();
        printWriter.close();
        mDeviceCrashInfo.put(STACK_TRACE, result);
        String fileName = "";
        try {
            long timestamp = System.currentTimeMillis();
            FileOutputStream trace;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmmss", Locale.getDefault());
            String time = format.format(new Date(timestamp));
            fileName = "crash-" + time + CRASH_REPORTER_EXTENSION;
            String filePath = getCrashDir(mContext);
            File file = new File(filePath, fileName);
            trace = new FileOutputStream(file);
            mDeviceCrashInfo.storeToXML(trace, "crashLog");
            trace.flush();
            trace.close();
            return result;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing report file..."
                    + fileName, e);
        }
        return result;
    }

    /**
     * 收集程序崩溃的设备信息
     *
     * @param ctx
     */
    public void collectCrashDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                mDeviceCrashInfo.put(VERSION_NAME,
                        pi.versionName == null ? "not set" : pi.versionName);
                mDeviceCrashInfo.put(VERSION_CODE, String.valueOf(pi.versionCode));
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "Error while collect package info", e);
        }
        // 使用反射来收集设备信息.在Build类中包含各种设备信息,
        // 例如: 系统版本号,设备生产商 等帮助调试程序的有用信息
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(), field.get(null).toString());
                if (DEBUG) {
                    Log.d(TAG, field.getName() + " : " + field.get(null).toString());
                }
            } catch (Exception e) {
                Log.e(TAG, "Error while collect crash info", e);
            }
        }
    }

    public interface CrashListener {
        void onCrashRestarted(); //当出现Crash并重启的时候

        void onCrash(Thread thread, Throwable ex);
    }

    private static class CrashSharePreferences {
        public static final String TAG = "Z-E-CrashShare";
        private static final String KEY_IS_CRASH = "KEY_IS_CRASH";

        private Context context;

        public CrashSharePreferences(Context context) {
            this.context = context;
        }

        private SharedPreferences getPreference() {
            return context.getSharedPreferences(getSpKEY(), Context.MODE_PRIVATE);
        }

        private String getSpKEY() {
            String spKey = "SP_CRASH_HANDLER_" + getCurProcessName(context);
            Log.i(TAG, "CrashListener spKey:" + spKey);
            return spKey;
        }

        public boolean isCrash() {
            SharedPreferences sp = getPreference();
            boolean isCrash = sp.getBoolean(KEY_IS_CRASH, false);
            Log.i(TAG, "isCrash" + isCrash);
            return isCrash;
        }

        public void setIsCrash(boolean isCrash) {
            SharedPreferences sp = getPreference();
            SharedPreferences.Editor edit = sp.edit();
            edit.putBoolean(KEY_IS_CRASH, isCrash);
            edit.commit();
            Log.i(TAG, "setIsCrash" + isCrash);
        }

        //获取进程名
        private String getCurProcessName(Context context) {
            int pid = android.os.Process.myPid();
            ActivityManager mActivityManager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                    .getRunningAppProcesses()) {
                if (appProcess.pid == pid) {

                    return appProcess.processName;
                }
            }
            return null;
        }
    }
}