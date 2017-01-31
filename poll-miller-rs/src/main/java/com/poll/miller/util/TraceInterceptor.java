package com.poll.miller.util;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

/**
 * Extends {@link CustomizableTraceInterceptor} to provide custom logging levels
 */
public class TraceInterceptor extends CustomizableTraceInterceptor {

	private static final long serialVersionUID = 1L;
	protected static Logger logger4J = Logger.getLogger("aop");

	@Override
	protected void writeToLog(Log logger, String message, Throwable ex) {
		if (ex != null) {
			System.out.println("message--"+message+" ex--"+ex);
			logger4J.debug(message, ex);
		} else {
			System.out.println("message--"+message);
			logger4J.debug(message);
		}
	}

	@Override
	protected boolean isInterceptorEnabled(MethodInvocation invocation, Log logger) {
		return true;
	}
}