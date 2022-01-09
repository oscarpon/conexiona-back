package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.Products;
import com.opbaquero.conexionaback.models.entity.WareHouseProduct;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.service.dto.WarehouseProductDTO;
import com.opbaquero.conexionaback.models.service.impl.WareHouseServiceImpl;
import com.opbaquero.conexionaback.models.service.interfaces.IProductService;
import com.opbaquero.conexionaback.models.service.interfaces.IWareHouseProductService;
import com.opbaquero.conexionaback.models.service.interfaces.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/warehouse-product")
public class WharehouseProductRestController {

    @Autowired
    private IWareHouseProductService wareHouseProductService;

    @Autowired
    private IWareHouseService wareHouseService;

    @Autowired
    private IProductService productService;

    @GetMapping("/{wareHouseProductId}")
    public WareHouseProduct getWareHouseProduct(@PathVariable(value = "wareHouseProductId") UUID wareHouseProductId){
        return wareHouseProductService.findOne(wareHouseProductId);
    }

    @PostMapping("/add/{wareHouseId}")
    public ResponseEntity addWarehousProduct(@PathVariable (value = "wareHouseId") UUID wareHouseId, @RequestBody WarehouseProductDTO warehouseProductDTO){
        Map<String, Object> response = new HashMap<>();
        try{
            WareHouseProduct wareHouseProduct = new WareHouseProduct();
            Products product1 = this.productService.findOne(warehouseProductDTO.getProduct());
            Warehouse warehouse1 = this.wareHouseService.findOne(warehouseProductDTO.getWarehouse());
            wareHouseProduct.setProducts(product1);
            wareHouseProduct.setWarehouse(warehouse1);
            wareHouseProduct.setStock(warehouseProductDTO.getStock());
            wareHouseProductService.save(wareHouseProduct);
        }catch (DataAccessException e){
            response.put("Error", "You can't add a product");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Message", "Product added");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
