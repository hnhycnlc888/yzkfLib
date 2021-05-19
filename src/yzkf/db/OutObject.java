package yzkf.db;

/**
 * 存储过程出参，如果是游标使用OutCursor
 * @author quilw
 * @version V4.0.0
 * @param <T> 返回的java类型
 */
public class OutObject<T> implements OutParameter<T> {

	private int sqlType;
	private T value;
	public OutObject(){
		this.sqlType = java.sql.Types.OTHER;
	}
	/**
	 * 创建出参对象
	 * @param sqlType 参数类型 java.sql.Types
	 */
	public OutObject(int sqlType){
		this.sqlType = sqlType;
	}
	/**
	 * 获取参数数据库类型
	 * @return
	 */
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
	 * 获取参数返回值
	 */
	@Override
	public T getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}
//	/**
//	 * 将记录集转换为出参类型
//	 * <br/>OutCursor设置后通过getValue获取
//	 * <br/>OutObject该方法无效，改用setValue
//	 */
//	@Override
//	public void handle(ResultSet rs) throws SQLException {
//		// TODO Auto-generated method stub
//	}
	/**
	 * 设置出参的值
	 * <br/>OutCursor通过handle设置
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setObject(Object value) {
		// TODO Auto-generated method stub
		this.value = (T) value;
	}

}
