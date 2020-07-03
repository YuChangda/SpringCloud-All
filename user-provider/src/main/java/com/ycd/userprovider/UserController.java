package com.ycd.userprovider;

import com.ycd.userapi.Person;
import com.ycd.userapi.UserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ycd on 2020/6/27 11:52 上午
 */
@RestController
public class UserController implements UserApi {

    @Value("${server.port}")
    private String port;

    private AtomicInteger count = new AtomicInteger();

    @Override
    public String alive() {
        try {
            System.out.println("准备睡");

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int i = count.getAndIncrement();
        System.out.println(port + "====第：" + i + "次调用");
        return "port:" + port;
    }

    @Override
    public Person postPerson(Person person) {
        return person;
    }

    @GetMapping("/getMap")
    public Map<Integer, String> getMap(@RequestParam("id") Integer id) {
        System.out.println(id);
        return Collections.singletonMap(id, "mmeme");
    }

    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

}

