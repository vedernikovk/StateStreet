package com.statestreet.students.service;

import com.statestreet.students.entity.many_to_many.PostEntity;
import com.statestreet.students.entity.many_to_many.PostTagEntity;
import com.statestreet.students.entity.many_to_many.TagEntity;
import com.statestreet.students.model.Post;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addPost(Post post) {

        var newPost = new PostEntity();
        newPost.setTitle(post.getTitle());
        em.persist(newPost);

        if (post.getTags() != null) {
            List<PostTagEntity> tags = post.getTags().stream().map(id -> {
                var tagEntity = em.find(TagEntity.class, id);
                return new PostTagEntity(newPost, tagEntity);
            }).collect(Collectors.toList());

            newPost.setTags(tags);
        }

        em.persist(newPost);
    }


    @Transactional
    public void deletePost(long id) throws StudentNotFoundException {
        var post = em.find(PostEntity.class, id);
        if (post == null) {
            throw new StudentNotFoundException();
        }

        em.remove(post);
    }

    @Transactional
    public void updatePost(Post post) throws StudentNotFoundException {
        var postEntity = em.find(PostEntity.class, post.getId());
        if (post == null) {
            throw new StudentNotFoundException();
        }

        // We have to remove all references to the entity that we are removing
        // PostTagEntity postTagEntity = postEntity.getTags().get(0);
        // postEntity.getTags().remove(postEntity);
        // postTagEntity.getPost().getTags().remove(postTagEntity);
        // em.remove(postTagEntity);

        // Remove TagEntity that is not in post
        postEntity.getTags().forEach(tagPost -> {
            if (post.getTags().stream().filter(newTag -> tagPost.getId().getTagId().equals(newTag)).findAny().isEmpty()) {
                tagPost.getPost().removeTag(tagPost.getTag());
            }
        });

        // Create TagEntity that is not in db
        post.getTags().stream().forEach(tag -> {
            if (postEntity.getTags().stream().filter(oldTag -> oldTag.getId().getTagId().equals(tag)).findAny().isEmpty()) {
                em.persist(new PostTagEntity(postEntity, em.find(TagEntity.class, tag)));
            }
        });
    }
}
