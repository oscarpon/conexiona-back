package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.service.dto.EmailDTO;
import com.opbaquero.conexionaback.models.service.impl.EmailServiceImpl;
import com.opbaquero.conexionaback.models.service.interfaces.IAccountService;
import com.opbaquero.conexionaback.models.service.interfaces.IEmailService;
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
@RequestMapping("/accounts")
public class AccountRestController {
    @Autowired
    public IAccountService accountService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Account> index(){
        return accountService.findAll();
    }

    @GetMapping("/all/{accountId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Account getAccount(@PathVariable (value = "accountId") UUID accountId){
        return accountService.findOne(accountId);
    }

    @GetMapping("/get/{accountName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UUID getAccountUUID(@PathVariable (value = "accountName") String accountName){
        return accountService.findByAccountName(accountName);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody Account account){
        Account newAccount = null;
        Map<String, Object> response = new HashMap<>();
        if(accountService.existsByAccountName(account.accountName)){
            response.put("error", "Account name already exists");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
        try{
            newAccount = accountService.save(account);
        }catch (DataAccessException e){
            response.put("error", "Impossible ad account to database");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Account created succesfully");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    @PutMapping("/add/{accountId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody Account account, @PathVariable (value = "accountId") UUID accountId){
        Account currentAccount = accountService.findOne(accountId);
        Account accountUpdated = null;
        Map<String, Object> response = new HashMap<>();
        if(currentAccount == null){
            response.put("message", "Account not found in database");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            currentAccount.setAccountName(account.getAccountName());
            accountUpdated = accountService.save(currentAccount);
        }catch (DataAccessException e){
            response.put("message", "Error updating database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Account updated sucesfully");
        response.put("account", accountUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/add/{accountId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable (value = "accountId") UUID accountId){
        Map<String, Object> response = new HashMap<>();
        Account accountAdmin = accountService.findOne(accountId);
        try{
                accountService.delete(accountId);

        }catch (DataAccessException e){
            response.put("message", "Error deleting account from database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Account deleted Succesfully");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }




}