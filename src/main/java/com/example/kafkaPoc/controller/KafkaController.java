package com.example.kafkaPoc.controller;

import com.example.kafkaPoc.producer.Producer;
import com.example.kafkaPoc.domain.ItemsPoc;
import com.example.kafkaPoc.repository.MessageRepository;
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




    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ApiOperation(value = "create", tags = "message")
    public ResponseEntity<Void> createItem (@RequestBody String item){
        producer.sendMessage(item);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "get all", tags = "messages")
    public ResponseEntity<String>  getAllItems() {
        return ResponseEntity.ok(messageRepository.getAllItems());
    }
}
