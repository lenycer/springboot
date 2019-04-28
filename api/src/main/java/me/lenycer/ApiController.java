package me.lenycer;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by a1100440 on 28/04/2019.
 */
@RestController
public class ApiController {


    @RequestMapping(value = "/api/{status}")
    public ResponseEntity<ApiResponse> api(@RequestHeader HttpHeaders headers
            , @PathVariable int status
            , @RequestParam(required = false) Long delay) {

        System.out.println(headers.toString());
        ApiResponse res = new ApiResponse();
        res.setType("api");
        res.setData(headers.getFirst("data") == null ? "default data" : headers.getFirst("data"));
        res.setStatus(status);
        if(delay != null) {
            try {
                Thread.sleep(delay.longValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(res, HttpStatus.valueOf(status));
    }

    @RequestMapping(value = "/authorize")
    public ResponseEntity<ApiResponse> authorize(@RequestHeader HttpHeaders headers) {

        System.out.println(headers.toString());
        ApiResponse res = new ApiResponse();
        res.setType(HttpHeaders.AUTHORIZATION);
        res.setData(isValidToken(headers) ? "token valid" : "token invalid");
        res.setStatus(isValidToken(headers) ? 200 : 401);
        return new ResponseEntity<>(res, HttpStatus.valueOf(200));
    }

    private boolean isValidToken(@RequestHeader HttpHeaders headers) {
        return headers.getFirst(HttpHeaders.AUTHORIZATION).equals("valid");
    }
}
