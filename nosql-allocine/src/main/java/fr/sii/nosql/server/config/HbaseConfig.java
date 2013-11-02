package fr.sii.nosql.server.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.hadoop.hbase.HbaseConfigurationFactoryBean;

@Configuration
@PropertySource("application.properties")
@Profile("hbase")
public class HbaseConfig {

	@Bean
	public HbaseConfigurationFactoryBean hbaseConfigurationFactory() {
		Properties properties = new Properties();
		properties.put("hbase.zookeeper.quorum", "192.168.1.91");
		properties.put("property.clientPort", "2181");

		HbaseConfigurationFactoryBean hbaseConfigurationFactory = new HbaseConfigurationFactoryBean();
		hbaseConfigurationFactory.setProperties(properties);
		return hbaseConfigurationFactory;
	}
}
