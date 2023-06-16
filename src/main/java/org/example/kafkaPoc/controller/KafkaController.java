package org.example.kafkaPoc.controller;

import org.example.kafkaPoc.producer.Producer;
import org.example.kafkaPoc.repository.MessageRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
public class KafkaController {

    @Autowired
    private Producer producer;
    @Autowired
    private MessageRepository messageRepository;




    @PostMapping(value = "/send")
    @ApiOperation(value = "create", tags = "message")
    public ResponseEntity<Void> createItem (@RequestBody String item){
        producer.sendMessage(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAll")
    @ApiOperation(value = "get all", tags = "messages")
    public ResponseEntity<String>  getAllItems() {
        return ResponseEntity.ok(messageRepository.getAllItems());
    }
}
