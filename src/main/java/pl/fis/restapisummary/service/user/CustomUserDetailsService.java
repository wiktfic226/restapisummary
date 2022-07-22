package pl.fis.restapisummary.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.fis.restapisummary.entity.permission.Permission;
import pl.fis.restapisummary.entity.user.UserEntity;
import pl.fis.restapisummary.exception.NotFoundException;
import pl.fis.restapisummary.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        HttpStatus.NOT_FOUND, String.format("User with username %s does not exists", username)));

        return new User(user.getUsername(), user.getPassword(), getGrantedAuthorities(user.getAuthorities()));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Collection<Permission> permissions) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Permission permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        }
        return authorities;
    }
}
