package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.*;
import com.opbaquero.conexionaback.models.service.dto.ReplacementDTO;
import com.opbaquero.conexionaback.models.service.dto.ReplacementDataExportDTO;
import com.opbaquero.conexionaback.models.service.dto.ReplacementItemDTO;
import com.opbaquero.conexionaback.models.service.interfaces.*;
import com.opbaquero.conexionaback.security.entity.User;
import com.opbaquero.conexionaback.security.service.UserService;
import com.opbaquero.conexionaback.utils.ReplacementExporterExcel;
import com.opbaquero.conexionaback.utils.ReplacementExporterPdf;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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

    @GetMapping("/export-data/{id}")
    public List<ReplacementDataExportDTO> exportData(@PathVariable(value = "id")UUID id){
        return replacementService.findDataRepositionByAccount(id);
    }

    @GetMapping("/detail/{replacementId}")
    public List<ReplacementItemDTO> replacementDetail(@PathVariable(value = "replacementId") UUID id){
        return replacementService.findItemsByRepositionId(id);
    }

    @GetMapping("/export-data/pdf/{id}")
    public void exportToPdfData(@PathVariable(value = "id")UUID id, HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=data_" + now + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<ReplacementDataExportDTO> listData = replacementService.findDataRepositionByAccount(id);

        ReplacementExporterPdf export = new ReplacementExporterPdf(listData);
        export.exportDocument(response);
    }

    @GetMapping("/export-data/excel/{id}")
    public void exportToExcelData(@PathVariable(value = "id")UUID id, HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String now = dateFormat.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=data_" + now + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<ReplacementDataExportDTO> listData = replacementService.findDataRepositionByAccount(id);

        ReplacementExporterExcel export = new ReplacementExporterExcel(listData);
        export.export(response);
    }

    /**
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
    }*/



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
