package com.example.shoppingmall.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserDto {

    private String name;
    private String age;

}
