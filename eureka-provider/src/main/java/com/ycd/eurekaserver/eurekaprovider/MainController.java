package com.ycd.eurekaserver.eurekaprovider;

import com.netflix.discovery.converters.Auto;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ycd on 2020/6/25 5:00 下午
 */
@RestController
public class MainController {
    @Autowired
    HealthStatusService healthStatusService;

    @Value("${server.port}")
    String port;

    @GetMapping("/getHi")
    public String getHi(){
        return "hi my port: "+port;
    }

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {

        healthStatusService.setStatus(status);
        return healthStatusService.getStatus();

    }

}
