package com.ParameterEndPoint._ParameterWithEndPoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/api/selamla")
    public String selamla(@RequestParam(defaultValue= "Dünya") String value) {
        return "Merhaba, " + value + "!";
    }
    
}
