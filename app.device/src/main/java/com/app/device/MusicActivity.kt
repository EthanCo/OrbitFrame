package com.app.device

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.alibaba.android.arouter.facade.annotation.Route
import com.lib.frame.view.simple.BaseSimpleActivity
import com.lib.kotlinex.extension.toast
import kotlinx.android.synthetic.main.device_activity_music.*

@Route(path = "/device/music")
class MusicActivity : BaseSimpleActivity() {
    override fun initVarAndView(savedInstanceState: Bundle?) {
        setContentView(R.layout.device_activity_music)
        initToolbar(null, true)
    }

    override fun initDoing() {
        super.initDoing()
        radiobtn_local_music.isChecked = true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.device_menu_music, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val result = super.onOptionsItemSelected(item)
        if (result) return true

        val id = item?.itemId

        when (id) {
            R.id.menu_device_details -> {
                toast("设备详情")
                return true
            }
        }
        return false
    }
}
