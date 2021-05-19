package yzkf.test;

import yzkf.app.Log;
import yzkf.exception.ParserConfigException;

public class LogTest {
	
	public static void main(String[] args) throws ParserConfigException{
//		Log log = Log.getLogger("CS2012");
//		log.trace("I'm trace!");
//		log.debug("I'm debug!");
//		log.info("I'm info!");		
//		log.warn("I'm warn!");
//		log.error("I'm error!");
//		log.fatal("I'm fatal!");
		Log log2 = Log.getLogger();
		log2.trace("trace!");
		log2.debug("debug!");
		log2.info("info!");		
		log2.warn("warn!");
		log2.error("error!");
		log2.fatal("fatal!");
	}
}
