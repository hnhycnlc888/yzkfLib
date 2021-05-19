package yzkf.db;

import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;
/**
 * 存储过程出参,将数据库返回记录集参数转换为List< Map< String,Object > >对象,当返回游标时使用该类作为出参
 * @author qiulw
 * @version V4.0.0 2012.02.10
 */
public class OutMapList extends OutCursor<List<Map<String,Object>>> {

	/**
	 * 创建出参对象
	 */
	public OutMapList() {
		super(new MapListHandler());
	}
	/**
	 * 创建出参对象
	 * @param type 出参数据库类型 java.sql.Types
	 */
	public OutMapList(int sqlType) {
		super(new MapListHandler(), sqlType);
	}

}
