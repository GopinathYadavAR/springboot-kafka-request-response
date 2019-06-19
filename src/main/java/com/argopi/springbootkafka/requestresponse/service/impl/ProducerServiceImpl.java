package com.argopi.springbootkafka.requestresponse.service.impl;

import com.argopi.springbootkafka.requestresponse.model.Employee;
import com.argopi.springbootkafka.requestresponse.service.ProducerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {
    Logger log = LoggerFactory.getLogger(ProducerServiceImpl.class);
    private final ReplyingKafkaTemplate<String,Employee,Employee> kafkaTemplate;
    @Value("${app.spring.kafka.topic}")
    private String requestTopic;
    @Value("${app.spring.kafka.reply-topic}")
    private String requestReplyTopic;
     @Autowired
    public ProducerServiceImpl(ReplyingKafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Employee sendMessage(Employee employee) throws Exception {
        log.info("String{}",employee);
        ProducerRecord<String,Employee> producerRecord = new ProducerRecord<String, Employee>(requestTopic,employee);

        producerRecord.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC,requestReplyTopic.getBytes()));
        RequestReplyFuture<String,Employee,Employee> employeeRequestReplyFuture = kafkaTemplate.sendAndReceive(producerRecord);

        SendResult<String,Employee> result = employeeRequestReplyFuture.getSendFuture().get();
        //print all headers
        result.getProducerRecord().headers().forEach(header -> log.info(header.key() + ":" + header.value().toString()));
// get consumer record
        ConsumerRecord<String, Employee> consumerRecord = employeeRequestReplyFuture.get();
        // return consumer value
        return consumerRecord.value();



    }
}
