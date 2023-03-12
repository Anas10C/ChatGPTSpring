package com.example.chatgptdemo.controllers;

import com.example.chatgptdemo.entities.Message;
import com.example.chatgptdemo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    MessageService messageService;
    @GetMapping("/conversations/{id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesInConversation(@PathVariable(value = "id") int id) {
        List<Message> messages = messageService.findMessagesInConversation(id);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping("/conversations/{id}/messages")
    public ResponseEntity<Message> createMessage(@PathVariable(value = "id") int id, @RequestBody Message messageRequest) {
        Message message = messageService.sendMessage(messageRequest, id);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
