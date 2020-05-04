package com.foxminded.university.config;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootConfiguration
public class DataConfiguration {

    @Autowired
    private Environment environment;

    private static final String driver = "org.postgresql.Driver";
    private static final String datasourceName = "jdbc/university";
    private static final String datasourceFactory = "org.apache.tomcat.jdbc.pool.DataSourceFactory";

    @Bean
    public TomcatServletWebServerFactory tomcatFactory() {
        return new TomcatServletWebServerFactory() {
            @Override
            protected TomcatWebServer getTomcatWebServer(org.apache.catalina.startup.Tomcat tomcat) {
                tomcat.enableNaming();
                return super.getTomcatWebServer(tomcat);
            }

            @Override
            protected void postProcessContext(Context context) {
                ContextResource resource = new ContextResource();
                resource.setProperty("factory", datasourceFactory);
                resource.setName(datasourceName);
                resource.setType(DataSource.class.getName());
                resource.setProperty("driverClassName", driver);
                resource.setProperty("url", environment.getProperty("spring.datasource.url"));
                resource.setProperty("username", environment.getProperty("spring.datasource.user"));
                resource.setProperty("password", environment.getProperty("spring.datasource.password"));
                context.getNamingResources()
                        .addResource(resource);
            }
        };
    }

    @Bean
    public DataSource dataSource() throws NamingException {

        return (DataSource) new JndiTemplate().lookup(environment.getProperty("spring.datasource.name.context"));
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("com.foxminded.university");
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }

}
