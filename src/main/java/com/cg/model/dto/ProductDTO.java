package com.cg.model.dto;

import com.cg.model.BaseEntity;
import com.cg.model.Category;
import com.cg.model.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class ProductDTO  {

    private Long id;


    private String productName;

    private CategoryDTO category;

    private int amountProduct;

    private BigDecimal priceProduct;

    private String image;
//    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Aisa/Ho Chi Minh")
//
//    private Date createdAt;
//    @JsonFormat(pattern = "HH:mm - dd/MM/yyyy", timezone = "Aisa/Ho Chi Minh")
//
//    private Date updatedAt;

    public ProductDTO(Long id,  String productName, Category category, int amountProduct, BigDecimal priceProduct, String image) {
        this.id = id;
        this.productName = productName;
        this.category = category.toCategoryDTO();
        this.amountProduct = amountProduct;
        this.priceProduct =priceProduct;
        this.image = image;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
    }

    public Product toProduct(){
        return new Product()
                .setId(id)
                .setProductName(productName)
                .setCategory(category.toCategory())
                .setAmountProduct(amountProduct)
                .setPriceProduct(priceProduct)
                .setImage(image)
                ;
    }
}
