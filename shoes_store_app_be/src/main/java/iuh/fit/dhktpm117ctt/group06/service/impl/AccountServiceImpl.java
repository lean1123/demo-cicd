package iuh.fit.dhktpm117ctt.group06.service.impl;

import iuh.fit.dhktpm117ctt.group06.dto.request.ResetPasswordRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.AccountResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Account;
import iuh.fit.dhktpm117ctt.group06.repository.AccountRepository;
import iuh.fit.dhktpm117ctt.group06.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }

    @Override
    public Optional<AccountResponse> findByUser(String userId) {
        Account account = accountRepository.findByUser(userId).orElse(null);
        if (account == null) {
            return Optional.empty();
        }
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setEmail(account.getEmail());
        return Optional.of(accountResponse);
    }

    @Override
    public Optional<Account> resetPassword(String id, ResetPasswordRequest resetPasswordRequest) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            Account account1 = account.get();
            account1.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
            return Optional.of(accountRepository.save(account1));
        }
        return Optional.empty();
    }
}
