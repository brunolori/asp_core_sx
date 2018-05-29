package al.gov.asp.brunoveizaj.casper.config;

import java.util.HashMap;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "timsEntityManagerFactory", 
					   transactionManagerRef = "timsTransactionManager", basePackages = {"al.gov.asp.casper.tims.dao" })
public class TimsDbConfig {

	@Bean(name = "timsDataSource")
	//@ConfigurationProperties(prefix = "spring.tims.datasource")
	public DataSource dataSource() {
		
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@//db-oracle-scan.asp.gov.al:1521/tims");
		ds.setPassword("VEIZAJB");
		ds.setUsername("VEIZAJB");
		
		
		return ds;
		
		//return DataSourceBuilder.create().build();
	}

	@Bean(name = "timsEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean timsEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("timsDataSource") DataSource dataSource) {
		
		/*LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("al.gov.asp.brunoveizaj.casper.tims.entities");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		*/
		HashMap<String, Object> props = new HashMap<>();
		props.put("hibernate.dialect", "");
		
		//em.setJpaPropertyMap(props);
		//return em;
		
		
		return builder.dataSource(dataSource).properties(props).packages("al.gov.asp.brunoveizaj.casper.tims.entities").persistenceUnit("tims").build();
	}

	@Bean(name = "timsTransactionManager")
	public PlatformTransactionManager timsTransactionManager(
			@Qualifier("timsEntityManagerFactory") EntityManagerFactory timsEntityManagerFactory) {
		return new JpaTransactionManager(timsEntityManagerFactory);
	}
}