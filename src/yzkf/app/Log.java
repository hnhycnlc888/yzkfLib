package yzkf.app;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.LogLog;

import yzkf.config.ConfigFactory;
import yzkf.config.ProjectConfig;
import yzkf.exception.ParserConfigException;
import yzkf.utils.Utility;
/**
 * 日志类
 * @author qiulw
 * @version V4.0.0
 */
public class Log {
	public static final String DEFAULT_APPENDER_NAME = "public";
	private org.apache.log4j.Logger log;
	private String project = DEFAULT_APPENDER_NAME;    
    private static ConcurrentMap<String, Log> instances = new ConcurrentHashMap<String, Log>();
    /**
     * 获取日志类对象
     * @param project 
     * 	项目名称
     * 	appender.File 使用配置文件中名为  public 的 appender 的 File 所在public同级目录中的 project同名文件夹；\n
     *  appender.layout 使用配置文件中名为 public 的 appender 的 layout.
     * @return 返回日志对象
     * @throws IOException
     */
    public static Log getLogger(String project){
    	if(Utility.isEmptyOrNull(project))
    		project = DEFAULT_APPENDER_NAME;
		if (!instances.containsKey(project)){
			synchronized (instances) {
				if (!instances.containsKey(project)) {					
					Log instance = new Log(project);
					instances.putIfAbsent(project, instance);
				}
			}
		}		
		return instances.get(project);
    }
    /**
     * 获取公用日志类对象，日志将使用配置文件appender记录
     * @return
     * @throws IOException
     */
    public static Log getLogger(){
    	String defaultProjectCode = ProjectConfig.getInstance().getCode();
    	if(Utility.isEmptyOrNull(defaultProjectCode))
    		defaultProjectCode = DEFAULT_APPENDER_NAME;
    	return getLogger(defaultProjectCode);
    }
    /**
     * 新建日志文件对象
     * @param project 项目名称
     * appender.File 使用配置文件中名为  public 的 appender 的 File 所在public同级目录中的 project同名文件夹；\n
     * appender.layout 使用配置文件中名为 public 的 appender 的 layout.
     * @throws IOException
     */
    private Log(String project){    	
    	this.project = project;
    	this.log = org.apache.log4j.Logger.getLogger(project);  
    	if(Utility.isEmptyOrNull(project) || project == DEFAULT_APPENDER_NAME)
    		return;
    	//this.log.setAdditivity(false);	//是否同时使用全局Appender输出
    	DailyRollingFileAppender appender = (DailyRollingFileAppender) log.getAppender(project);
    	if(appender == null){
    		DailyRollingFileAppender publicAppender = (DailyRollingFileAppender) org.apache.log4j.Logger.getLogger(DEFAULT_APPENDER_NAME).getAppender(DEFAULT_APPENDER_NAME);
    		//DailyRollingFileAppender publicAppender = (DailyRollingFileAppender) org.apache.log4j.Logger.getRootLogger().getAppender(DEFAULT_APPENDER_NAME);
    		if(publicAppender != null){
    			try {					
					appender = new DailyRollingFileAppender(publicAppender.getLayout(), 
							publicAppender.getFile().replace(DEFAULT_APPENDER_NAME, project),
							publicAppender.getDatePattern());
//					appender = new DailyRollingFileAppender();
//					appender.setLayout(publicAppender.getLayout());
//					appender.setDatePattern(publicAppender.getDatePattern());
//					appender.setFile(publicAppender.getFile().replace(DEFAULT_APPENDER_NAME, project),
//							publicAppender.getAppend(), 
//							publicAppender.getBufferedIO(), 
//							publicAppender.getBufferSize());
					
					appender.setName(project);
					appender.setThreshold(publicAppender.getThreshold());					
					appender.setEncoding(publicAppender.getEncoding());
					appender.setErrorHandler(publicAppender.getErrorHandler());										
					appender.setImmediateFlush(publicAppender.getImmediateFlush());

	        		log.addAppender(appender);
				} catch (IOException e) {
					this.log.setAdditivity(true);
					LogLog.error("为"+project+"日志增加Appender发生IO异常", e);
				}        		
    		}    		    		
    	}
    }

   /**
    * 静态初始化，使用配置工程获取Log4j的配置初始化Log4j
    */
    static{
		try {
			ConfigFactory factory = ConfigFactory.getInstance();
			String configFilename = factory.getLog4jConfigPath();
	    	if(!Utility.isEmptyOrNull(configFilename)){
	    		PropertyConfigurator.configure(configFilename);
	    	}else{		
	    		//BasicConfigurator.configure();
	    	}
		} catch (ParserConfigException e) {
			LogLog.error("初始化Log4j配置文件读取异常", e);
			//BasicConfigurator.configure();
		}    	
    }
    /**
     * 获取项目名称
     * @return
     */
    public String getProject(){
    	return this.project;
    }
    /**
     * 记录fatal级别日志
     * @param msg 信息
     */
    public void fatal(String msg){
    	log.fatal(msg);
    }
    /**
     * 记录fatal级别日志
     * @param msg 信息
     */
    public void fatal(String msg,Throwable t){
    	log.fatal(msg,t);
    }
    /**
     * 记录debug日志
     * @param msg 信息
     */
    public void debug(String msg){
    	log.debug(msg);
    }
    /**
     * 记录debug级别日志
     * @param msg 信息
     * @param t 异常对象
     */
    public void debug(String msg, Throwable t){
    	log.debug(msg, t);
    }
    /**
     * 记录error级别日志
     * @param msg 信息
     * @param t 异常对象
     */
    public void error(String msg){
    	log.error(msg);
    }
    /**
     * 记录error级别日志
     * @param msg 信息
     * @param t 异常对象
     */
    public void error(String msg, Throwable t){
    	log.error(msg, t);
    }
    /**
     * 记录info级别日志
     * @param msg 信息
     * @param t 异常对象
     */
    public void info(String msg){
    	log.info(msg);
    }
    /**
     * 记录info级别日志
     * @param msg 信息
     * @param t 异常对象
     */
    public void info(String msg, Throwable t){
    	log.info(msg, t);
    } 
    /**
     * 记录trace级别日志
     * @param msg 信息
     * @param t 异常对象
     */
    public void trace(String msg){
    	log.trace(msg);
    }
    /**
     * 记录trace级别日志
     * @param msg 信息
     * @param t 异常对象
     */
    public void trace(String msg, Throwable t){
    	log.trace(msg, t);
    }
    /**
     * 记录warn级别日志
     * @param msg 信息
     * @param t 异常对象
     */
    public void warn(String msg){
    	log.warn(msg);
    }
    /**
     * 记录warn级别日志
     * @param msg 信息
     * @param t 异常对象
     */
    public void warn(String msg, Throwable t){
    	log.warn(msg, t);
    } 
}
