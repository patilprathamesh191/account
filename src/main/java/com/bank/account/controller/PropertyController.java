package com.bank.account.controller;

import com.bank.account.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class PropertyController {

    private PropertyService propertyService;

    PropertyController(PropertyService propertyService)
    {
        this.propertyService=propertyService;
    }

    @GetMapping("/getProperties")
    public ResponseEntity<Object> getProperties()
    {
        return new ResponseEntity<>(propertyService.getProperties(), HttpStatus.OK);
    }

    @GetMapping("/getKeyVal")
    public ResponseEntity<Object> getPropertiesByKey(@RequestParam(name="key") String key)
    {
        return new ResponseEntity<>(propertyService.getProperties(key), HttpStatus.OK);
    }
}
