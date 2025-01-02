package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.UserRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.UserResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Address;
import iuh.fit.dhktpm117ctt.group06.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> findAll();

    Optional<UserResponse> findById(String id);

    Optional<UserResponse> findUserByToken(String token);

    Optional<UserResponse> findByEmail(String email);

    Optional<UserResponse> findByPhone(String phone);

    Optional<UserResponse> save(UserRequest userRequest);

    void deleteById(String id);

    Optional<UserResponse> updateInfo(String id, UserRequest userRequest);

    Optional<UserResponse> updateAvatar(String id, MultipartFile avatar);

	Optional<User> getUserById(String id);

    List<UserResponse> search(String keyword);
}
