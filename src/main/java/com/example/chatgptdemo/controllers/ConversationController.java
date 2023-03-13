package com.example.chatgptdemo.controllers;

import com.example.chatgptdemo.entities.Conversation;
import com.example.chatgptdemo.repository.ConversationRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api(value="The Conversation controller handles CRUD operations and when a conversation is deleted so are its messages (It cascades on delete)")
public class ConversationController {
    @Autowired
    ConversationRepository conversationRepository;

    @ApiOperation(value="Get all conversations")
    @GetMapping("/conversations")
    public ResponseEntity<List<Conversation>> getAllConversations() {
        List<Conversation> conversations = conversationRepository.findAll();

        if(conversations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(conversations, HttpStatus.OK);

    }

    @ApiOperation(value="Get conversation")
    @GetMapping("/conversations/{id}")
    public ResponseEntity<Conversation> getConversationById(@PathVariable("id") int id) throws Exception {
        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new Exception("Conversation with ID=" + id + " not found."));
        return new ResponseEntity<>(conversation, HttpStatus.OK);
    }

    @ApiOperation(value="Create conversation")
    @PostMapping("/conversations")
    public ResponseEntity<Conversation> createConversation(@RequestBody Conversation conversation) {
        Conversation savedConversation = conversationRepository.save(new Conversation(conversation.getName()));
        return new ResponseEntity<>(savedConversation, HttpStatus.CREATED);
    }
    @ApiOperation(value="Delete conversation")
    @DeleteMapping("/conversations/{id}")
    public ResponseEntity<HttpStatus> deleteConversation(@PathVariable("id") int id) {
        conversationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value="Delete all conversations")
    @DeleteMapping("/conversations")
    public ResponseEntity<HttpStatus> deleteAllConversations() {
        conversationRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
