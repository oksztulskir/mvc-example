package pl.sdacademy.springdatauserdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sdacademy.springdatauserdemo.exceptions.UserNotFoundException;
import pl.sdacademy.springdatauserdemo.model.User;
import pl.sdacademy.springdatauserdemo.model.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public User create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User update(User user) {
        User foundUser = findById(user.getId());
        foundUser.setEmail(user.getEmail());
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setPhoneNumber(user.getPhoneNumber());
        return repository.save(foundUser);
    }

    public User findById(long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found!"));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<User> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
