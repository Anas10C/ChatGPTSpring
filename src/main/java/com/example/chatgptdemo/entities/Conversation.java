package com.example.chatgptdemo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="Conversations")
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    @Column(name="name", nullable = false)
    private String name;

    public Conversation(String name) {
        this.name = name;
    }
}
