package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.*;
import com.opbaquero.conexionaback.models.service.dto.ReplacementDTO;
import com.opbaquero.conexionaback.models.service.dto.ReplacementItemDTO;
import com.opbaquero.conexionaback.models.service.interfaces.*;
import com.opbaquero.conexionaback.security.entity.User;
import com.opbaquero.conexionaback.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/replacement")
public class ReplacementRestController {

    @Autowired
    private IReplacementService replacementService;

    @Autowired
    private IReplacementItemService replacementItemService;

    @Autowired
    private IWareHouseService wareHouseService;

    @Autowired
    private IWareHouseProductService wareHouseProductService;

    @Autowired
    private UserService userService;

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
    public ResponseEntity<?> add(@RequestBody ReplacementDTO reqReplacement){
        Map<String, Object> response = new HashMap<>();
        Replacement replacement = new Replacement();
        Warehouse warehouse = wareHouseService.findOne(reqReplacement.getWarehouse());
        User user = userService.findByUserName(reqReplacement.getUser());
        List<ReplacementItemDTO> listReplacements = reqReplacement.getReplacementItems();
        replacement.setUser(user);
        replacement.setWarehouse(warehouse);
        try{
            for(ReplacementItemDTO rep : listReplacements){
                WareHouseProduct wareHouseProduct = wareHouseProductService.findWareHouseProductByIds(reqReplacement.getWarehouse(), rep.getProduct());
                WareHouseProduct warehouseProductUpdated = wareHouseProduct;
                warehouseProductUpdated.setStock(wareHouseProduct.getStock() + rep.getQuantity());
                wareHouseProductService.update(warehouseProductUpdated);
                ReplacementItem replacementItem = new ReplacementItem();
                replacementItem.setProduct(wareHouseProduct);
                replacementItem.setQuantityReplaced(rep.getQuantity());
                replacement.addItemReplacement(replacementItem);
            }
            replacementService.save(replacement);
        }catch (DataAccessException e){
            response.put("error", "You can't create replacement");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Replacement Created");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }





}
