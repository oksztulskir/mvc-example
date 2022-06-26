package pl.sdacademy.springdatauserdemo.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.sdacademy.springdatauserdemo.utils.JwtUtil;
import pl.sdacademy.springdatauserdemo.web.model.LoginRequestDto;
import pl.sdacademy.springdatauserdemo.web.model.LoginResponseDto;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class LoginRestController {
    private final AuthenticationManager authenticationManager;

    @PostMapping
    @ResponseBody
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        log.info("User authenticated: {}", SecurityContextHolder.getContext().getAuthentication());
        UserDetails userDetails = (UserDetails) authenticate.getPrincipal();

        return new LoginResponseDto(JwtUtil.generate(userDetails));
    }
}
