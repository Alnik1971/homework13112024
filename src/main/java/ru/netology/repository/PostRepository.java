package ru.netology.repository;

import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {

  private ConcurrentHashMap<Long, Post> rep = new ConcurrentHashMap<Long, Post>();
  private AtomicLong counter = new AtomicLong(1);
  public List<Post> all() {
    if (rep.isEmpty()) {
      return Collections.emptyList();
    }
    return new ArrayList<Post>(rep.values());
  }

  public Optional<Post> getById(long id) {
    if (rep.containsKey(id)) {
      return Optional.of(rep.get(id));
    }
    return Optional.empty();
  }

  public Post save(Post post) {
    if (post.getId() == 0) {
      long id = counter.getAndIncrement();
      post.setId(id);
      rep.put(id, post);
    }
    else {
      rep.put(post.getId(), post);
    }
    return post;
  }

  public void removeById(long id) {
    rep.remove(id);
    }
}
