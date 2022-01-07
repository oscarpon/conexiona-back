package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.Building;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.service.interfaces.IBuildingService;
import com.opbaquero.conexionaback.models.service.interfaces.IWareHouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/warehouse")
public class WareHouseRestController {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IWareHouseService wareHouseService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Warehouse> listAll(){
        return wareHouseService.findAll();
    }

    @GetMapping("/all/{buildingId}")
    public List<Warehouse> listByBuilding(@PathVariable (value = "buildingId")Building building){
        return wareHouseService.findByBuilding(building);
    }

    @GetMapping("/{wareHouseId}")
    public Warehouse getWareHouse(@PathVariable(value = "wareHouseId") UUID wareHouseId){
        return wareHouseService.findOne(wareHouseId);
    }

    @GetMapping("/all/account/{accountId}")
    public List<Warehouse> listByAccount(@PathVariable(value = "accountId")UUID accountId){
        return wareHouseService.joinWarehouseByIdWithBuildingWithHospitalWithAccount(accountId);
    }

    @PostMapping("/add/{buildingId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createWareHouse(@PathVariable (value = "buildingId") UUID buildingId, @RequestBody Warehouse warehouseReq){
        Building building = buildingService.findOne(buildingId);       
        Map<String, Object> response = new HashMap<>();
        try{
            warehouseReq.setBuilding(building);
            wareHouseService.save(warehouseReq);
        }catch (DataAccessException e){
            response.put("Error", "You can't add warehouses");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Message", "WareHouse Created");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/add/{wareHouseId}")
    public ResponseEntity<?> delete(@PathVariable (value = "wareHouseId") UUID wareHouseId){
        Map<String, Object> response = new HashMap<>();
        try {
            wareHouseService.delete(wareHouseId);
        }catch (DataAccessException e){
            response.put("message", "Error deleting wareHouse from database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("message", "WareHouse deleted");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("add/{wareHouseId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody Warehouse warehouse, @PathVariable (value = "wareHouseId")UUID wareHouseId){
        Warehouse currentWareHouse = wareHouseService.findOne(wareHouseId);
        Warehouse wareHouseUpdated = null;
        Map<String, Object> response = new HashMap<>();
        if(currentWareHouse == null){
            response.put("message", "WareHouse not found");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            currentWareHouse.setFloor(warehouse.getFloor());
            currentWareHouse.setWareHouseName(warehouse.getWareHouseName());
            wareHouseUpdated = wareHouseService.save(currentWareHouse);
        }catch (DataAccessException e){
            response.put("message", "Error updating database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Warehouse updated");
        response.put("warehouse", wareHouseUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
