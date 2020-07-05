package com.ycd.eurekaserver.eurekaprovider;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

    private int id;
    private String name;


    public Person(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }


}
