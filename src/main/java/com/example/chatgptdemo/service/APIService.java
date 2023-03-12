package com.example.chatgptdemo.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class APIService {
    @Value("${env.openai.token}")
    private String token;
    public String getAIResponse(String prompt) {
        OpenAiService service = new OpenAiService(token);
        List<String> stopSequence = new ArrayList<>();
        stopSequence.add("\n");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                .model("text-davinci-003")
                .stop(stopSequence)
                .build();
        String response = service.createCompletion(completionRequest).getChoices().get(0).getText();
        return response;
    }
}
