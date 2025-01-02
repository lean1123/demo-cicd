package iuh.fit.dhktpm117ctt.group06.service.impl;

import iuh.fit.dhktpm117ctt.group06.dto.request.PostRequest;
import iuh.fit.dhktpm117ctt.group06.dto.response.PostResponse;
import iuh.fit.dhktpm117ctt.group06.entities.Post;
import iuh.fit.dhktpm117ctt.group06.repository.PostRepository;
import iuh.fit.dhktpm117ctt.group06.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post mapToPost(PostRequest postRequest) {
        return modelMapper.map(postRequest, Post.class);
    }

    public PostResponse mapToPostResponse(Post post) {
        return modelMapper.map(post, PostResponse.class);
    }

    @Override
    public Optional<PostResponse> findById(String id) {
        Post post = postRepository.findById(id).orElse(null);
        return Optional.of(mapToPostResponse(post));
    }

    @Override
    public Optional<PostResponse> save(PostRequest postRequest) {
        return Optional.of(mapToPostResponse(postRepository.save(mapToPost(postRequest))));
    }

    @Override
    public void deleteById(String id) {
        postRepository.deleteById(id);
    }

    @Override
    public Optional<PostResponse> update(String id, PostRequest postRequest) {
        Post post = postRepository.getReferenceById(id);
        Post updatedPost = mapToPost(postRequest);
        updatedPost.setId(post.getId());
        return Optional.of(mapToPostResponse(postRepository.save(updatedPost)));
    }
}