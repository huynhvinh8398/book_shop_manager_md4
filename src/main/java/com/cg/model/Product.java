package com.cg.model;


import com.cg.model.dto.ProductDTO;
//import com.cg.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "products")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "product_name")
    private String productName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "amount_product")
    private int amountProduct;

    @Column(name = "price_product")
    private BigDecimal priceProduct;

    @Column(name ="images")
    private String image;

    public ProductDTO toProductDTO() {
        return new ProductDTO()
                .setId(id)
                .setProductName(productName)
                .setCategory(category.toCategoryDTO())
                .setAmountProduct(String.valueOf(amountProduct))
                .setPriceProduct(String.valueOf(priceProduct))
                .setImage(image)
                ;


    }
}

