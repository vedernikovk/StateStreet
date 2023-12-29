package com.statestreet.students.entity.many_to_many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostTagEntity> tags = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PostTagEntity> getTags() {
        return tags;
    }

    public void setTags(List<PostTagEntity> tags) {
        this.tags = tags;
    }

    public void removeTag(TagEntity tag) {
        for (Iterator<PostTagEntity> iterator = tags.iterator(); iterator.hasNext(); ) {
            PostTagEntity postTag = iterator.next();
            if (postTag.getPost().equals(this) && postTag.getTag().equals(tag)) {
                iterator.remove();
                postTag.getTag().getPosts().remove(postTag);
                postTag.setPost(null);
                postTag.setTag(null);
                break;
            }
        }
    }
}
