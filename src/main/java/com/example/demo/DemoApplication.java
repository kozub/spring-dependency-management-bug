package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Bean
    @Primary
    DataSource dataSource(@Qualifier("firstDS") DataSource firstDS,
                          @Qualifier("secondDS") DataSource secondDS) {
        MyRoutingDataSource ds = new MyRoutingDataSource();
        ds.setCurrentDS(firstDS);
        return ds;
    }

    @Bean("firstDS")
    public DataSource firstDS(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean("secondDS")
    public DataSource secondDs(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    class MyRoutingDataSource extends AbstractRoutingDataSource {
        private DataSource currentDS;

        public void setCurrentDS(DataSource currentDS) {
            this.currentDS = currentDS;
        }

        @Override
        protected Object determineCurrentLookupKey() {
            return currentDS;
        }
    }
}
