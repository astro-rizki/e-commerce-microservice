package io.jtkpolban.ecommerce.productservices;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@EnableBinding(Source.class)
public class Producer {
    
    private Source src;

    public Producer(Source src){
        super();
        this.setSource(src);
    }

    public Source getSource(){
        return this.src;
    }

    public void setSource(Source src){
        this.src = src;
    }
}