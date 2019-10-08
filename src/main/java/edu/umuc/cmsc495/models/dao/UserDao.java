package edu.umuc.cmsc495.models.dao;

import edu.umuc.cmsc495.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Author: stanislav
 * Date: 10/5/19
 * Project: blog-mvc
 * Package: edu.umuc.cmsc495.models.dao
 */
@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {
    User findByUid(int uid);

    List<User> findAll();

    User findByUsername(String username);
}
