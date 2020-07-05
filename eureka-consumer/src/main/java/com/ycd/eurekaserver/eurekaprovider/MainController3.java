package com.ycd.eurekaserver.eurekaprovider;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * Created by ycd on 2020/6/25 5:00 下午
 */
@RestController
public class MainController3 {
    @Autowired
    DiscoveryClient client;

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient client2;

    @Autowired
    LoadBalancerClient lbc;

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/client8")
    public Object client8() {

        String url = "http://provider/getObj";

        Person person = restTemplate.getForObject(url, Person.class);
        return person;
    }

    @GetMapping("/client9")
    public Object client9() {

        String url = "http://provider/getObj2?name={1}";

        Person person = restTemplate.getForObject(url, Person.class, "maliuliu666");
        return person;
    }

    @GetMapping("/client10")
    public Object client10() {
        // 自动处理URL
        String url = "http://provider/getObj2?name={name}";
        Map<String, String> map = Collections.singletonMap("name", "xiaobaby");

        Person object = restTemplate.getForObject(url, Person.class, map);

        return object;
    }

    @GetMapping("/client11")
    public Object client11(HttpServletResponse response) throws IOException {
        // 自动处理URL
        String url = "http://provider/postLocation";
        Map<String, String> map = Collections.singletonMap("name", "huangguangyu");

        URI uri = restTemplate.postForLocation(url, map, Person.class);
        response.sendRedirect(uri.toString());
        return null;
    }
}
