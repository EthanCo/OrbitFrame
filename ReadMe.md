## 相关类库
网络加载: Retrofit2  
图片加载:Glide  
数据解析:Gson  
响应式框架:RxJava2  
数据绑定框架:DataBinding  
路由跳转:ARouter  
内存泄漏解析:LeakCanary  
Recyclerview框架: BaseRecyclerViewHelper  
数据库:暂无需使用，待定  

## 路由架构  
- 模块间进一步解耦，所有的基础公共库按需进行依赖，各个项目可共用
- 开发阶段仅对单个模块进行运行和调试，提高开发效率
- 减少协同开发中的冲突，提高开发效率
- 可自由选择开发姿势（MVC/MVP/MVVM等)
- 可进行针对性的的测试(仅需对修改过的模块进行测试)
- 实现各模块的配置和组装，当一个功能不需要的时候，随时可去掉，当又需要该功能的时候，随时又可加上。
	

### 项目组件  
项目的功能可拆分为如下各个组件，各个业务模块之间可以独立运行，亦可组合在一起打Release包，如下图所示。

![这里写图片描述](http://oqk78xit2.bkt.clouddn.com/20170714151835881.png)  

### 新建组件步骤
详见[创建组件](/doc/创建组件.md)

### 现存在的问题
- 新建组件较为繁琐 			//后期考虑是否能够采用更加自动化、便捷的方式新建组件  
- 各组件的资源约束名称必须不同  //后期考虑能否有更优雅的方式解决资源冲突  

