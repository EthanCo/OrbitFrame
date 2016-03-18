# SimpleFrame #
## 宗旨 ##
- 简单
- 最少的学习成本
	- 尽可能保持原环境
- 采用Material Design
- 默认添加的类库，也可根据需求进行删减
	- 网络加载: Retrofit
	- 图片加载:Fresco
	- 数据解析:Gson
	- 响应式框架:RxAndroid
	- Lambda支持:retrolambda  
	- <s>注解框架:ButterKnife</s> (改为DataBinding)
	- 对话框:material-dialogs (基于DialogFragment)
	- Cardview、RecyclerView、Palette

## MVVM架构 ##
采用MVVM+RxJava+RxAndroid+Retrofit+Lambda表达式，Java需要使用Java8
	
### 添加SimpelFrame后，请在项目的build.gradle中添加  

	allprojects {
    	repositories {
    	    ...
    	    maven { url "https://jitpack.io" }
    	}
	}

还有

	buildscript {
		//开始加入
		 dependencies {
		 classpath 'me.tatarka:gradle-retrolambda:3.2.4'
		 }
		 //结束插入
	}
##  ##