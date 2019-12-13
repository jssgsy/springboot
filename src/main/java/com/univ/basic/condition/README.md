# spring boot 提供的各种常用条件注解
* @ConditionalOnBean：当指定的bean已经存在于BeanFactory中时匹配(Conditional that only matches when beans of the specified classes and/or with the specified names are already contained in the BeanFactory.)
* @ConditionalOnClass：当指定的bean已经在classpath中时匹配(Conditional that only matches when the specified classes are on the classpath.)
* @ConditionalOnMissingBean
* @ConditionalOnMissingClass