package al.gov.asp.brunoveizaj.casper.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "timsEntityManagerFactory", transactionManagerRef = "timsTransactionManager", basePackages = {
		"al.gov.asp.casper.tims.dao" })
public class TimsDbConfig {

	@Bean(name = "timsDataSource")
	@ConfigurationProperties(prefix = "spring.tims.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "timsEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean timsEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("timsDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("al.gov.asp.casper.tims.entities").persistenceUnit("tims")
				.build();
	}

	@Bean(name = "timsTransactionManager")
	public PlatformTransactionManager timsTransactionManager(
			@Qualifier("timsEntityManagerFactory") EntityManagerFactory timsEntityManagerFactory) {
		return new JpaTransactionManager(timsEntityManagerFactory);
	}
}