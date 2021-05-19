package yzkf.db;
import org.apache.commons.dbutils.handlers.BeanHandler;
/**
 * 存储过程出参,将数据库返回记录集参数首行转换为JavaBean,当返回游标时使用该类作为出参
 * @author qiulw
 *
 * @param <T> JavaBean
 */
public class OutBean<T> extends OutCursor<T> {

	/**
	 * 创建出参对象
	 * @param type JavaBean.class
	 */
	public OutBean(Class<T> type) {
		super(new BeanHandler<T>(type));
		// TODO Auto-generated constructor stub
	}
	/**
	 * 创建出参对象
	 * @param type JavaBean.class
	 * @param sqlType 出参数据库类型 java.sql.Types
	 */
	public OutBean(Class<T> type,int sqlType) {
		super(new BeanHandler<T>(type),sqlType);
		// TODO Auto-generated constructor stub
	}
}
