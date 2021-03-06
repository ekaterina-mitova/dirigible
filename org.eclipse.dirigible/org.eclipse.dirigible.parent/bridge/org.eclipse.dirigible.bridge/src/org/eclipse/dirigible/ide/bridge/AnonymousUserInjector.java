package org.eclipse.dirigible.ide.bridge;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnonymousUserInjector implements IInjector {
	
	private static final Logger logger = LoggerFactory.getLogger(AnonymousUserInjector.class);
	
	private static final String COOKIE_ANONYMOUS_USER = "dirigible_anonymous_user";

	@Override
	public void injectOnRequest(ServletConfig servletConfig,
			HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Cookie[] cookies = req.getCookies();
		String cookieName = COOKIE_ANONYMOUS_USER;
		String defaultValue = "guest";
		String value = "";
		for ( int i=0; i<cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName())) {
				value = cookie.getValue();
				logger.debug("Dirigible's anonymous user cookie found: " + value);
				break;
			}
		  	value = defaultValue;
		}
		
		req.getSession().setAttribute(COOKIE_ANONYMOUS_USER, value);
	}

	@Override
	public void injectOnStart(ServletConfig servletConfig)
			throws ServletException, IOException {
		//		
	}

}
