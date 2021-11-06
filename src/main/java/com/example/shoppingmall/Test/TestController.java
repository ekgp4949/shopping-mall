package com.example.shoppingmall.Test;

import com.example.shoppingmall.domain.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String helloUser() {
        return UserDto.builder().name("Dahye").age("28").build().toString();
    }
}
