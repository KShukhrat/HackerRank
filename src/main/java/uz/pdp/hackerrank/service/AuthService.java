package uz.pdp.hackerrank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.pdp.hackerrank.exception.DataNotFoundException;
import uz.pdp.hackerrank.repository.UserRepository;
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserEntityByUsername(username)
                .orElseThrow(
                        () -> new DataNotFoundException("USER NOT FOUND")
                );
    }
}
