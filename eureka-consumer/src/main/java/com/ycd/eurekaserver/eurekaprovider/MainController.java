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
public class MainController {
    @Autowired
    DiscoveryClient client;

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient client2;

    @Autowired
    LoadBalancerClient lbc;

    @GetMapping("/getHi")
    public String getHi() {
        return "hi";
    }

    @GetMapping("/client")
    public String client() {
        List<String> services = client.getServices();
        services.forEach(System.out::println);
        return "hi";
    }

    @GetMapping("/client2")
    public String client2() {
        //具体服务名
        //List<InstanceInfo> instances = client2.getInstancesById("192.168.1.109:provider:80");

        //使用服务名，找列表
        List<InstanceInfo> instances = client2.getInstancesByVipAddress("provider", false);
        for (InstanceInfo ins : instances) {
            System.out.println(ToStringBuilder.reflectionToString(ins));
        }
        if (instances.size() > 0) {
            instances.forEach(e -> {
                if (e.getStatus() == InstanceInfo.InstanceStatus.UP) {
                    String url = "http://" + e.getHostName() + ":" + e.getPort() + "/getHi";
                    System.out.println(url);
                }

            });

        }
        return "hi";
    }

    @GetMapping("/client3")
    public String client3() {
        // ribbon 完成客户端的负载均衡，过滤掉down了的节点
        ServiceInstance instance = lbc.choose("provider");

        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/getHi";
        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(url, String.class);
        System.out.println("resp: " + resp);
        return "hi";
    }
}
