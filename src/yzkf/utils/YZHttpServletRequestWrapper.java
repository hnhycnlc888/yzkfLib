package yzkf.utils;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class YZHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public YZHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	/**
     * Returns the value of a request parameter as a <code>String</code>, ignoring case considerations,
     * or <code>null</code> if the parameter does not exist. Request parameters
     * are extra information sent with the request.  For HTTP servlets,
     * parameters are contained in the query string or posted form data.
     * 
	 * @param name a String specifying the name of the parameter
	 * @return a String representing the single value of the parameter
	 * 
	 * @see #getParameterIgnoreCase
	 */
	public String getParameterIgnoreCase(String name){
		Enumeration<String> enu = super.getParameterNames();
		while(enu.hasMoreElements()){
			String paraName=enu.nextElement();
			 if(name.equalsIgnoreCase(paraName))   {   
                 return super.getParameter(paraName);   
			 }
		}
		return null;
	}
	/**
     * Returns an array of <code>String</code> objects containing 
     * all of the values the given request parameter has, ignoring case considerations, or 
     * <code>null</code> if the parameter does not exist.
     *
     * <p>If the parameter has a single value, the array has a length
     * of 1.
     *
     * @param name	a <code>String</code> containing the name of 
     *			the parameter whose value is requested
     *
     * @return		an array of <code>String</code> objects 
     *			containing the parameter's values
     *
     * @see		#getParameterIgnoreCase
     *
	 */
	public String[] getParameterValuesIgnoreCase(String name){
		Enumeration<String> enu = super.getParameterNames();
		while(enu.hasMoreElements()){
			String paraName=enu.nextElement();
			 if(paraName.equalsIgnoreCase(name))   {   
                 return super.getParameterValues(paraName);   
			 }
		}
		return null;		
	}
	/**
	 * ??????HttpServletRequest?????????cookie??????(??????????????????)???cookie???
	 * @param name cookie??????
	 * @return ??????cookie?????????cookie?????????????????????????????????
	 */
	public String getCookieValueIgnoreCase(String name){
		return getCookieValue(name, true);
	}
	/**
	 * ??????HttpServletRequest?????????cookie?????????cookie???
	 * @param name cookie??????
	 * @return ??????cookie?????????cookie?????????????????????????????????
	 */
	public String getCookieValue(String name){
		return getCookieValue(name, false);
	}
	/**
	 * ??????HttpServletRequest?????????cookie?????????cookie???
	 * @param name cookie??????
	 * @param ignoreCase cookie???????????????????????????
	 * @return ??????cookie?????????cookie?????????????????????????????????
	 */
	private String getCookieValue(String name,boolean ignoreCase){
		Cookie[] cookies = super.getCookies();
		if(cookies == null) return "";
		for(int i=0; i<cookies.length; i++) {
			Cookie cookie = cookies[i];
			String cookieName = cookie.getName();
			if (ignoreCase ? name.equalsIgnoreCase(cookieName) : name.equals(cookieName))
				return cookie.getValue();
		}
		return "";
	}
	/**
	 * @return  ???????????????????????????IP??????
	 */
	public String getClientIP() {    
		String ip = "";
		ip = super.getHeader("X-Forwarded-For");
		if(isEmptyOrNull(ip)|| "unknown".equalsIgnoreCase(ip)){
			ip = super.getHeader("Proxy-Client-IP");
		}
		if(isEmptyOrNull(ip)|| "unknown".equalsIgnoreCase(ip)){
			ip = super.getHeader("WL-Proxy-Client-IP");
		}
		if(isEmptyOrNull(ip)|| "unknown".equalsIgnoreCase(ip)){
			ip = super.getRemoteAddr();
		}
		return ip;
    }
	/**
	 * ????????????header??????User-Agent???
	 * @return
	 */
	public String getUserAgent(){
		return getHeader("User-Agent");
	}
	private boolean isEmptyOrNull(String source){
		if (source != null && !source.isEmpty())
	    {
	        return (source.trim().length() == 0);
	    }
	    return true;
	}
}
