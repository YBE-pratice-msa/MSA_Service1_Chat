package com.example.service1.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 231215 (금) FeginTemplate 실습
 */
@FeignClient("service2")
public interface FeignClientCommunicator {
    @RequestMapping(
            method= RequestMethod.GET,
            value="v1/service2/name/{id}")
    String getName(@PathVariable("id") String id);
}
