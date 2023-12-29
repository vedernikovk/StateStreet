package com.statestreet.students.entity.many_to_many;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "PostTag")
@Table(name = "post_tag")
public class PostTagEntity {

    @EmbeddedId
    private PostTagId id;

    @ManyToOne
    @MapsId("postId")
    private PostEntity post;

    @ManyToOne
    @MapsId("tagId")
    private TagEntity tag;

    private PostTagEntity() {}

    public PostTagEntity(PostEntity post, TagEntity tag) {
        this.post = post;
        this.tag = tag;
        this.id = new PostTagId(post.getId(), tag.getId());
    }

    public PostTagId getId() {
        return id;
    }

    public void setId(PostTagId id) {
        this.id = id;
    }

    public PostEntity getPost() {
        return post;
    }

    public void setPost(PostEntity post) {
        this.post = post;
    }

    public TagEntity getTag() {
        return tag;
    }

    public void setTag(TagEntity tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostTagEntity that = (PostTagEntity) o;
        return Objects.equals(post, that.post) &&
                Objects.equals(tag, that.tag);
    }
    @Override
    public int hashCode() {
        return Objects.hash(post, tag);
    }
}