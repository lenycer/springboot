package me.lenycer.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import me.lenycer.feign.AuthorizeFeignClient;
import me.lenycer.feign.AuthorizeFeignHystrix;
import me.lenycer.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.netflix.zuul.context.RequestContext.getCurrentContext;

/**
 * Created by a1100440 on 28/04/2019.
 */
@Component
public class AuthCheckZuulFilter extends ZuulFilter {

    @Autowired
    AuthorizeFeignHystrix authorizeFeignHystrix;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return !StringUtils.isEmpty(getCurrentContext().getRequest().getHeader(HttpHeaders.AUTHORIZATION));
    }

    @Override
    public Object run() throws ZuulException {
        String token = getCurrentContext().getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        ApiResponse apiResponse = authorizeFeignHystrix.authorize(token);
        System.out.println(apiResponse);
        if(apiResponse.getStatus() == 401) {
            throw new ZuulException("Unauthorize", 401, apiResponse.getData());
        }
        return null;
    }
}
