package com.example.chatgptdemo.service;

import com.example.chatgptdemo.entities.Conversation;
import com.example.chatgptdemo.entities.Message;
import com.example.chatgptdemo.repository.ConversationRepository;
import com.example.chatgptdemo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private APIService apiService;

    public List<Message> findMessagesInConversation(int id) {
        return messageRepository.findByConversationId(id);
    }

    public Message sendMessage(Message message, int conversationId) {
        List<Message> messages;
        Conversation conversation = conversationRepository.findConversationById(conversationId);
        StringBuilder builder = new StringBuilder();
        String response;
        String prompt;

        message.setConversation(conversation);
        message.setFromHuman(true);
        messageRepository.save(message);

        messages = findMessagesInConversation(conversationId);

        messages.forEach((msg) -> {
            builder.append(msg.getContent() + "\n");
        });

        prompt = builder.toString();

        response = apiService.getAIResponse(prompt);
        System.out.print(prompt);
        System.out.println("response: " + response);
        messageRepository.save(new Message(false, response, conversation));

        return message;
    }

}
