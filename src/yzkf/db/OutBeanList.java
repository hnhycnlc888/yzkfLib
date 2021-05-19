package yzkf.db;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
/**
 * 存储过程出参,将数据库返回记录集参数转换为List< JavaBean >,当返回游标时使用该类作为出参
 * @author qiulw
 * 
 * @param <T> JavaBean
 */
public class OutBeanList<T> extends OutCursor<List<T>> {

	/**
	 * 创建出参对象
	 * @param type JavaBean.class
	 */
	public OutBeanList(Class<T> type) {
		super(new BeanListHandler<T>(type));
	}
	/**
	 * 创建出参对象
	 * @param type JavaBean.class
	 * @param sqlType 出参数据库类型 java.sql.Types
	 */
	public OutBeanList(Class<T> type,int sqlType) {
		super(new BeanListHandler<T>(type),sqlType);
	}
}
