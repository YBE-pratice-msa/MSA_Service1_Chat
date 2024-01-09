package com.example.service1.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 231215 (금) RibbonClient 실습
 */
@Component
public class RibbonClientCommunicator {

    @Autowired
    RestTemplate restTemplate;

    /** 향상된 스프링 RestTemplate을 사용해 리본 기반의 서비스를 호출 */

    /**
     * @Autowired 어노테이션으로 조금전 Bean으로 등록한 RestTemplate을 주입받습니다.
     *
     * 이번에는 IP:PORT 대신 서비스2의 고유명(service2)으로 호출하면 Ribbon이 내부적으로 IP와 PORT를 찾아서 처리해 줍니다.
     *
     * 물론 서비스2가 여러개의 인스턴스로 이중화 구성되어 있다면 소프트웨어적 로드밸런싱 처리도 해줍니다.
     *
     * 추가 설정을 하지않아 기본 설정인 Round Robbin 알고리즘으로 순차 로드밸런싱을 해줍니다.
     *
     * Ribbon => Load Balancer
     */

    public String getName(String id){

        ResponseEntity<String> restExchange =
                /** Url : http://{applicationId}/v1/ ~~ */
                restTemplate.exchange("http://service2/v1/service2/name/{id}"
                        , HttpMethod.GET, null, String.class, id);

        return "Ribbon : " + id + " is " + restExchange.getBody();
    }
}
