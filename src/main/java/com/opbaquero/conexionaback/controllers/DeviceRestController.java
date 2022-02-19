package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.Device;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.service.interfaces.IDeviceService;
import com.opbaquero.conexionaback.models.service.interfaces.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/device")
public class DeviceRestController {

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IWareHouseService wareHouseService;

    @PostMapping("/add/{warehouseId}")
    public ResponseEntity<?> createDevice(@PathVariable(value = "warehouseId") UUID warehouseId, @RequestBody Device device){
        Warehouse warehouse = wareHouseService.findOne(warehouseId);
        Map<String,Object> response = new HashMap<>();
        try{
            device.setWarehouse(warehouse);
            deviceService.save(device);
        }catch (DataAccessException e){
            response.put("Error", "You can't add device");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Message", "Device created");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
