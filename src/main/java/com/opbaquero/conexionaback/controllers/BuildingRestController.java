package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.Building;
import com.opbaquero.conexionaback.models.entity.Hospital;
import com.opbaquero.conexionaback.models.service.interfaces.IBuildingService;
import com.opbaquero.conexionaback.models.service.interfaces.IHospitalService;
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

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/buildings")
public class BuildingRestController {

    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private IHospitalService hospitalService;

    @Autowired
    public BuildingRestController(IHospitalService hospitalService, IBuildingService buildingService){
        this.buildingService = buildingService;
        this.hospitalService = hospitalService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Building> listAll(){
        return buildingService.findAll();
    }

    @GetMapping("/all/{buildingId}")
    public Building getBuilding(@PathVariable (value = "buildingId") UUID buildingId){
        return buildingService.findOne(buildingId);
    }

    @GetMapping("/all/account/{accountId}")
    public List<Building> listByAccount(@PathVariable(value = "accountId")UUID accountId){
        return buildingService.fetchBuildingByIdWithHospitalWithAccount(accountId);
    }

    @GetMapping("/list/{hospitalId}")
    public List<Building> listByHospital(@PathVariable (value = "hospitalId")Hospital hospitalId){
        return buildingService.findByHospital(hospitalId);
    }

    @PostMapping("/add/{hospitalId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createBuilding(@PathVariable (value = "hospitalId") UUID hospitalId, @RequestBody Building buildingReq){
        Hospital hospital = hospitalService.findOne(hospitalId);
        Map<String, Object> response = new HashMap<>();
        try{
            buildingReq.setHospital(hospital);
            buildingService.save(buildingReq);
        }catch (DataAccessException e){
            response.put("Error", "You can't add a building");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Message", "Building created");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("add/{buildingId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(@RequestBody Building building, @PathVariable (value = "buildingId")UUID buildingId){
        Building currentBuilding = buildingService.findOne(buildingId);
        Building buildingUpdated = null;
        Map<String, Object> response = new HashMap<>();
        if(currentBuilding == null){
            response.put("message", "Building not found");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            currentBuilding.setBuildingName(building.getBuildingName());
            buildingUpdated = buildingService.save(currentBuilding);
        }catch (DataAccessException e){
            response.put("message", "Error updating database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Building updated");
        response.put("building", buildingUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @DeleteMapping("/add/{buildingId}")
    public ResponseEntity<?> delete(@PathVariable (value = "buildingId") UUID buildingId){
        Map<String, Object> response = new HashMap<>();
        try{
            buildingService.delete(buildingId);
        }catch (DataAccessException e){
            response.put("message", "Error deleting building");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Building Deleted");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }



}
