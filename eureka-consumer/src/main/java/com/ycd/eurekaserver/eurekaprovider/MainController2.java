package com.ycd.eurekaserver.eurekaprovider;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by ycd on 2020/6/25 5:00 下午
 */
@RestController
public class MainController2 {
    @Autowired
    DiscoveryClient client;

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient client2;

    @Autowired
    LoadBalancerClient lbc;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/client6")
    public String client6(){
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance instance = lbc.choose("provider");

        String url = "http://"+instance.getHost()+":"+instance.getPort()+"/getHi";

        String resp = restTemplate.getForObject(url,String.class);
        return resp;
    }

    @GetMapping("/client7")
    public String client7(){

        String url = "http://provider/getHi";

        String resp = restTemplate.getForObject(url,String.class);
        return resp;
    }
}
