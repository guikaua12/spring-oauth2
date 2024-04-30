package me.approximations.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor(force=true)
@Getter
@Setter
@EqualsAndHashCode(of="id")
@Table(name="users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy="author")
    private final Set<Post> posts = new HashSet<>();

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
