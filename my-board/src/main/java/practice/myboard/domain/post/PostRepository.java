package practice.myboard.domain.post;

import java.util.List;

public interface PostRepository {

    public Post save(Post post);

    public Post findById(Long id);

    public List<Post> findAll();

    public void update(Long postId, Post updateParam);

    public void delete(Long postId);
}