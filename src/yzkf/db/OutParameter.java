package yzkf.db;
import java.sql.SQLException;
/**
 * 数据库出参类型接口
 * @author qiulw
 * @version V4.0.0 2012.02.10
 * @param <T>
 */
public interface OutParameter<T> {
	T getValue();
	int getSqlType();
	//void handle(ResultSet rs) throws SQLException;
	void setObject(Object value) throws SQLException;
}
