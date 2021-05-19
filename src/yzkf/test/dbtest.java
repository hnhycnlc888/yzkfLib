package yzkf.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

import yzkf.app.Log;
import yzkf.db.DBAccess;
import yzkf.db.OutArrayList;
import yzkf.db.OutCursor;
import yzkf.db.OutObject;
import yzkf.exception.ParserConfigException;

public class dbtest {
	private static final Logger LOG = LoggerFactory.getLogger(dbtest.class.getName());
	
	public static void main(String[] args) throws ParserConfigException{
		testQueryObejct();
		//testExists();
		//testReturning();
	}
	public static void testReturning(){
		String sql = "insert into T_Y_CN20120207_CODES_TMP(ASID,CODES) values(?,?) returning asid into ?";
		DBAccess dba = new DBAccess();
		OutObject<BigDecimal> out = new OutObject<BigDecimal>(OracleTypes.NUMBER);
		try {
			int r = dba.update(sql, 1,"abc",1);
			System.out.println(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(out.getValue());
	}
	public static void testExists(){
		DBAccess dba = new DBAccess();
		try {
			if(dba.exists("select count(1) from t_y_cn20120207_invitelog")){
				System.out.println("exists");
			}else{
				System.out.println("not exists");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testQueryObejct(){
		DBAccess dba = new DBAccess();
		try {
			Object ret = dba.queryObject("select * from t_y_cn20110207_login where projectno='GD201404A1'");
			System.out.println(ret);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void test(){
//		DBAccess dba = new DBAccess();
//		try {
//			int effects=0;
			//int effect =dba.update("insert into dbtest(strs,ints) values(?,?)","a",1);
			
//			Object[][] params = new Object[][] {{"o",2},{"p",3},{"q",4},{"r",4},{"s",4},{"t",4},{"u",4},{"v",4},{"w",4},{"x",4}};
//			int[] effectssss = dba.batch("insert into dbtest(strs,ints) values(?,?)", params);
			
//			Object[][] params = new Object[][] {{1,"a"},{2,"b"},{3,"c"}};
//			int[] effects = dba.batch("update dbtest set ints=? where strs=?",params);
			
			//Object[] obj = dba.queryObject("select * from dbtest");
			
			//List<Object[]> list = dba.queryList("select * from dbtest");
			
//			effects = dba.update("{call pkg_dbtest.up(?,?)}", "d",10);
//			System.out.println(effects);
			
//			effects = dba.update("{call pkg_dbtest.up2(?,?,?)}", "abc",9,null);
//			System.out.println(effects);
			
//			Object[] r = new Object[2];
//			Object[] obj = dba.queryObject("{call pkg_dbtest.query(?)}",r);
//			System.out.println(obj);
			
//			 ResultSetHandler<List<Object>> h = new BeanListHandler<Object>(Object.class);
//		     List<Object> results = h.handle((ResultSet)null);
		        
//			Object obj = new Object();
//			int effects = dba.update("{call pkg_dbtest.up2(?,?,?)}", "e",8,null);
			
//			OutObject<Integer> outp = new OutObject<Integer>();
//			effects = dba.procedure("{call pkg_dbtest.up2(?,?,?)}", "d",4,outp);
//			System.out.println(outp.getValue());
			
//			ResultSetHandler<List<Object[]>> rsh = new ArrayListHandler();
//			//OutCursor<List<Object[]>> outp = new OutCursor<List<Object[]>>(rsh,OracleTypes.CURSOR);	
//			OutArrayList outp = new OutArrayList(OracleTypes.CURSOR);
//			OutObject<String> outObj = new OutObject<String>(OracleTypes.VARCHAR);
//			effects = dba.procedure("{call pkg_dbtest.query(?,?,?)}", "d",outObj,outp);
//			System.out.println(outp.getValue());
//			
			
//			DBAccess dao = new DBAccess();
//			//List<Dbmodel> list = dao.queryList("select OBJECT_NAME,OBJECT_ID from test_log");
//			List<Dbmodel> list = dao.query("select OBJECT_NAME name,OBJECT_ID id from test_log", Dbmodel.class);
//			Iterator<Dbmodel> iter = list.iterator();
//			StringBuilder sb = new StringBuilder();
//			sb.append("[");
//			while(iter.hasNext()){
//				Dbmodel m = iter.next();
//				sb.append("{\"name\":\""+m.name+"\",\"id\":\""+m.id+"\"},");
//			}
//			sb.deleteCharAt(sb.length()-1);
//			sb.append("]");
//			System.out.println(sb.toString());
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		Log log = Log.getLogger();
//		log.warn("警告");
//		log.debug("测试");
//		Log cnlog = Log.getLogger("CNMail");
//		Log gdlog = Log.getLogger("GDMail");
//		gdlog.error("GDMail程序异常2次");
//		cnlog.warn("cnMail 警告");
//		cnlog.error("CNMail程序异常");
//			gdlog.error("GDMail程序异常3次啊");
		
		//SqlRunner runner = new SqlRunner();
		//runner.
//		try {
////			Query sqlhelper = new Query();
////		
////		int efect = sqlhelper.ExecuteNonQuery(" insert into t_y_cn20111201_view  (vid, curtime, usernumber, userid, userip, refer, bid) values  (SEQ_CN20111201_VIEW_VID.NEXTVAL,   null,   '159',   'sessionid',   '127.0.0.1',   'baidu',   '42')");
////		System.out.println(efect);
////		
//		Query sqlhelper = new Query();
//		ArrayList<SqlParameter> params = new ArrayList<SqlParameter>();
//		//params.add(new SqlParameter(SqlType.Int,1));
//
//		params.add(new SqlParameter(java.sql.Types.DATE,new java.util.Date()));
//		params.add(new SqlParameter(java.sql.Types.VARCHAR,"137777777"));
//		params.add(new SqlParameter(java.sql.Types.VARCHAR,"userid"));
//		params.add(new SqlParameter(java.sql.Types.VARCHAR,"127.1.1.1"));
//		params.add(new SqlParameter(java.sql.Types.VARCHAR,null));
//		params.add(new SqlParameter(java.sql.Types.VARCHAR,"53"));
//
//		String sql = " insert into t_y_cn20111201_view  "
//		+" (vid, curtime, usernumber, userid, userip, refer, bid)"
//		+" values (SEQ_CN20111201_VIEW_VID.NEXTVAL, ?,?,?,?,?,?)";
//		
////		String sql = " insert into t_y_cn20111201_view  "
////			+" (vid, usernumber,)"
////			+" values (?,?)";
//		
////		String sql = " update t_y_cn20111201_view set usernumber=? where vid=1 ";
//		
////		Connection conn = null;
////		try{
////			conn = sqlhelper.getConnection();
////			PreparedStatement state = conn.prepareStatement(sql);
////			//state.setString(1, "15912345678");		
////			sqlhelper.prepareParameters(state, params);
////			
////			int efect = state.executeUpdate();
////			System.out.println(efect);
////		
////		}finally{
////			conn.close();
////		}
//		
//		int efect = sqlhelper.ExecuteNonQuery(sql,params);
//		System.out.println(efect);
//		} catch (ParserConfigException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}catch(SQLException e){
//			e.printStackTrace();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		WapLogin waplogin = new WapLogin();
//		WapLoginEnum result = waplogin.execute("13760709457", "Iphone", "000", "376084512", "04E5EF1173E9669F039A107A3D6EDB68");
//		System.out.println(result.getDescr());
//		try {
//			System.out.println("try");
//			return;
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("catch");
//		} finally{
//			
//			System.out.println("finally");
//		}
//		System.out.println("other");
	}
}
