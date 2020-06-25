package io.jtkpolban.ecommerce.productservices;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@Data
@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private ObjectId _id;
    private String name;
    private String description;
    private float price;
    private String currency;
    private String image;
    private String url;
    
//    @Field("published") //gunakan untuk different field name
//    private LocalDate publicationDate;


}
