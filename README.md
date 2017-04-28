# druid-spring-boot
将druid数据源整合到spring boot自动配置中，实现零配置。

使用非常简单

进入工程根目录druid-spring-boot/

mvn clean install

在项目pom内引入


	<dependency>
		<groupId>com.github.druid</groupId>
		<artifactId>druid-spring-boot-starter</artifactId>
    		<version>1.0.0-SNAPSHOT</version>
	</dependency>
  
  配置工程数据源类型
  
  spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
  
  好了就是这么简单
