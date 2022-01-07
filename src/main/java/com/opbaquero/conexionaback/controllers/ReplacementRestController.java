package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.*;
import com.opbaquero.conexionaback.models.service.interfaces.IProductService;
import com.opbaquero.conexionaback.models.service.interfaces.IReplacementService;
import com.opbaquero.conexionaback.models.service.interfaces.IWareHouseProductService;
import com.opbaquero.conexionaback.models.service.interfaces.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/replacement")
public class ReplacementRestController {

    @Autowired
    private IReplacementService replacementService;

    @Autowired
    private IWareHouseService wareHouseService;

    @Autowired
    private IWareHouseProductService wareHouseProductService;

    @Autowired
    private IProductService productService;

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable(value = "id")UUID id){
        Replacement replacement = replacementService.fetchReplacementByIdWithUserWithReplacementItemWithProducts(id);
        Map<String, Object> response = new HashMap<>();
        if(replacement == null){
            response.put("error", "Replace not found");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        try{

        }catch (DataAccessException e){
            response.put("error", "Imposible access");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Message", "Access succesfull");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid Replacement replacement,
                                 @RequestParam(value = "productId[]", required = false) UUID[] productId,
                                 @RequestParam(value = "cantidad[]", required = false) Integer[] cantidad){
        Map<String, Object> response = new HashMap<>();
        try{
            for(int i = 0; i < productId.length; i++){
                WareHouseProduct producto = wareHouseProductService.findOne(productId[i]);
                ReplacementItem replacementItem = new ReplacementItem();
                replacementItem.setProduct(producto);
                replacementItem.setQuantityReplaced(cantidad[i]);
                replacement.addItemReplacement(replacementItem);
            }

        }catch (DataAccessException e){
            response.put("Error", "You can't create replacement");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Message", "Replacement Created");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }





}
