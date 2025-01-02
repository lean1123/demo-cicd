package iuh.fit.dhktpm117ctt.group06.service;

import iuh.fit.dhktpm117ctt.group06.dto.request.PostRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.PostResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Post;

import java.util.Optional;

public interface PostService {
    Optional<PostResponse> findById(String id);
    Optional<PostResponse> save(PostRequest postRequest);
    void deleteById(String id);
    Optional<PostResponse> update(String id, PostRequest postRequest);
}
