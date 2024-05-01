package me.approximations.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor(force=true)
@Getter
@Setter
@EqualsAndHashCode(of="id")
@Table(name="posts")
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private final Long id;
    private String title;
    private String content;

    @ManyToOne
    private final User author;

    @Column(name = "created_at")
    private final Instant createdAt;
}
