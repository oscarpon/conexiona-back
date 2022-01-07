package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.entity.Products;
import com.opbaquero.conexionaback.models.service.interfaces.IAccountService;
import com.opbaquero.conexionaback.models.service.interfaces.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IProductService productService;

    @GetMapping("/all")
    public List<Products> all(){
        return productService.findAll();
    }

    @GetMapping("/all/{accountId}")
    public List<Products> listByAccount(@PathVariable (value = "accountId")Account account){
        return productService.findByAccount(account);
    }

    @GetMapping("/{productId}")
    public Products getProduct(@PathVariable (value = "productId") UUID productId){
        return productService.findOne(productId);
    }

    @PostMapping("/add/{accountId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@PathVariable (value = "accountId")UUID accountId, @RequestBody Products products){
        Account account = accountService.findOne(accountId);
        Map<String, Object> response = new HashMap<>();
        try{
            products.setAccount(account);
            productService.save(products);
        }catch (DataAccessException e){
            response.put("Error", "You can't add products");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Message", "Product created");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/add/{productId}")
    public ResponseEntity<?> delete(@PathVariable(value = "productId") UUID productId){
        Map<String, Object> response = new HashMap<>();
        try{
            productService.delete(productId);
        }catch (DataAccessException e){
            response.put("message", "Error deleting product from database");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Product deleted");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/add/{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody Products products, @PathVariable (value = "productId") UUID productId){
        Products currentProduct = productService.findOne(productId);
        Products updateProduct = null;
        Map<String, Object> response = new HashMap<>();
        if(currentProduct == null){
            response.put("message", "Product not found in database");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            currentProduct.setNameProduct(products.getNameProduct());
            updateProduct = productService.save(currentProduct);
        }catch (DataAccessException e){
            response.put("message", "Error updating product");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Product updated succesfully");
        response.put("product", updateProduct);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

}
