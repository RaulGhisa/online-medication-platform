package com.example.springdemo;

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

@Configuration
@EnableWs
public class SoapConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServletServletRegistrationBean(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean<>(servlet, "/soapWs/*");
    }

    @Bean(name = "activity")
    public DefaultWsdl11Definition defaultWsdl11DefinitionActivity(XsdSchema schemaActivity) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("DoctorSoapServicePort");
        defaultWsdl11Definition.setLocationUri("/soapWs");
        defaultWsdl11Definition.setTargetNamespace("activity");
        defaultWsdl11Definition.setSchema(schemaActivity);

        return defaultWsdl11Definition;
    }

    @Bean(name = "medication")
    public DefaultWsdl11Definition defaultWsdl11DefinitionMedication(XsdSchema schemaMedication) {
        DefaultWsdl11Definition defaultWsdl11Definition = new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("DoctorSoapServicePort");
        defaultWsdl11Definition.setLocationUri("/soapWs");
        defaultWsdl11Definition.setTargetNamespace("medication");
        defaultWsdl11Definition.setSchema(schemaMedication);

        return defaultWsdl11Definition;
    }

    @Bean(name = "schemaActivity")
    public XsdSchema schemaActivity() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/activity.xsd"));
    }

    @Bean(name = "schemaMedication")
    public XsdSchema schemaMedication() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/medication.xsd"));
    }

}
