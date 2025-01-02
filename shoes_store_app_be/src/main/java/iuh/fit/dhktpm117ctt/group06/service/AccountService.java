package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.ResetPasswordRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.AccountResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<AccountResponse> findByUser(String userId);
    Optional<Account> resetPassword(String id, ResetPasswordRequest resetPasswordRequest);
}
