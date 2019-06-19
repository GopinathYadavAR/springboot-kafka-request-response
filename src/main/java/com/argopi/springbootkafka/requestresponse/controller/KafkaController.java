package com.argopi.springbootkafka.requestresponse.controller;


import com.argopi.springbootkafka.requestresponse.model.Employee;
import com.argopi.springbootkafka.requestresponse.service.ConsumerService;
import com.argopi.springbootkafka.requestresponse.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private ProducerService producerService;
    private ConsumerService consumerService;

    @Autowired
    public KafkaController(ProducerService producerService,ConsumerService consumerService) {
        this.producerService = producerService;
        this.consumerService =consumerService;
    }
    @PostMapping
    ResponseEntity<Employee> send(@RequestBody Employee employee) throws Exception {
       Employee result = producerService.sendMessage(employee);
        return new ResponseEntity<Employee>(result,HttpStatus.CREATED);
    }

}
