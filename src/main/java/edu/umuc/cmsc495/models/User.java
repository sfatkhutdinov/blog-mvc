package edu.umuc.cmsc495.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author: stanislav
 * Date: 10/5/19
 * Project: blog-mvc
 * Package: edu.umuc.cmsc495.models
 */
@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    private String username;
    private String pwHash;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private List<Post> posts;

    public User() {}

    public User(String username, String password) {
        super();
        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("Invalid username");
        }

        this.username = username;
        this.pwHash = hasPassword(password);
    }

    @NotNull
    @Column(name = "pwHash")
    public String getPwHash() {
        return pwHash;
    }

    private void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    @NotNull
    @Column(name = "username", unique = true)
    public String getUsername() {
        return username;
    }

    private static String hasPassword(String password) {
        return encoder.encode(password);
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public static boolean isValidPassword(String password) {
        Pattern validUsernamePatter = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]{3,20}");
        Matcher matcher = validUsernamePatter.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidUsername(String username) {
        Pattern validUsernamePattern = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]{3,11}");
        Matcher matcher = validUsernamePattern.matcher(username);
        return matcher.matches();
    }

    protected void addPost(Post post) {
        posts.add(post);
    }

    @OneToMany
    @JoinColumn(name = "author_uid")
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
