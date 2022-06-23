package pl.sdacademy.springdatauserdemo.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sdacademy.springdatauserdemo.model.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .map(UserDetailsAdapter::new)
                .orElse(null);
    }
}
