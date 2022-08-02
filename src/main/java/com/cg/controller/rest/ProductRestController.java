package com.cg.controller.rest;


import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Category;
import com.cg.model.Product;
import com.cg.model.dto.CategoryDTO;
import com.cg.model.dto.ProductDTO;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private AppUtil appUtils;

    @GetMapping
    public ResponseEntity<?> getAllProducts() {

        Iterable<ProductDTO> productDTOS = productService.findAllProductDTO();


        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if (productOptional.isPresent()){
            throw new ResourceNotFoundException("Id không hợp lệ");
        }
        return new ResponseEntity<>(productOptional.get().toProductDTO(),HttpStatus.OK);
    }
//    @GetMapping("/category")
//    public ResponseEntity<?> getCategory() {
//
//        List<CategoryDTO> categoryDTOS = categoryService.findAllCategoryDTO();
//        if (categoryDTOS == null) {
//            return new ResponseEntity<>("Danh sách trống!", HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
//    }

    @PostMapping("/create")
    public ResponseEntity<?> doCreate(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }


        productDTO.setId(0L);
//        productDTO.setAmountProduct(0);
//        productDTO.setPriceProduct(new BigDecimal(0L));

//        productDTO.getCategory().setId(0L);

        Product newProduct = productService.save(productDTO.toProduct());

        return new ResponseEntity<>(newProduct.toProductDTO(), HttpStatus.CREATED);
    }

}
