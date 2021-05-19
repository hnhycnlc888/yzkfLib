package yzkf.db;

import java.util.Map;
import org.apache.commons.dbutils.handlers.MapHandler;

/**
 * 存储过程出参,将数据库返回记录集参数首行转换为Map对象,当返回游标时使用该类作为出参
 * @author qiulw
 * @version V4.0.0 2012.02.10
 */
public class OutMap extends OutCursor<Map<String,Object>> {
	/**
	 * 创建出参对象
	 */
	public OutMap() {
		super(new MapHandler());
	}
	/**
	 * 创建出参对象
	 * @param type 出参数据库类型 java.sql.Types
	 */
	public OutMap(int sqlType) {
		super(new MapHandler(), sqlType);
	}

}
