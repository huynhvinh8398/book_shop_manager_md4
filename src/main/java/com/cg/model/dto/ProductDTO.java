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
import org.hibernate.validator.constraints.Length;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class ProductDTO  {

    private Long id;

    @NotEmpty(message = "Tên sách không được để trống")
    @Size(min = 5,max = 30, message = "Tên sách từ 5 đến 30 kí tự")
    private String productName;

    @Valid
     private CategoryDTO category;

    @NotEmpty(message = "Số lượng sách không được để trống")
    @Pattern(regexp = "^[0-9]+$", message = "số lượng chỉ nhập số ")
    private String amountProduct;

    @NotEmpty(message = "Giá sách không được để trống")
    @Pattern(regexp = "^[0-9]+$", message = "Giá chỉ nhập số")
    @Length(max = 6,message = "giá tiền vượt quá giá hạn 999999")
    private String priceProduct;

//    @Size(max = 10000, message = "Đường dẫn ảnh quá dài vượt quá 10000 kí tự!")
    @NotBlank(message = "Đường dẫn ảnh không được để trống")
    private String image;

    public ProductDTO(Long id,  String productName, Category category, int amountProduct, BigDecimal priceProduct, String image) {
        this.id = id;
        this.productName = productName;
        this.category = category.toCategoryDTO();
        this.amountProduct = String.valueOf(amountProduct);
        this.priceProduct =String.valueOf(priceProduct);
        this.image = image;
    }

    public Product toProduct(){
        return new Product()
                .setId(id)
                .setProductName(productName)
                .setCategory(category.toCategory())
                .setAmountProduct(Integer.parseInt(amountProduct))
                .setPriceProduct(new BigDecimal(priceProduct))
                .setImage(image)
                ;
    }
}
