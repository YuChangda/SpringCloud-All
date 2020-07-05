package com.ycd.userprovider;

/**
 * Created by ycd on 2020/6/27 11:50 上午
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户操作相关接口
 */
@RequestMapping("/User")
public interface RegisterApi {

    @GetMapping("/isAlive")
    public String isAlive();
}
