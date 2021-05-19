package yzkf.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 存储过程出参,当返回游标时使用该类作为出参
 * @author qiulw
 * @version V4.0.0
 * @param <T> 返回的java类型
 */
public class OutCursor<T> implements OutParameter<T>{
	
	private T value;
	private int sqlType;
	private  ResultSetHandler<T> rsh;
	/**
	 * 实例化出参对象
	 * @param rsh 将结果集ResultSet转为返回值 T 的处理类
	 */
	public OutCursor(ResultSetHandler<T> rsh){
		this.rsh = rsh;
		this.sqlType = java.sql.Types.OTHER;
	}
	/**
	 * 实例化出参对象
	 * @param rsh 将结果集ResultSet转为返回值 T 的处理类
	 * @param sqlType 出参数据库类型 java.sql.Types
	 */
	public OutCursor(ResultSetHandler<T> rsh,int sqlType){
		this.sqlType = sqlType;
		this.rsh = rsh;
	}
	/**
	 * 获取参数数据库类型
	 * @return
	 */
	@Override
	public int getSqlType() {
		return sqlType;
	}
	/**
	 * 设置参数数据库类型
	 * @param sqlType java.sql.Types
	 */
	public void setSqlType(int sqlType) {
		this.sqlType = sqlType;
	}
	/**
	 * 设置将结果集ResultSet转为返回值 T 的处理类
	 * @return
	 */
	public ResultSetHandler<T> getRsh() {
		return rsh;
	}
	/**
	 * 获取将结果集ResultSet转为返回值 T 的处理类
	 * @param rsh
	 */
	public void setRsh(ResultSetHandler<T> rsh) {
		this.rsh = rsh;
	}
	/**
	 * 获取参数返回值 T
	 */
	@Override
	public T getValue(){
		return value;
	}
//	/**
//	 * 将记录集转换为出参类型 T
//	 * <br/>OutCursor设置后通过getValue获取
//	 * <br/>OutObject该方法无效，改用setValue
//	 */
//	@Override
//	public void handle(ResultSet rs) throws SQLException{
//		if (this.rsh == null) {
//            throw new SQLException("Null ResultSetHandler");
//        }
//		this.value = this.rsh.handle(rs);
//	}
	/**
	 * @throws SQLException 
	 * 设置出参的值 T
	 * <br/>OutCursor通过handle设置
	 * @throws  数据库异常
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setObject(Object value) throws SQLException {
		// TODO Auto-generated method stub
		if (this.rsh == null) {
			throw new NullPointerException("Null ResultSetHandler");
        }
		if(value instanceof ResultSet){
			ResultSet rs = null;
			try{
				rs = (ResultSet)value;
				this.value = this.rsh.handle(rs);
			}finally{
				DbUtils.closeQuietly(rs);
			}
		}else{
			this.value = (T) value;
		}
	}

}
