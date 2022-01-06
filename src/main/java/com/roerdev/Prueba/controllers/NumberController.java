package com.roerdev.Prueba.controllers;

import com.roerdev.Prueba.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class NumberController {

    private final NumberService numberService;

    @Autowired
    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @PostMapping(value = "/addValue")
    public ResponseEntity<?> addValue(HttpServletRequest request) {
        String value = request.getParameter("value");
        this.numberService.addNumber(value);
        return new ResponseEntity<>("Add value succesfull", HttpStatus.OK);
    }

    @GetMapping(value = "/calculateResult")
    public ResponseEntity<?> getResult(HttpServletRequest request) {
        String operator = request.getParameter("operator");
        return new ResponseEntity<>(this.numberService.result(operator), HttpStatus.OK);
    }

}
