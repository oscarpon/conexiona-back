package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.WareHouseProduct;
import com.opbaquero.conexionaback.models.service.interfaces.IWareHouseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/warehouse-product")
public class WharehouseProductRestController {

    @Autowired
    private IWareHouseProductService wareHouseProductService;

    @GetMapping("/{wareHouseProductId}")
    public WareHouseProduct getWareHouseProduct(@PathVariable(value = "wareHouseProductId") UUID wareHouseProductId){
        return wareHouseProductService.findOne(wareHouseProductId);
    }

}
