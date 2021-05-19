package yzkf.db;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import yzkf.config.ConfigFactory;
import yzkf.config.DBConfig;
import yzkf.exception.ParserConfigException;

/**
 * 数据库操作类
 * @author qiulw
 * @version V4.0.0 2012.02.09
 */
public class DBAccess {
	
	private static ConcurrentMap<String, DataSource> dataSourceMap = new ConcurrentHashMap<String, DataSource>();
	private QueryRunner runner;
	private DataSource dataSource;
	private String dbType;	
	private DBConfig config;
	
	/**
	 * 创建数据库操作对象
	 * @throws ParserConfigException 
	 */
	public DBAccess() throws ParserConfigException{
		this(DBConfig.DEFAULT_TYPE);
	}
	/**
	 * 创建数据库操作对象
	 * @param dbType 数据库类型，对应配置文件中type属性
	 * @throws ParserConfigException
	 */
	public DBAccess(String dbType) throws ParserConfigException{
		this(ConfigFactory.getInstance().newDatabaseConfig(),dbType);
	}
	/**
	 * 创建SQL脚本执行类对象
	 * @param config DB链接配置对象
	 * @throws ParserConfigException 
	 */
	public DBAccess(DBConfig config,String dbType) throws ParserConfigException{
		this.config = config;
		this.dbType = dbType;
		this.dataSource = createDataSource(this.dbType);
		this.runner = new QueryRunner(dataSource,true);
	}
	/**
	 * 获取数据库链接
	 * @return
	 * @throws SQLException
	 * @throws ParserConfigException 
	 */
	public Connection getConnection() throws SQLException{
		return this.dataSource.getConnection();
	}
	public QueryRunner getRunner(){
		return this.runner;
	}
	/**
	 * 获取数据源
	 * @return org.apache.tomcat.jdbc.pool.DataSource对象
	 * @throws ParserConfigException 
	 */
	public DataSource getDataSource(){
		return this.dataSource;
	}
	/**
	 * 获取数据源
	 * @param dbtyp 数据库类型，对应配置文件中对应type属性
	 * @return org.apache.tomcat.jdbc.pool.DataSource 对象
	 * @throws ParserConfigException 
	 */
	private DataSource createDataSource(String dbtype) throws ParserConfigException{
		DataSource datasource;
		if (!dataSourceMap.containsKey(dbtype)){
			synchronized (dataSourceMap) {
				if (!dataSourceMap.containsKey(dbtype)) {					
					datasource = new DataSource();
					datasource.setPoolProperties(getPoolProperties(dbtype));
					dataSourceMap.putIfAbsent(dbtype, datasource);
				}
			}
		}		
		return dataSourceMap.get(dbtype);
	}
	/**
	 * 获取配置文件中指定type数据库连接池属性
	 * @param dbtype 数据库类型，对应配置文件中对应type属性
	 * @return
	 * @throws ParserConfigException
	 */
	private PoolProperties getPoolProperties(String dbtype) throws ParserConfigException{
		return this.config.getPoolProperties(dbtype);		
	}
	/**
	 * 执行批处理 INSERT, UPDATE, DELETE
	 * <br/> 示例：<br/>
	 * <br/>String sql = "insert testTable(f1,f2,f3) values(?,?,?)";
	 * <br/>Object[][] params = new Object[][]{{"row11","row12","row12"},{"row21","row22","row22"},{"row21","row22","row22"}};
	 * <br/>batch(sql,params);
	 * @param sql SQL脚本
	 * @param params 参数数组
	 * @return 返回一个更新计数数组
	 * @throws SQLException
	 */
	 public int[] batch(String sql, Object[][] params) throws SQLException {
		 return this.runner.batch(sql, params);
	 }
	 /**
	  * 执行数据查询，返回是否存在记录
	  * <br/>
	  * 通过判断查询语句返回结果的第一行第一列是否等于0
	  * <br/>
	  * 使用这样的sql：select count(1) from table_name where ...
	  * @param sql SQL脚本
	  * @return true/false 是否存在记录
	  * @throws SQLException
	  */
	 public boolean exists(String sql) throws SQLException{
		 return this.exists(sql, (Object[]) null);
	 }
	 /**
	  * 执行数据查询，返回是否存在记录
	  * <br/>
	  * 通过判断查询语句返回结果的第一行第一列是否等于0
	  * <br/>
	  * 使用这样的sql：select count(1) from table_name where ....
	  * @param sql SQL脚本
	  * @param params 参数
	  * @return true/false 是否存在记录
	  * @throws SQLException
	  */
	 public boolean exists(String sql, Object... params) throws SQLException{
		 Object count = this.queryObject(sql, params);
		 if(count == null || ((BigDecimal)count).intValue() == 0)
			 return false;
		 return true;
	 }
	 /**
	  * 执行数据库查询，返回结果集第一行第一列的数据
	  * @param sql SQL脚本
	  * @return  封装了结果集第一行第一列的数据的 Object
	  * @throws SQLException
	  */
	 public Object queryObject(String sql) throws SQLException{
		 return this.queryObject(sql, (Object[]) null);
	 }
	 /**
	  * 执行数据库查询，返回结果集第一行第一列的数据
	  * @param sql SQL脚本
	  * @param params 参数
	  * @return  封装了结果集第一行第一列的数据的 Object
	  * @throws SQLException
	  */
	 public Object queryObject(String sql, Object... params) throws SQLException{
		 ResultSetHandler<Object> rsh = new ScalarHandler<Object>();
		 return this.query(sql, rsh, params);
	 }
	
	 /**
	  * 执行数据库查询，奖结果集第一行数据封装到Object[]
	  * @param sql SQL脚本
	  * @return 封装了结果第一行数据的 Object[]
	  * @throws SQLException
	  */
	 public Object[] queryArray(String sql) throws SQLException{
		 return this.queryArray(sql, (Object[]) null);
	 }
	 /**
	  * 执行数据库查询，奖结果集第一行数据封装到Object[]
	  * @param sql SQL脚本
	  * @param params 参数
	  * @return 封装了结果第一行数据的 Object[]
	  * @throws SQLException
	  */
	 public Object[] queryArray(String sql, Object... params) throws SQLException{
		 ResultSetHandler<Object[]> rsh = new ArrayHandler();
		 Object[] results = this.runner.query(sql, rsh, params);
		 return results;
	 }
	 /**
	  * 执行数据库查询，奖结果集数据封装到List< Object[] >
	  * @param sql SQL脚本
	  * @return 封装了结果数据的List< Object[] >
	  * @throws SQLException
	  */
	 public List<Object[]> queryArrayList(String sql) throws SQLException{
		 return this.queryArrayList(sql, (Object[]) null);
	 }
	 /**
	  * 执行数据库查询，奖结果集数据封装到List< Object[] >
	  * @param sql SQL脚本
	  * @param params 参数
	  * @return 封装了结果数据的List< Object[] >
	  * @throws SQLException
	  */
	 public List<Object[]> queryArrayList(String sql, Object... params) throws SQLException{
		 ResultSetHandler<List<Object[]>> rsh = new ArrayListHandler();
		 List<Object[]> results = this.runner.query(sql, rsh, params);
		 return results;
	 }
	 /**
	  * 执行查询，将结果集的第一行数据封装到JavaBean中
	  * @param <T> JavaBean
	  * @param sql SQL脚本
	  * @param type JavaBean.class
	  * @return 封装了第一行数据的JavaBean
	 * @throws SQLException 
	  */
	 public <T> T queryBean(String sql, Class<T> type) throws SQLException{
		 return this.queryBean(sql, type, (Object[]) null);
	 }
	 /**
	  * 执行数据库查询，将结果集的第一行数据封装到JavaBean中
	  * @param <T> JavaBean
	  * @param sql SQL脚本
	  * @param type JavaBean.class
	  * @param params 参数
	  * @return 封装了第一行数据的JavaBean
	 * @throws SQLException 
	  */
	 public <T> T queryBean(String sql, Class<T> type,Object... params) throws SQLException{
		 ResultSetHandler<T> rsh = new BeanHandler<T>(type);
		 T results = this.runner.query(sql, rsh, params);

		 return results;
	 }
	 /**
	  * 执行数据库查询，将结果集数据封装到JavaBean数组中
	  * @param <T> JavaBean
	  * @param sql SQL脚本
	  * @param type JavaBean.class
	  * @return 封装了结果集数据的JavaBean数组
	 * @throws SQLException 
	  */
	 public <T> List<T> queryBeanList(String sql, Class<T> type) throws SQLException{
		 return this.queryBeanList(sql, type, (Object[]) null );
	 }
	 /**
	  * 执行数据库查询，将结果集数据封装到JavaBean数组中
	  * @param <T> JavaBean
	  * @param sql SQL脚本
	  * @param type JavaBean.class
	  * @param params 参数
	  * @return 封装了结果集数据的JavaBean数组
	 * @throws SQLException 
	  */
	 public <T> List<T> queryBeanList(String sql, Class<T> type,Object... params) throws SQLException{
		 ResultSetHandler<List<T>> rsh = new BeanListHandler<T>(type);
		 List<T> results = this.runner.query(sql, rsh, params);
		 return results;
	 }
	 /**
	  * 执行数据库查询
	  * @param <T> 返回值类型
	  * @param sql SQL脚本
	  * @param rsh 结果集转换的类型
	  * @return 封装到指定类型数据的查询结果
	  * @throws SQLException
	  */
	 public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		 return this.runner.query(sql, rsh);
	 }
	 /**
	  * 执行数据库查询
	  * @param <T> 返回值类型
	  * @param sql SQL脚本
	  * @param rsh 结果集转换的类型
	  * @param params 参数
	  * @return 封装到指定类型数据的查询结果
	  * @throws SQLException
	  */
	 public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException{
		 return this.runner.query(sql, rsh, params);
	 }
	 /**
	  * 执行数据库更新操作 DML、DDL
	  * @param sql SQL脚本
	  * @return 影响的行数
	  * @throws SQLException
	  */
	 public int update(String sql) throws SQLException {
		 return this.runner.update(sql);
	 }
	 /**
	  * 执行数据库更新操作 DML、DDL
	  * @param sql SQL脚本
	  * @param params 参数
	  * @return 影响的行数
	  * @throws SQLException
	  */
	 public int update(String sql, Object... params) throws SQLException {
		 return this.runner.update(sql, params);
	 }
	/**
	 * 执行存储过程
	 * @param sql 存储过程命令 <code>"{? = call proc(?,?)}"</code>
	 * @param params 参数集合，出参使用OutParameter接口
	 * @return 返回影响的行数，没有则返回-1
	 * @throws SQLException 数据库异常
	 */
	public int procedure(String sql, Object... params) throws SQLException {	 
		if (sql == null) {
		    throw new SQLException("Null SQL statement");
		}
		Connection conn = null;
		CallableStatement stmt = null;
		int result = 0;
		Map<Integer, OutParameter<?>> outs = null;
		try {
			conn = this.getConnection();
			stmt = conn.prepareCall(sql);
			outs = new HashMap<Integer, OutParameter<?>>();
			for (int i = 0; i < params.length; i++) {
				if(params[i] instanceof OutParameter){   		 
					stmt.registerOutParameter(i + 1,((OutParameter<?>)params[i]).getSqlType());
					outs.put(i+1, (OutParameter<?>)params[i]);
				}else if(params[i] != null) {
			        stmt.setObject(i + 1, params[i]);
			    } else {
			        stmt.setNull(i + 1, Types.VARCHAR);
			    };
			}
			stmt.execute();
			 
			Iterator<Entry<Integer, OutParameter<?>>>  itr = outs.entrySet().iterator();
			while (itr.hasNext()) {
				Entry<Integer, OutParameter<?>> entry = itr.next();
				OutParameter<?> param = entry.getValue();
				param.setObject( stmt.getObject(entry.getKey()) );				
			}
			result = stmt.getUpdateCount();
		} finally {
			try {
				DbUtils.close(stmt);
			} finally {
				DbUtils.close(conn);
			}
		}		
		return result;
	}
}
