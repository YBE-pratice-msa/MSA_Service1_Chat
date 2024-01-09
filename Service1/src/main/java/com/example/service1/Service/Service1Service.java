package com.example.service1.Service;

import com.example.service1.rest.FeignClientCommunicator;
import com.example.service1.rest.RestTemplateClientCommunicator;
import com.example.service1.rest.RibbonClientCommunicator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Service1Service {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplateClientCommunicator restTemplateClientCommunicator;
    @Autowired
    private RibbonClientCommunicator ribbonClientCommunicator;
    @Autowired
    private FeignClientCommunicator feignClientCommunicator;

    public List getServices(){
        List<String> services = new ArrayList<String>();

        /** 람다스트림 표현 */
        discoveryClient.getServices().forEach(serviceName -> {
            discoveryClient.getInstances(serviceName).forEach(instance->{
                services.add( String.format("%s:%s",serviceName,instance.getUri()));
            });
        });
        return services;
    }

    // 231215 (금) RestTemplate 실습
    public String restTemplate(String id) {
        log.info("Communicating by RestTemplateClientCommunicator.");
        return restTemplateClientCommunicator.getName(id);
    }

    public String ribbonTemplate(String id) {
        log.info("Communicating by RibbonTemplateClientCommunicator.");
        return ribbonClientCommunicator.getName(id);
    }

    public String feginTemplate(String id) {
        log.info("Communicating by FeginTemplateClientCommunicator.");
        return "Fegin : " + feignClientCommunicator.getName(id);
    }
}
