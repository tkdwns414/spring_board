package practice.myboard.domain.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class MemoryPostRepository implements PostRepository{

    private static Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        post.setPubDate(LocalDateTime.now());
        store.put(post.getId(), post);
        log.info("save: post={}", post);
        return post;
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void update(Long postId, Post updateParam) {
        Post findPost = findById(postId);
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());
        findPost.setEditDate(LocalDateTime.now());
        log.info("update: post={}", findPost);
    }

    @Override
    public void delete(Long postId) {
        log.info("remove: post={}", findById(postId));
        store.remove(postId);
        log.info("remove success");
    }
}
