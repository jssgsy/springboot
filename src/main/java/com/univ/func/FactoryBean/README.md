
# 基本事实
注入FactoryBean对象给spring容器时，实际上会注入两个bean给容器：
* FactoryBean本身；
* FactoryBean#getObject产生的bean;
且通过FactoryBean的名字到容器中找到的是getObject产生的bean，要获取FactoryBean自身实例，需要在名字前加&，当然一般也用不到
  
# 何时使用FactoryBean
用在创建复杂对象中，背后的逻辑是：此对象并不是简单的new一样这样简单，可能有很多属性有很多内部逻辑，此时通过编码的方式要创建要更简单些；
参考mybatis集成spring时提供的SqlSessionFactoryBean；