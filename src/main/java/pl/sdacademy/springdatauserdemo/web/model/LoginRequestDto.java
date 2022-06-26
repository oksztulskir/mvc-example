package pl.sdacademy.springdatauserdemo.web.model;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String login;
    private String password;
}
