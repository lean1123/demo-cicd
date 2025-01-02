package iuh.fit.dhktpm117ctt.group06.controller;

import iuh.fit.dhktpm117ctt.group06.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAccountByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(accountService.findByUser(userId));
    }
}
