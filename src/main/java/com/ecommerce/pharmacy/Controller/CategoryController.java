package com.ecommerce.pharmacy.Controller;

import com.ecommerce.pharmacy.Entity.Category;
import com.ecommerce.pharmacy.service.CategoryService;
import com.ecommerce.pharmacy.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("http://localhost:4200")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<Category> getCategoryById (@RequestParam("id") Long id) throws Exception {
        Category category = categoryService.findCategoryById(id);
        if (category == null) {
            throw new Exception("Category not found");
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping("/secure/create")
    public String createCategory(@RequestBody Category category ){
//                                ,@RequestHeader(value = "Authorization") String token) throws Exception{
//        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
//        if (admin != null && admin.equals("0oaay7q4bqrtzxXOk5d7")) {
//            admin = "admin";
//        }
//        if (admin == null || !admin.equals("admin")) {
//            throw new Exception("Administrator only");
//        }
        categoryService.createCategory(category);
        return "Success";
    }

    @PutMapping("/secure/update")
    public Category updateCategory (@RequestParam("category_id") Long id,
                                    @RequestBody Category category,
                                    @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin != null && admin.equals("0oaay7q4bqrtzxXOk5d7")) {
            admin = "admin";
        }
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        return categoryService.updateCategory(id,category);
    }

    @DeleteMapping("/secure/delete")
    public void removeCategory (@RequestParam("category_id") Long id,
                                @RequestHeader(value = "Authorization") String token) throws Exception{
        String admin = ExtractJWT.payloadJWTExtraction(token,"\"userType\"");
        if (admin != null && admin.equals("0oaay7q4bqrtzxXOk5d7")) {
            admin = "admin";
        }
        if (admin == null || !admin.equals("admin")) {
            throw new Exception("Administrator only");
        }
        categoryService.deleteCategoryById(id);
    }
}
