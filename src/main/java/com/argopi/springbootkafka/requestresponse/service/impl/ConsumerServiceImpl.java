package com.argopi.springbootkafka.requestresponse.service.impl;

import com.argopi.springbootkafka.requestresponse.model.Employee;
import com.argopi.springbootkafka.requestresponse.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    Logger log = LoggerFactory.getLogger(ConsumerServiceImpl.class);

    List<Employee> employees = new ArrayList<>();

    @KafkaListener(topics = "${app.spring.kafka.topic}",groupId = "${app.spring.kafka.groupid}")
    @SendTo
    @Override
    public Employee read(Employee employee) {
        employee.setStatus("Completed");
        return employee;
    }


}
