package pl.sdacademy.springdatauserdemo;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.sdacademy.springdatauserdemo.model.User;

@ActiveProfiles("integration-tests")
@AutoConfigureMockMvc
@SpringBootTest
public class BaseIntegrationTest {
    protected User prepareUser() {
        User user = new User();
        user.setPassword("password");
        user.setFirstName("Jan");
        user.setLastName("Kowalski");
        user.setEmail("jkowalski@gmail.com");
        user.setPhoneNumber("3948548584");

        return user;
    }
}
