package com.cg.controller.rest;


import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Category;
//import com.cg.model.Customer;
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

        List<ProductDTO> productDTOS = productService.findAllProductDTO();


        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id){
        Optional<ProductDTO> productOptional = productService.findProductDTOById(id);
        if(!productOptional.isPresent()){
            throw  new ResourceNotFoundException("Id sách không hợp lệ");
        }
        return new ResponseEntity<>(productOptional.get(),HttpStatus.OK);
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
        Optional<Category> category = categoryService.findById(productDTO.toProduct().getCategory().getId());

        if (!category.isPresent()) {
            throw new DataInputException(" Giá trị Category k hợp lệ");
        }

        productDTO.setId(0L);
      try {

          Product newProduct = productService.save(productDTO.toProduct());
          return new ResponseEntity<>(newProduct.toProductDTO(), HttpStatus.CREATED);
      } catch (Exception e){
          throw new DataInputException(" không thể thêm mới  server không thể xử lý");

      }
    }
    @PutMapping("/update/{id}")
     public ResponseEntity<?> doUpdate(@Validated @RequestBody ProductDTO productDTO, BindingResult bindingResult){

        if (bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }
            Boolean exitsById =productService.exitsByIdProduct(productDTO.getId());

        if (!exitsById){
            throw new ResourceNotFoundException("Id sản phẩm không tồn tại");
        }
        Optional<Category> category = categoryService.findById(productDTO.toProduct().getCategory().getId());

        if (!category.isPresent()) {
            throw new DataInputException(" Giá trị Category k hợp lệ");
        }
        productDTO.setId(productDTO.getId());


       try{

           Product productUpdate = productService.save(productDTO.toProduct());
           return new ResponseEntity<>(productUpdate.toProductDTO(), HttpStatus.OK);
       }catch (Exception ex){
           throw new DataInputException(" không thể cập nhật server không thể xử lý");
       }
    }

     @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> doDelete(@PathVariable Long id){

         Optional<Product> optionalProduct = productService.findById(id);

         if (optionalProduct.isPresent()) {
             productService.deleteProductSoft(optionalProduct.get());
             return new ResponseEntity<>("Xoá sản phẩm thành công", HttpStatus.OK);
         } else {
             return new ResponseEntity<>("Xoá sản phẩm thất bại", HttpStatus.BAD_REQUEST);

         }
     }

}
