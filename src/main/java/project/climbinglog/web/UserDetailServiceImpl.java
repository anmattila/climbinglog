package project.climbinglog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.climbinglog.domain.AppUser;
import project.climbinglog.domain.AppUserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final AppUserRepository repository;

    @Autowired
    public UserDetailServiceImpl(AppUserRepository appUserRepository) {
        this.repository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser currentUser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(
            currentUser.getUsername(),
            currentUser.getPasswordHash(), 
            AuthorityUtils.createAuthorityList(currentUser.getRole()));
        return user;
    }
    
}
