package com.example.service1.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 231215 (금) RestTemplate 실습
 */
@Component
public class RestTemplateClientCommunicator {

    @Autowired
    private DiscoveryClient discoveryClient;

    public String getName(String id) {

        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("service2");

        if (instances.size()==0) return null;

        /** 인스턴스들 중 0번째 클라이언트에 요청 */
        // TODO http://192.168.0.5:8023/v1/service2/name/{id} - service2
        /**
         * 호출하는 서비스에서  IP와 PORT를 관리해야 한다. (아니면 위 예제 처럼 DiscoveryClient를 통해 항상 정보를 가져온 뒤 호출해야 함)
         * 클라이언트 측 부하 분산을 할 수 없다. (Ribbon의 다양한 로드밸런싱 설정 사용 불가)
         */
        String serviceUri = String.format("%s/v1/service2/name/%s",instances.get(0).getUri().toString(), id);

        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, String.class, id);

        return  id + " is " + restExchange.getBody();
    }
}

