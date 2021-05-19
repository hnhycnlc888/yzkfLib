package yzkf.config;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.tomcat.jdbc.pool.PoolProperties;

import yzkf.exception.ParserConfigException;
import yzkf.utils.Utility;

/**
 * 数据库配置类
 * @author qiulw
 * @version V4.0.0 2011.12.01
 */
public class DBConfig extends Configuration {
	
	/**
	 * 配置中默认使用的数据库类型
	 */
	public final static String DEFAULT_TYPE="default";

	private ConcurrentHashMap<String, PoolProperties> properties = new ConcurrentHashMap<String, PoolProperties>();
	/**
	 * 创建数据库配置类对象
	 * @param path 配置文件路径
	 * @throws ParserConfigException
	 */
	DBConfig(String path) throws ParserConfigException{
		super(path);
	}
	/**
	 * 获取默认数据库配置
	 * @return 连接池属性集合
	 * @throws ParserConfigException 读取配置文件异常
	 */
	public PoolProperties getPoolProperties() throws ParserConfigException{
		return getPoolProperties(DEFAULT_TYPE);
	}
	/**
	 * 获取指定type数据库配置属性
	 * @param dbtype 数据库类型，对应配置文件type属性
	 * @return 连接池属性集合
	 * @throws ParserConfigException 读取配置文件异常
	 */
	public PoolProperties getPoolProperties(String dbtype) throws ParserConfigException{
		if (!properties.containsKey(dbtype)){			
			PoolProperties p = new PoolProperties();
			
			String node = "/databases/db[@type=\""+dbtype+"\"]/";					
			p.setDriverClassName(getXPathValue(node+"driverClassName"));
			p.setUrl(getXPathValue(node+"url"));
			p.setUsername(getXPathValue(node+"username"));		
			p.setPassword(getXPathValue(node+"password"));		
			p.setMaxActive(Integer.parseInt(getXPathValue(node+"maxActive")));
			p.setMaxIdle(Integer.parseInt(getXPathValue(node+"maxIdle")));
			p.setMinIdle(Integer.parseInt(getXPathValue(node+"minIdle")));
			p.setInitialSize(Integer.parseInt(getXPathValue(node+"initialSize")));
			p.setMaxWait(Integer.parseInt(getXPathValue(node+"maxWait")));
			p.setTestWhileIdle(Boolean.parseBoolean(getXPathValue(node+"testWhileIdle")));
			p.setTestOnBorrow(Boolean.parseBoolean(getXPathValue(node+"testOnBorrow")));
			p.setTestOnReturn(Boolean.parseBoolean(getXPathValue(node+"testOnReturn")));
			if(!Utility.isEmptyOrNull(getXPathValue(node+"validationQuery")))
				p.setValidationQuery(getXPathValue(node+"validationQuery"));
			if(!Utility.isEmptyOrNull(getXPathValue(node+"validatorClassName")))
				p.setValidatorClassName(getXPathValue(node+"validatorClassName"));
			p.setTimeBetweenEvictionRunsMillis(Integer.parseInt(getXPathValue(node+"timeBetweenEvictionRunsMillis")));
			p.setMinEvictableIdleTimeMillis(Integer.parseInt(getXPathValue(node+"minEvictableIdleTimeMillis")));
			p.setRemoveAbandoned(Boolean.parseBoolean(getXPathValue(node+"removeAbandoned")));
			p.setRemoveAbandonedTimeout(Integer.parseInt(getXPathValue(node+"removeAbandonedTimeout")));
			p.setLogAbandoned(Boolean.parseBoolean(getXPathValue(node+"logAbandoned")));
			if(!Utility.isEmptyOrNull(getXPathValue(node+"connectionProperties")))
				p.setConnectionProperties(getXPathValue(node+"connectionProperties"));
			
			properties.putIfAbsent(dbtype, p);
		}		
		return properties.get(dbtype);
	}
}
