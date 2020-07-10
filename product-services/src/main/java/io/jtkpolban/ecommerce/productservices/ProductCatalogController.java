package io.jtkpolban.ecommerce.productservices;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.messaging.support.MessageBuilder;

@RestController
public class ProductCatalogController {

    @Autowired
    ProductRepository productRepository;
    
    private Producer producer;

    public ProductCatalogController(Producer producer){
        super();
        this.producer = producer;
    }

    @RequestMapping("/")
    public @ResponseBody
    String index() {
        return "<h1>Welcome to Product Catalog API!</h1><br/>use //products//list to list";
    }

    //ambil semua
    @GetMapping("/products/list")
    public @ResponseBody
    Flux<Product> productList() {
        return this.productRepository.findAll();
    }

    //ambil satu by id
    @GetMapping("/products/")
    public @ResponseBody
    Mono<Product> getOneProductById(@RequestParam String id) {
        ObjectId oid = new ObjectId(id);
        return this.productRepository.findById(oid);
    }

    // tambah, need product, id kosongkan!
    @PostMapping("/products/")
    public @ResponseBody
    Mono<Void> insertProduct(@RequestBody Product product){
        producer.getSource()
                .output()
                .send(MessageBuilder.withPayload(new ProductConsumed(product))
                    .setHeader("type","POST")
                    .build());
        return this.productRepository.save(product).then();
    }

    // hapus, by id
    @DeleteMapping("/products")
    public @ResponseBody
    Mono<Void> deleteProduct(@RequestParam String id){
        ObjectId oid = new ObjectId(id);
        producer.getSource()
                .output()
                .send(MessageBuilder.withPayload(id)
                    .setHeader("type","DELETE")
                    .build());
        return this.productRepository.deleteById(oid);
    }

    //update product, by id, product body kosongkan id!
    @PutMapping("/products")
    public @ResponseBody
    Mono<Void> updateProduct(@RequestParam String id, @RequestBody Product product){
        product.set_id(new ObjectId(id));
        producer.getSource()
                .output()
                .send(MessageBuilder.withPayload(new ProductConsumed(product))
                    .setHeader("type","PUT")
                    .build());
        return this.productRepository.save(product).then();
    }

    //default rest error handling
//    @ExceptionHandler(Exception.class)
//    void handleExceptions(HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error. We will be addressing this issue soon.");
//    }
}
