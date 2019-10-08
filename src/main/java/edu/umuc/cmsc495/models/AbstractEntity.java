package edu.umuc.cmsc495.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Author: stanislav
 * Date: 10/5/19
 * Project: blog-mvc
 * Package: edu.umuc.blogz.models
 */
@MappedSuperclass
public abstract class AbstractEntity {

    private int uid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "uid", unique = true)
    public int getUid() {
        return this.uid;
    }

    protected void setUid(int uid) {
        this.uid = uid;
    }
}
