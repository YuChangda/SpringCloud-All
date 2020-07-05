package com.ycd.userconsumer;

import com.ycd.userapi.Person;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserProviderBack implements ConsumerApi {

    @Override
    public String alive() {


        return "降级了";
    }

    @Override
    public Person postPerson(Person person) {
        return null;
    }


    @Override
    public Map<Integer, String> getMap(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Map<Integer, String> getMap3(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return null;
    }


}
