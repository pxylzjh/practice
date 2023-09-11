# mp以及springBoot使用注意事项

* 使用了mybatis-plus-boot-starter则不用配置sqlSession了。
因为在mp-boot-starter的`spring.factories`文件中已经自动装配了
```yml
# Auto Configure
org.springframework.boot.env.EnvironmentPostProcessor=\
  com.baomidou.mybatisplus.autoconfigure.SafetyEncryptProcessor
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  com.baomidou.mybatisplus.autoconfigure.IdentifierGeneratorAutoConfiguration,\
  com.baomidou.mybatisplus.autoconfigure.MybatisPlusLanguageDriverAutoConfiguration,\
  com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration
```
详情查看MybatisPlusAutoConfiguration类
* spring操作数据库所需的三要素：DataSource、TransactionManager、orm框架
* 在SpringBoot项目中，无需手动注入TransactionManager，也无需使用`@EnableTransactionManagement`，因为springboot自动装配已经进行了配置
spring自动装配：`@SpringBootApplication->@EnableAutoConfiguration->@Import(AutoConfigurationImportSelector.class)->扫描所有的spring.factories文件`，最终注入`TransactionAutoConfiguration`，里面配置了`TransactionManager`

# Spring事务的实现原理
代理类的生成

这里只考虑基于注解的声明式事务的场景，大致原理都差不多。

Spring需要扫描所有使用了@Transactional注解的类，然后通过AOP对这些bean进行增强。AOP是在Bean的初始化之后创建的。Spring开启注解式事务必不可少的条件是`@EnableTransactionManagement`注解。
```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({TransactionManagementConfigurationSelector.class})
public @interface EnableTransactionManagement {
    // 使用CGLIB 或者 JDK 创建代理对象，只有在Advice.mode=PROXY时才有用
    boolean proxyTargetClass() default false;
    // AOP的实现方式 PROXY表示使用JDK proxy或者CGLIB，ASPECTJ表示使用AspectJ
    AdviceMode mode() default AdviceMode.PROXY;

    int order() default Integer.MAX_VALUE;
}
```
重要的是，它通过`@Import`注解注入了`TransactionManagementConfigurationSelector`类，其实Spring很多功能都采用了类似的方式进行开启，例如通过注解开启AOP、@MapperScan对mapper接口创建代理等。。
```java
public class TransactionManagementConfigurationSelector extends AdviceModeImportSelector<EnableTransactionManagement> {

	// 通过 AdviceMode 判断需要 注入哪些类用于
	@Override
	protected String[] selectImports(AdviceMode adviceMode) {
		switch (adviceMode) {
			case PROXY:
				return new String[] {AutoProxyRegistrar.class.getName(),
						ProxyTransactionManagementConfiguration.class.getName()};
			case ASPECTJ:
				return new String[] {determineTransactionAspectClass()};
			default:
				return null;
		}
	}

	private String determineTransactionAspectClass() {
		return (ClassUtils.isPresent("javax.transaction.Transactional", getClass().getClassLoader()) ?
				TransactionManagementConfigUtils.JTA_TRANSACTION_ASPECT_CONFIGURATION_CLASS_NAME :
				TransactionManagementConfigUtils.TRANSACTION_ASPECT_CONFIGURATION_CLASS_NAME);
	}

}
```