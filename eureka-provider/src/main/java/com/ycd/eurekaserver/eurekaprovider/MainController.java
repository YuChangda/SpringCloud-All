package com.ycd.eurekaserver.eurekaprovider;

import com.netflix.discovery.converters.Auto;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;

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

    @GetMapping("/getObj")
    public Person getObj() {
        Person person = new Person(100,"xiaoliu");
        return person;
    }

    @GetMapping("/getObj2")
    public Person getObj2(String name) {
        Person person = new Person(100,name);
        return person;
    }

    @PostMapping("/postLocation")
    public URI postParam(@RequestBody Person person, HttpServletResponse response) throws URISyntaxException {
        URI uri = new URI("https://www.baidu.com/s?wd="+person.getName().trim());
        response.addHeader("location",uri.toString());
        return uri;
    }

}
