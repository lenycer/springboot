package me.lenycer.feign;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import me.lenycer.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by a1100440 on 28/04/2019.
 */
@Component
public class AuthorizeFeignHystrix {

    @Autowired
    AuthorizeFeignClient authorizeFeignClient;

    @HystrixCommand(groupKey = "authorize", fallbackMethod = "tokenFallBack")
    public ApiResponse authorize(String token) {
        return authorizeFeignClient.authorize(token);
    }

    public ApiResponse tokenFallBack(String token) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setType("Unauthorize");
        apiResponse.setData(token);
        apiResponse.setStatus(401);
        return authorizeFeignClient.authorize(token);
    }
}
