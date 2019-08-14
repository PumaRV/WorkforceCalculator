package com.challenge.spo.workforcecalculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {

    @GetMapping(value = "/")
    public String redirectToSwaggerUi() {
        return "redirect:/swagger-ui.html";
    }

}