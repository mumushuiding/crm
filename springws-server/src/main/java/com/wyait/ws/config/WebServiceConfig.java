package com.wyait.ws.config;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter{
	@Bean
	public ServletRegistrationBean<Servlet> messageDispatcherServlet(
			ApplicationContext applicationContext){
		MessageDispatcherServlet servlet=new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<Servlet>(servlet, "/ws/*");
	}
	@Bean(name="countries")
	public DefaultWsdl11Definition defaultWsdl11Definition(
			XsdSchema cSchema){
		DefaultWsdl11Definition wsdl11Definition=new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CountriesPort");
		
		wsdl11Definition.setSchema(cSchema);
		return wsdl11Definition;
	}
	@Bean 
	public XsdSchema cSchema(){
		return new SimpleXsdSchema(new ClassPathResource("/schema/countries.xsd"));
	}
	
}
