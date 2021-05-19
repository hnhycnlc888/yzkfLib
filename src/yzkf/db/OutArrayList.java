package yzkf.db;

import java.util.List;

import org.apache.commons.dbutils.handlers.ArrayListHandler;
/**
 * 存储过程出参,将数据库返回记录集参数首行转换为List< Object[] >对象,当返回游标时使用该类作为出参
 * @author qiulw
 * @version V4.0.0 2012.02.10
 */
public class OutArrayList extends OutCursor<List<Object[]>> {

	/**
	 * 创建出参对象
	 */
	public OutArrayList() {
		super(new ArrayListHandler());
	}
	/**
	 * 创建出参对象
	 * @param type 出参数据库类型 java.sql.Types
	 */
	public OutArrayList(int sqlType) {
		super(new ArrayListHandler(), sqlType);
	}

}
