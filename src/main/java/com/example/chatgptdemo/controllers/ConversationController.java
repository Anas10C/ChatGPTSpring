package com.example.chatgptdemo.controllers;

import com.example.chatgptdemo.entities.Conversation;
import com.example.chatgptdemo.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ConversationController {
    @Autowired
    ConversationRepository conversationRepository;

    @GetMapping("/conversations")
    public ResponseEntity<List<Conversation>> getAllConversations() {
        List<Conversation> conversations = new ArrayList<>();

        if(conversations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(conversations, HttpStatus.OK);

    }

    @GetMapping("/conversations/{id}")
    public ResponseEntity<Conversation> getConversationById(@PathVariable("id") int id) throws Exception {
        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new Exception("Conversation with ID=" + id + " not found."));
        return new ResponseEntity<>(conversation, HttpStatus.OK);
    }

    @PostMapping("/conversations")
    public ResponseEntity<Conversation> createConversation(@RequestBody Conversation conversation) {
        Conversation savedConversation = conversationRepository.save(new Conversation(conversation.getName()));
        return new ResponseEntity<>(savedConversation, HttpStatus.CREATED);
    }

    @DeleteMapping("/conversations/{id}")
    public ResponseEntity<HttpStatus> deleteConversation(@PathVariable("id") int id) {
        conversationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/conversations")
    public ResponseEntity<HttpStatus> deleteAllConversations() {
        conversationRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
