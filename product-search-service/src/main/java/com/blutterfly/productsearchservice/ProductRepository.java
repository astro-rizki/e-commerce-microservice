package com.blutterfly.productsearchservice;

import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;

public interface ProductRepository extends ReactiveElasticsearchRepository<Product, String> {
    
}