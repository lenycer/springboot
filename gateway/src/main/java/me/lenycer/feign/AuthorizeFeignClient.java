package me.lenycer.feign;

import me.lenycer.vo.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authorize-api",
        url = "${authorize.api.url}"
)
public interface AuthorizeFeignClient {

    @GetMapping("/authorize")
    ApiResponse authorize(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);

}
