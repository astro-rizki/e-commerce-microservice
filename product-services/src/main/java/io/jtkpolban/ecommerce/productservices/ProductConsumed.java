package io.jtkpolban.ecommerce.productservices;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductConsumed {

    private String id;
    private String name;
    private String description;
    private float price;
    private String currency;
    private String image;
    private String url;

    public ProductConsumed(Product product) {
        this.id = product.get_id().toHexString();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.currency = product.getCurrency();
        this.image = product.getImage();
        this.url = product.getUrl();
    }
    
//    @Field("published") //gunakan untuk different field name
//    private LocalDate publicationDate;


}
