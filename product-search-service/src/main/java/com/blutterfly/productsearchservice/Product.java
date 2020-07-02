package com.blutterfly.productsearchservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private float price;
    private String currency;
    private String image;
    private String url;
}
