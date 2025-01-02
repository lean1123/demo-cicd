package iuh.fit.dhktpm117ctt.group06.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import iuh.fit.dhktpm117ctt.group06.dto.request.UserRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.UserResponse;
import iuh.fit.dhktpm117ctt.group06.entities.User;
import iuh.fit.dhktpm117ctt.group06.service.MailSenderService;
import iuh.fit.dhktpm117ctt.group06.service.UserService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MailSenderService mailSenderService;

    @GetMapping("/send-mail")
    public ResponseEntity<?> sendMail() {
        mailSenderService.sendMail("lethanhan20039@gmail.com", "Hello", "Test");
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<UserResponse> user = userService.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        return ResponseEntity.ok(user.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String keyword) {
        return ResponseEntity.ok(userService.search(keyword));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateById(@PathVariable String id, @Valid @ModelAttribute UserRequest userRequest, BindingResult bindingResult) {
        Map<String, Object> response = new LinkedHashMap<>();

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new LinkedHashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            response.put("status", HttpStatus.BAD_REQUEST.value());
            response.put("errors", errors);
        }

        Optional<UserResponse> user = userService.updateInfo(id,userRequest);
        if (user.isEmpty()) {
            response.put("status", HttpStatus.NOT_FOUND.value());
            response.put("message", "User not found");
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/avatar")
    @Operation(
            summary = "Update user avatar",
            description = "Upload a new avatar for the user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "multipart/form-data",
                            schema = @Schema(
                                    type = "object",
                                    description = "Request body for avatar upload",
                                    requiredProperties = {"avatar"},
                                    implementation = MultipartFile.class
                            )
                    )
            ),
            security = @SecurityRequirement(name = "bearer-jwt")
    )


    public ResponseEntity<?> updateAvatar(@PathVariable String id, @RequestParam("avatar") MultipartFile avatar) {
        if (avatar.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }
        UserResponse user = userService.updateAvatar(id, avatar).orElse(null);
        return ResponseEntity.ok(user);
    }

}
