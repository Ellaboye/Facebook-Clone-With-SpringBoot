package com.example.week7.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Likes {
    @Id
    @SequenceGenerator(
            name = "likes_sequence",
            sequenceName = "likes_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy  = SEQUENCE,
            generator = "likes_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Users person;

    @OneToOne
    @JoinColumn(name = "postId", referencedColumnName = "postId")
    private Post post;
}
