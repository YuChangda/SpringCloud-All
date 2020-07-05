package com.ycd.userconsumer;

import com.ycd.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by ycd on 2020/6/27 1:41 下午
 */
@FeignClient(name = "user-provider", fallback = UserProviderBack.class)
public interface ConsumerApi extends UserApi {

    /**
     * 这里 getMapping 是给Feign看的 get请求 user-provider/getMap?id={1}
     *
     * @param id
     * @return
     * @RequestParam("id") 也是给Feign看的
     * <p>
     * HttpClient Http协议
     */
    @GetMapping("/getMap")
    Map<Integer, String> getMap(@RequestParam("id") Integer id);

    @GetMapping("/getMap3")
    Map<Integer, String> getMap3(@RequestParam Map<String, Object> map);
}
