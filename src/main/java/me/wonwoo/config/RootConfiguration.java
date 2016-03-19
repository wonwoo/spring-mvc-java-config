package me.wonwoo.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by wonwoo on 2016. 3. 19..
 */

@Configuration
@EnableJpaRepositories(basePackages = "me.wonwoo.account")
@EnableTransactionManagement
public class RootConfiguration {

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
    EmbeddedDatabase db = builder
      .setType(EmbeddedDatabaseType.H2)
      .build();
    return db;
  }

  @Bean
  public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
    return entityManagerFactory.createEntityManager();
  }

  @Bean
  public FactoryBean<EntityManagerFactory> entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    containerEntityManagerFactoryBean.setDataSource(dataSource());
    JpaVendorAdapter adaptor = new HibernateJpaVendorAdapter();
    containerEntityManagerFactoryBean.setJpaVendorAdapter(adaptor);
    containerEntityManagerFactoryBean.setPackagesToScan("me.wonwoo.account");
    Properties props = new Properties();
    props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    props.setProperty("hibernate.show_sql", "true");
    props.setProperty("hibernate.hbm2ddl.auto", "create");
    containerEntityManagerFactoryBean.setJpaProperties(props);
    return containerEntityManagerFactoryBean;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
    jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
    return jpaTransactionManager;
  }
}
