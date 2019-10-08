package edu.umuc.cmsc495.models.dao;

import edu.umuc.cmsc495.models.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: stanislav
 * Date: 10/5/19
 * Project: blog-mvc
 * Package: edu.umuc.blogz.models.dao
 */
@Transactional
@Repository
public interface PostDao extends CrudRepository<Post, Integer> {

    List<Post> findByAuthor(int authorId);

    Post findByUid(int uid);

    List<Post> findAll();
}
