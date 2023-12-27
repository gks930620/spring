package com.study.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class CookieUtils {
	
	private Map<String,Cookie> cookieMap=new HashMap<String, Cookie>();
	
	public CookieUtils(HttpServletRequest request) { //생성될 때 그 request의 모든 쿠키가 cookieMap에 담김
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
	}
	
	public boolean exists(String name) { 
		return cookieMap.get(name)!=null; 
	}
	
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}  
	
	public String getValue(String name) throws IOException{
		return exists(name)  ? URLDecoder.decode(getCookie(name).getValue(),"utf-8") : null;
	}
	
	public static Cookie createCookie(String name, String value) throws IOException {
		return createCookie(name, value, "", "", -1);
	}
	
	public static Cookie createCookie(String name,String value,String path) throws IOException{
		return createCookie(name,value,"",path,-1);
	}
	
	
	public static Cookie createCookie(String name,String value,int maxAge) throws IOException{
		return createCookie(name,value,"","",maxAge);
	}
	
	public static Cookie createCookie(String name, String value, String path, int maxAge) throws IOException {
		return createCookie(name, value, "", path, maxAge);
	}
	
	public static Cookie createCookie(String name, String value, String domain, String path, int maxAge) throws IOException {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setDomain(domain);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		return cookie;
	}
	//생성자에 파라미터가 여러개일 때
	//위에서처럼 하나의 메소드에 일을 다 집중시키는  방식
	// Builder패턴   내부 스태틱 Builder클래스만들고 어쩌구저쩌구.  Cookie.builder.name().value().maxAge().build();

}
