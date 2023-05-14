package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "body")
    private String body;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,
                                                CascadeType.PERSIST,CascadeType.REFRESH}  )
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

}
