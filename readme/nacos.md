[toc]

# 关于同步获取配置
* 前提条件：NacosPropertySource指定autoRefreshed为true;
```java
@NacosPropertySource(dataId = "com.univ", autoRefreshed = true)
```
* 同时每个配置项的@NacosValue都要指定autoRefreshed为true;
    * 说明每个配置项都要手动开启同步机制(有点奇怪，竟然没有全局配置，需要深入源码验证下)；
```java
// 此时同步不生效
@NacosValue(value = "${city:god}")
private String city;

// 此时同步才生效
@NacosValue(value = "${name:default_name}", autoRefreshed = true)
private String name;
```
* 经验证，@NacosValue不指定autoRefreshed为true情况下，即使指定如下属性，也做不到同步：
  * 说明对其含义理解不够深入；
```properties
nacos.config.enableRemoteSyncConfig=true
nacos.config.auto-refresh=true
```