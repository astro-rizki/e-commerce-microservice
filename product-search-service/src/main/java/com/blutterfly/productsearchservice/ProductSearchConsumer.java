package com.blutterfly.productsearchservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableBinding(Sink.class)
public class ProductSearchConsumer {
    
    @Autowired
    ProductRepository productRepository;
    
    // tambah, need product, id kosongkan!
    @StreamListener(target = Sink.INPUT, condition = "headers['type']=='POST'")
    public void insertProduct(@Payload Product product) {
        log.info("This new product is pretty good.");
        this.productRepository.save(product).then().block();
    }

    // hapus, by id
    @StreamListener(target = Sink.INPUT, condition = "headers['type']=='DELETE'")
    public void deleteProduct(@Payload String id){
        log.info("Why did you delete this precious thing?");
        this.productRepository.deleteById(id).block();
    }

    //update product, by id, product body kosongkan id!
    @StreamListener(target = Sink.INPUT, condition = "headers['type']=='PUT'")
    public void updateProduct(@Payload Product product){
        log.info("Changed your mind?");
        this.productRepository.save(product).then().block();
    }

}
