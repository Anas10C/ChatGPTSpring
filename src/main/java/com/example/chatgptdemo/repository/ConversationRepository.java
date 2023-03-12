package com.example.chatgptdemo.repository;

import com.example.chatgptdemo.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {
    public Conversation findConversationById(int id);
}
