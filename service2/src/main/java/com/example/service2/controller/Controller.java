package com.example.service2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class Controller {
	
	@Autowired
	private DiscoveryClient client;
	
	@Autowired
	private RestTemplate restTemplate;
	
    @GetMapping("/hello")
    public String hello() {
    	List<ServiceInstance> lstofInstance=client.getInstances("service-1");
    	String uri=lstofInstance.get(0).getUri().toString();
    	Object res= restTemplate.getForObject(uri+"/api/hello", Object.class);
        return "Hello from Service 2!   ->>" + res;
    }
}

