package pl.sdacademy.springdatauserdemo.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("test")
@RequiredArgsConstructor
public class WebSecurityConfigTest implements CommandLineRunner {
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        log.info("password_mvc: " + encoder.encode("password_mvc"));
        log.info("password_rest: " + encoder.encode("password_rest"));
        log.info("admin: " + encoder.encode("admin"));
    }
}
