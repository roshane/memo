package ro.ducati.conf;

import org.apache.derby.database.Database;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author roshanep@hsenidmobile.com
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:/application.properties"})
@EnableJpaRepositories(basePackages = {"ro.ducati.repo"})
public class Infrastructure {

    private
    @Value("${dataSource.driverClassName}")
    String driver;
    private
    @Value("${db.location}")
    String dbUrl;
    private
    @Value("${db.username}")
    String username;
    private
    @Value("${db.password}")
    String password;
    private
    @Value("${hibernate.hbm2ddl.auto}")
    String hbm2ddl;


    @Bean(name = {"dataSource"})
    public DataSource dataSource() {
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName(driver);
        source.setUrl(dbUrl);
        source.setUsername(username);
        source.setPassword(password);
        return source;
    }

    @Bean(name = {"entityManagerFactory"})
    public AbstractEntityManagerFactoryBean entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("ro.ducati.entity");
        factoryBean.setJpaPropertyMap(getJpaPropertyMap());
        factoryBean.setJpaVendorAdapter(vendorAdapter());
        return factoryBean;
    }

    private Map<String, Object> getJpaPropertyMap() {
        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put(AvailableSettings.DIALECT,"org.hibernate.dialect.DerbyTenSevenDialect");
        jpaProperties.put(AvailableSettings.AUTOCOMMIT,true);
        jpaProperties.put(AvailableSettings.FORMAT_SQL, true);
        return jpaProperties;
    }

    @Bean
    public HibernateJpaVendorAdapter vendorAdapter(){
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        vendorAdapter.setDatabase(org.springframework.orm.jpa.vendor.Database.DERBY);
        return vendorAdapter;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
