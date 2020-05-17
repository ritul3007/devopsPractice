package com.vaadin.tutorial.crm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.vaadin.flow.server.VaadinServlet;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ServletRegistrationBean<VaadinServlet> frontendServletBean() {
		ServletRegistrationBean<VaadinServlet> bean = new ServletRegistrationBean<>(new VaadinServlet() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void service(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
				if (!serveStaticOrWebJarRequest(req, resp)) {
					resp.sendError(404);
				}
			}
		}, "/frontend/*");
		bean.setLoadOnStartup(1);
		return bean;
	}

}
