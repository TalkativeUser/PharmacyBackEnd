package com.ecommerce.pharmacy.Controller;

import com.ecommerce.pharmacy.DTO.ProductDTO;
import com.ecommerce.pharmacy.Entity.Product;
import com.ecommerce.pharmacy.service.ProductService;
import com.ecommerce.pharmacy.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
//@CrossOrigin("http://localhost:4200")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public Product findProductById(@RequestParam("id") Long id)throws Exception{
        return productService.findProductById(id);
    }

    @GetMapping("/category")
    public List<Product> getProductByCategoryId(@RequestParam("category_id") Long categoryId) throws Exception {
        return productService.getProductByCategoryId(categoryId);
    }
    @GetMapping("/sort")
    public Page<Product> findProductWithPaginationAndSort(@RequestParam("offset") int offset,
                                                          @RequestParam("field")String field ){
        return productService.findProductsWithPaginationAndSorting(offset,field);
    }

    @PostMapping("/secure/create")
    public void addNewProduct(@RequestBody ProductDTO productDTO){



//                              @RequestHeader(value = "Authorization") String token
//    ) throws Exception{
//        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
//        if (admin != null && admin.equals("0oaay7q4bqrtzxXOk5d7")) {
//            admin = "admin";
//        }
//        if (admin == null || !admin.equals("admin")) {
//            throw new Exception("Administrator only");
//        }
        productService.postProduct(productDTO);
    }

    @PostMapping("/secure/products-list")
    public void addProductsList(@RequestBody List<ProductDTO> productDTOList) {
        productService.postListOfProducts(productDTOList);
    }

    @PutMapping("/secure/update")
    public Product updateProduct (@RequestParam("product_id") Long id,
                                  @RequestBody Product product,
                                  @RequestHeader(value = "Authorization") String token
    ) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin != null && admin.equals("0oaay7q4bqrtzxXOk5d7")) {
            admin = "admin";
        }
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/secure/delete")
    public void removeProduct (@RequestParam("product_id") Long id,
                               @RequestHeader(value = "Authorization") String token
    ) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin != null && admin.equals("0oaay7q4bqrtzxXOk5d7")) {
            admin = "admin";
        }
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        productService.deleteProductById(id);
    }
}
