package com.example.strategy2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strategy")
public class CalculationController {

    @Autowired
    private CalculationSeriviece calculationSeriviece;


    /**
     * 这种请求您方式 在URL直接写/test/add/2/1
     * @param operation
     * @param num1
     * @param num2
     * @return
     */
    @GetMapping("/test/{operation}/{num1}/{num2}")
    public int testCalculation(@PathVariable String operation ,@PathVariable int num1,@PathVariable int num2){

        return calculationSeriviece.operateByStrategy(operation,num1,num2);
    }
}
