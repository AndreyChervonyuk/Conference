package com.edu.chdtu.conference.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySources({
        @PropertySource("classpath:app.properties"),
})
public class JavaMailConfig {

    @Autowired
    Environment environment;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(environment.getProperty("mail.host"));
        javaMailSender.setPort(Integer.parseInt(environment.getProperty("mail.port")));
        javaMailSender.setPassword(environment.getProperty("mail.password"));
        javaMailSender.setUsername(environment.getProperty("mail.username"));
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", environment.getProperty("mail.transport.protocol"));
        properties.setProperty("mail.smtp.auth",environment.getProperty("mail.smtp.auth"));
        properties.setProperty("mail.smtp.starttls.enable", environment.getProperty("mail.smtp.starttls.enable"));
        properties.setProperty("mail.debug", environment.getProperty("mail.debug"));
        return properties;
    }
}
