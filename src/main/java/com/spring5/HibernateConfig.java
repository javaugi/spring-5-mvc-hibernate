package com.spring5;

import com.spring5.model.Contact;
import com.spring5.model.ContactNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.spring5.model.Product;
import com.spring5.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.spring5")
public class HibernateConfig {
    //might be renaed as MyApplication. the main method call to match @SpringBootApplication
    // the @EnableTransactionManagement to match @EnableJpaRepositories

    private static final Logger LOG = LoggerFactory.getLogger(HibernateConfig.class);

    public static void main(String[] args) {
        SpringApplication.run(HibernateConfig.class, args);
    }

    @Autowired
    private ApplicationContext context;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
        Class<?>[] annonClasses = {Product.class, User.class, Contact.class, ContactNote.class};
        factoryBean.setAnnotatedClasses(annonClasses);
        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

}
