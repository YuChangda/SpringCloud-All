package com.ycd.userconsumer;

import com.ycd.userapi.Person;
import com.ycd.userapi.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ycd on 2020/6/27 1:38 下午
 */
@RestController
public class MainController {
    @Autowired
    ConsumerApi api;

    @GetMapping("/alive")
    public String alive(){
        return api.alive();
    }

    @GetMapping("/postPerson")
    public Person postPerson(@RequestParam Map<Integer,String> map) {
        Person person = new Person();
        person.setId(Integer.parseInt(map.get("id")));
        person.setName("mmmmmm");
        System.out.println(person);

        return api.postPerson(person);
    }

    @GetMapping("/map")
    public Map<Integer, String> map(@RequestParam Integer id) {
        System.out.println(id);
        return api.getMap(id);
    }
    @GetMapping("/map3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map){

//        System.out.println(map);
        HashMap<String, Object> map1 = new HashMap<>(2);

        map1.put("id", 2000);
        map1.put("name", "凯");

        return api.getMap3(map1);
    }
}
