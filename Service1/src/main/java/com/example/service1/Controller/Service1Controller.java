package com.example.service1.Controller;

import com.example.service1.Service.Service1Service;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("v1/service1")
@AllArgsConstructor
@RestController
public class Service1Controller {

    @Autowired
    private Service1Service service1Service;

    @GetMapping(value = "/services")
    public List<String> services() {
        return service1Service.getServices();
    }

    // 231215 (금) RestTemplate 실습
    @GetMapping(value = "/restTemplate/{id}")
    public String restTemplate(@PathVariable("id") String id) {
        return service1Service.restTemplate(id);
    }

    // 231215 (금) RibbonTemplate 실습
    @GetMapping(value = "/ribbonTemplate/{id}")
    public String ribbonTemplate(@PathVariable("id") String id) {
        return service1Service.ribbonTemplate(id);
    }

    // 231215 (금) FeginTemplate 실습
    @GetMapping(value = "/feginTemplate/{id}")
    public String feginTemplate(@PathVariable("id") String id) {
        return service1Service.feginTemplate(id);
    }



}
