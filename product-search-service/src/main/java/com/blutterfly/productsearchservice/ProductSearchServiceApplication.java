package com.blutterfly.productsearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableReactiveElasticsearchRepositories
public class ProductSearchServiceApplication extends AbstractReactiveElasticsearchConfiguration {

	public static void main(String[] args) {
		SpringApplication.run(ProductSearchServiceApplication.class, args);
	}

	@Override
	public ReactiveElasticsearchClient reactiveElasticsearchClient() {
	  	return ReactiveRestClients.create(ClientConfiguration.create("elasticsearch:9200"));
	}

}
