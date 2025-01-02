package iuh.fit.dhktpm117ctt.group06.repository;

import iuh.fit.dhktpm117ctt.group06.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {
    Optional<Post> findById(String id);
}