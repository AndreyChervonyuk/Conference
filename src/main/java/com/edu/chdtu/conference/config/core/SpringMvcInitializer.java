package com.edu.chdtu.conference.config.core;

import com.edu.chdtu.conference.config.AppConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final String LOCATION = System.getProperty("user.home");
	private static final long MAX_FILE_SIZE = 5242880;
	private static final long MAX_REQUEST_SIZE = 20971520;
	private static final int FILE_SIZE_THRESHOLD = 0;

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(getMultipartConfigElement());
	}

	private MultipartConfigElement getMultipartConfigElement() {
		return new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
	}

}