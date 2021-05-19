package yzkf.db;

import org.apache.commons.dbutils.handlers.ArrayHandler;
/**
 * 存储过程出参,将数据库返回记录集参数首行转换为Object[]对象,当返回游标时使用该类作为出参
 * @author qiulw
 * @version V4.0.0 2012.02.10
 */
public class OutArray extends OutCursor<Object[]> {
	/**
	 * 创建出参对象
	 */
	public OutArray() {
		super(new ArrayHandler());
	}
	/**
	 * 创建出参对象
	 * @param type 出参数据库类型 java.sql.Types
	 */
	public OutArray(int sqlType) {
		super(new ArrayHandler(), sqlType);
	}

}
