package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.entity.Hospital;
import com.opbaquero.conexionaback.models.service.interfaces.IAccountService;
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

@RestController
@RequestMapping("/hospital")
public class HospitalRestController {

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IHospitalService hospitalService;

    @Autowired
    public HospitalRestController(IAccountService accountService, IHospitalService hospitalService){
        this.hospitalService = hospitalService;
        this.accountService = accountService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Hospital> listAll(){
        return hospitalService.findAll();
    }

    @GetMapping("/{hospitalId}")
    public Hospital getHospital(@PathVariable (value = "hospitalId") UUID hospitalId){
        return hospitalService.findOne(hospitalId);
    }

    @GetMapping("/all/{accountId}")
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public List<Hospital> listByAccount(@PathVariable (value = "accountId") Account accountId){
        return hospitalService.findByAccount(accountId);
    }

    @PostMapping("/add/{accountId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createHospital(@PathVariable (value = "accountId") UUID accountId, @RequestBody Hospital hospitalReq){
        Hospital newHospital = new Hospital();
        Account account = accountService.findOne(accountId);
        Map<String, Object> response = new HashMap<>();
        try{
            hospitalReq.setAccount(account);
            hospitalService.save(hospitalReq);
        }catch (DataAccessException e){
            response.put("Error", "You can't add hospital");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("Message", "Hospital Created");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/add/{hospitalId}")
    public ResponseEntity<?> delete(@PathVariable (value = "hospitalId") UUID hospitalId){
        Map<String, Object> response = new HashMap<>();
        try{
            hospitalService.delete(hospitalId);
        }catch (DataAccessException e){
            response.put("message", "Error deleting hospital from database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Hospital deleted succesfully");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


    @PutMapping("/add/{hospitalId}")
    public ResponseEntity<?> update(@RequestBody Hospital hospital, @PathVariable (value = "hospitalId") UUID hospitalId){
        Hospital currentHospital = hospitalService.findOne(hospitalId);
        Hospital updatedHospital;
        Map<String, Object> response = new HashMap<>();
        if(currentHospital == null){
            response.put("message", "Hospital not found in database");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            currentHospital.setId(hospital.getId());
            currentHospital.setHospitalName(hospital.getHospitalName());
            currentHospital.setPhone(hospital.getPhone());
            currentHospital.setAddress(hospital.getAddress());
            currentHospital.setZipCode(hospital.getZipCode());
            currentHospital.setCity(hospital.getCity());
            currentHospital.setProvince(hospital.getProvince());
            updatedHospital = hospitalService.save(currentHospital);
        }catch (DataAccessException e){
            response.put("message", "Error updating hospital");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Hospital updated succesfully");
        response.put("hospital", updatedHospital);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
