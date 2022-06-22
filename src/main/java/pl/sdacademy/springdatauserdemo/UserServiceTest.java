package pl.sdacademy.springdatauserdemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import pl.sdacademy.springdatauserdemo.model.User;
import pl.sdacademy.springdatauserdemo.model.UserRepository;
import pl.sdacademy.springdatauserdemo.service.UserService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceTest implements CommandLineRunner {
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
//        User user1 = User.builder()
//                .firstName("Jan")
//                .lastName("Kowalski")
//                .email("j.kowalski@gmail.com")
//                .phoneNumber("123456789")
//                .build();
//        log.info("Created user: " + userService.save(user1));
//
//        User user2 = User.builder()
//                .firstName("Andrzej")
//                .lastName("Nowak")
//                .email("a.nowak@gmail.com")
//                .phoneNumber("123456555")
//                .build();
//        log.info("Created user: " + userService.save(user2));

        List<User> users = userRepository.findAllByLastNameAndFirstName("Kowalski", "Jan");
        log.info(users.toString());
//        log.info(userRepository.findDistinctByLastNameAndFirstName("Kowalski", "Jan").toString());
    }
}
