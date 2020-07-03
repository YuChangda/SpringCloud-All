package com.ycd.userapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ycd on 2020/6/27 2:19 下午
 */
@RequestMapping("/user")
public interface UserApi {

    /**
     *
     * @return
     */
    @GetMapping("/alive")
    String alive();

    @PostMapping("/postPerson")
    Person postPerson(@RequestBody Person person);
}
