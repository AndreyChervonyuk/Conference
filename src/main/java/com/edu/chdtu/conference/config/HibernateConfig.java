package com.edu.chdtu.conference.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.edu.chdtu.conference.*"})
@PropertySources({
        @PropertySource("classpath:app.properties")

})
public class HibernateConfig {

    @Autowired
    Environment environment;

    @Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder
                .scanPackages("com.edu.chdtu.conference.model")
                .addProperties(getHibernateProperties());
        return builder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties prop = new Properties();

        prop.put("hibernate.format_sql", environment.getProperty("hibernate.format_sql"));
        prop.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));
        prop.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        prop.put("hibernate.connection.driver_class", environment.getProperty("hibernate.connection.driver_class"));
        prop.put("hibernate.hbm2ddl.auto", environment.getProperty("hibernate.hbm2ddl.auto"));
        prop.put("current_session_context_class", environment.getProperty("current_session_context_class"));
        prop.put("hibernate.enable_lazy_load_no_trans", environment.getProperty("hibernate.enable_lazy_load_no_trans"));

        return prop;
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                environment.getProperty("jdbc.url"),
                environment.getProperty("jdbc.username"),
                environment.getProperty("jdbc.password")
        );

        dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
        return dataSource;
    }

    @Bean
    public HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }
}
