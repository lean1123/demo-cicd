package iuh.fit.dhktpm117ctt.group06.service.impl;


import iuh.fit.dhktpm117ctt.group06.entities.Account;
import iuh.fit.dhktpm117ctt.group06.entities.User;
import iuh.fit.dhktpm117ctt.group06.entities.enums.UserRole;
import iuh.fit.dhktpm117ctt.group06.repository.AccountRepository;
import iuh.fit.dhktpm117ctt.group06.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByEmail(username);
        if (account.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        Optional<User> user = userRepository.findById (account.get().getUser().getId());
        UserRole role = user.get().getRole();
        if (role == null) role = UserRole.CUSTOMER;
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((new SimpleGrantedAuthority(role.toString())));
        return new org.springframework.security.core.userdetails.User(account.get().getEmail(), account.get().getPassword(), authorities);
    }
}
