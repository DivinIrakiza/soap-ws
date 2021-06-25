package com.rca.spring.exam.soapws.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<MessageDispatcherServlet>(messageDispatcherServlet, "/ws/divinirakiza/*");
    }

    @Bean(name = "suppliers")
    public DefaultWsdl11Definition suppliersWsdl(XsdSchema suppliersSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("SuppliersPort");
        definition.setTargetNamespace("com.rca.spring.exam/divinirakiza/soapws/suppliers");
        definition.setLocationUri("/ws/divinirakiza/");
        definition.setSchema(suppliersSchema);
        return definition;
    }
    @Bean(name = "items")
    public DefaultWsdl11Definition itemsWsdl(XsdSchema itemsSchema) {
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setPortTypeName("ItemsPort");
        definition.setTargetNamespace("com.rca.spring.exam/divinirakiza/soapws/items");
        definition.setLocationUri("/ws/divinirakiza/");
        definition.setSchema(itemsSchema);
        return definition;
    }

    @Bean
    public XsdSchema suppliersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("suppliers.xsd"));
    }

    @Bean
    public  XsdSchema itemsSchema(){
        return new SimpleXsdSchema(new ClassPathResource("items.xsd"));
    }
}