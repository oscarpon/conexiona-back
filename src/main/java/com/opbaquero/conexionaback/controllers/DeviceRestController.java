package com.opbaquero.conexionaback.controllers;

import com.opbaquero.conexionaback.models.entity.Device;
import com.opbaquero.conexionaback.models.entity.Warehouse;
import com.opbaquero.conexionaback.models.service.interfaces.IDeviceService;
import com.opbaquero.conexionaback.models.service.interfaces.IWareHouseProductService;
import com.opbaquero.conexionaback.models.service.interfaces.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/device")
public class DeviceRestController {

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IWareHouseService wareHouseService;

    @Autowired
    private IWareHouseProductService wareHouseProductService;



}
