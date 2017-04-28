package com.github.druid.autocofigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

import java.util.Properties;

/**
 * 
 * <p>Title: DruidProperties</p>
 * <p>Description: druid数据源属性类</p>
 * @author Harry Chang
 * @Email cxiaolng@qq.com
 * @Company CassTime Technologies Co.Ltd.
 * @date 2017-04-28 22:05:11
 */
@ConfigurationProperties("spring.datasource.druid")
@Data
public class DruidProperties {
	
	/**
	 * 统计访问路径;
	 * 默认值：/druid/*
	 */
	private String path = "/druid/*";
	
	/**
	 * 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
	 * 默认值：true
	 */
	private Boolean testWhileIdle = true;
	
	/**
	 * 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
	 * 默认值：true
	 */
	private Boolean testOnBorrow = true;
	
	/**
	 * 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会其作用
	 * 默认值: SELECT 1
	 */
	private String validationQuery = "SELECT 1";
	/**
	 * 合并多个DruidDataSource的监控数据
	 */
	private Boolean useGlobalDataSourceStat;
	
	/**
	 * 属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：监控统计用的filter:stat/日志用的filter:log4j/防御sql注入的filter:wall
	 */
	private String filters;
	private Long timeBetweenLogStatsMillis;
	private Integer maxSize;
	private Boolean clearFiltersEnable;
	private Boolean resetStatEnable;
	private Integer notFullTimeoutRetryCount;
	private Integer maxWaitThreadCount;
	private Boolean failFast;
	private Boolean phyTimeoutMillis;
	/**
	 * 连接保持空闲而不被驱逐的最长时间
	 */
	private Long minEvictableIdleTimeMillis = 300000L;
	private Long maxEvictableIdleTimeMillis;
	private Boolean keepAlive;
	
	/**
	 * 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭。
	 * 默认值：false
	 */
	private Boolean poolPreparedStatements = false;
	private Boolean initVariants;
	private Boolean initGlobalVariants;
	private Boolean useUnfairLock;
	
	/**
	 * 初始化时建立物理连接的个数。初始化发生在显示调用init方法，或者第一次getConnection时
	 * 默认值：0
	 */
	private Integer initialSize = 0;
	/**
	 * 最小连接池数量
	 * 默认值：5
	 */
	private Integer minIdle = 5;
	
	/**
	 * 最大连接池数量
	 * 默认值：8
	 */
	private Integer maxActive = 8;
	
	/**
	 * 获取连接时最大等待时间，单位毫秒。配置了maxWait之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置useUnfairLock属性为true使用非公平锁
	 */
	private Long maxWait = 60000L;
	/**
	 * 有两个含义：1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
	 * 默认：1分钟
	 */
	private Long timeBetweenEvictionRunsMillis = 60000L;
	
	/**
	 * 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些，比如说100
	 */
	private Integer maxPoolPreparedStatementPerConnectionSize = 100;
	
	/**
	 * 经常需要排除一些不必要的url，比如*.js,/jslib/*等等
	 * 默认：*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*
	 */
	private String webStatFilterExclusions = "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*";
	
	/**
	 * web控制台登陆账号
	 */
	private String webLoginUsername;
	
	/**
	 * web控制台登陆密钥
	 */
	private String webLoginPassword;
	
	/**
	 * web控制台访问IP白名单
	 */
	private String webAllow;
	
	/**
	 * web控制台访问IP黑名单
	 */
	private String webDeny;
	
	private Properties connectionProperties = new Properties() {
		private static final long serialVersionUID = 1L;

		{
			put("druid.stat.mergeSql", "true");
			put("druid.stat.slowSqlMillis", "5000");
		}
	};

	
	public Properties toProperties() {
		Properties properties = new Properties();
		notNullAdd(properties, "testWhileIdle", this.testWhileIdle);
		notNullAdd(properties, "testOnBorrow", this.testOnBorrow);
		notNullAdd(properties, "validationQuery", this.validationQuery);
		notNullAdd(properties, "useGlobalDataSourceStat", this.useGlobalDataSourceStat);
		notNullAdd(properties, "filters", this.filters);
		notNullAdd(properties, "timeBetweenLogStatsMillis", this.timeBetweenLogStatsMillis);
		notNullAdd(properties, "stat.sql.MaxSize", this.maxSize);
		notNullAdd(properties, "clearFiltersEnable", this.clearFiltersEnable);
		notNullAdd(properties, "resetStatEnable", this.resetStatEnable);
		notNullAdd(properties, "notFullTimeoutRetryCount", this.notFullTimeoutRetryCount);
		notNullAdd(properties, "maxWaitThreadCount", this.maxWaitThreadCount);
		notNullAdd(properties, "failFast", this.failFast);
		notNullAdd(properties, "phyTimeoutMillis", this.phyTimeoutMillis);
		notNullAdd(properties, "minEvictableIdleTimeMillis", this.minEvictableIdleTimeMillis);
		notNullAdd(properties, "maxEvictableIdleTimeMillis", this.maxEvictableIdleTimeMillis);
		notNullAdd(properties, "initialSize", this.initialSize);
		notNullAdd(properties, "minIdle", this.minIdle);
		notNullAdd(properties, "maxActive", this.maxActive);
		notNullAdd(properties, "maxWait", this.maxWait);
		notNullAdd(properties, "timeBetweenEvictionRunsMillis", this.timeBetweenEvictionRunsMillis);
		notNullAdd(properties, "poolPreparedStatements", this.poolPreparedStatements);
		notNullAdd(properties, "maxPoolPreparedStatementPerConnectionSize",
				this.maxPoolPreparedStatementPerConnectionSize);
		return properties;
	}

	private void notNullAdd(Properties properties, String key, Object value) {
		if (value != null) {
			properties.setProperty("druid." + key, value.toString());
		}
	}
}
