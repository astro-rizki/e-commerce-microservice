package com.blutterfly.productsearchservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class ProductSearchController {
    
    @Autowired
    ProductRepository productRepository;

    @GetMapping("")
    public @ResponseBody String index() {
        log.info("Ko ko da yo!");
        return "Not here!";
    }

    public ProductSearchController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    //ambil semua
    @GetMapping("/products/list")
    public @ResponseBody
    Flux<Product> productList() {
        log.info("Wanna get all of our products? Here you go.");
        return this.productRepository.findAll();
    }

    //ambil satu by id
    @GetMapping("/products")
    public @ResponseBody
    Mono<Product> getOneProductById(@RequestParam String id) {
        log.info("You know what you want to buy yeah.");
        return this.productRepository.findById(id);
    }

    // tambah, need product, id kosongkan!
    @PostMapping("/products")
    public @ResponseBody
    Mono<Void> insertProduct(@RequestBody Product product){
        log.info("This new product is pretty good.");
        return this.productRepository.save(product).then();
    }

    // hapus, by id
    @DeleteMapping("/products")
    public @ResponseBody
    Mono<Void> deleteProduct(@RequestParam String id){
        log.info("Why did you delete this precious thing?");
        return this.productRepository.deleteById(id);
    }

    //update product, by id, product body kosongkan id!
    @PutMapping("/products")
    public @ResponseBody
    Mono<Void> updateProduct(@RequestParam String id, @RequestBody Product product){
        log.info("Changed your mind?");
        product.setId(id);
        return this.productRepository.save(product).then();
    }

}
