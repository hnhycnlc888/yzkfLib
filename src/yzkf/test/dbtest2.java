package yzkf.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import oracle.jdbc.OracleTypes;

import yzkf.db.DBAccess;
import yzkf.db.OutBeanList;
import yzkf.db.OutMap;
import yzkf.db.OutObject;
import yzkf.exception.ParserConfigException;

public class dbtest2 {
	public static void main(String[] args) throws ParserConfigException, SQLException{
		upbyin();
		//arrparam();
//		DBAccess dao = new DBAccess();
//		String sql = "{call pkg_y_cn20120207.P_Y_CN20120207_QUERYACT(?,?)}";
		
//		String rtn = null;
//		List<Dbmodel2> act;
//		try {
//			OutObject<String> str = new OutObject<String>(OracleTypes.VARCHAR);
//			OutBeanList<Dbmodel2> list = new OutBeanList<Dbmodel2>(Dbmodel2.class,OracleTypes.CURSOR);
//			dao.procedure(sql, str,list);
//			rtn = str.getValue();
//			act = list.getValue();
//			Iterator<Dbmodel2> ite = act.iterator();
//			while(ite.hasNext()){
//				Dbmodel2 m = ite.next();
//				System.out.println(m.getId());
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
//		OutMap out = new OutMap(OracleTypes.ARRAY);
//		dao.procedure("{call PKG_Y_CN20120207_USER.outrow(?)}", out);
//		System.out.println(out.getValue().get(("rescode")));
		
//		OutObject<BigDecimal> out = new OutObject<BigDecimal>(OracleTypes.NUMBER);
//		dao.procedure("{call PKG_Y_CN20120207_USER.test(?)}", out);
//		Long lo = out.getValue().longValue();
//		System.out.println(lo);
	}
	public static void arrparam(){
		DBAccess dao = new DBAccess();
		String sql = "select count(1) from T_Y_CN20120207_ACTIVITY t where actid > ? and rownum <= ?";
		Object[] ps = new Object[2];
		ps[0] = 100;
		ps[1] = 10;
		try {
			Object ret = dao.queryObject(sql, ps);
			System.out.print(ret);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void upbyin(){
		DBAccess dao = new DBAccess();
		String sql = "update t_y_cn20120207_act set status = ? where ano = ? and status in (?)";
		int r;
		try {
			r = dao.update(sql, 3,"cn20120203",0);
			System.out.println(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
