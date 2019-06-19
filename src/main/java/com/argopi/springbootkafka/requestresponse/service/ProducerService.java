package com.argopi.springbootkafka.requestresponse.service;

import com.argopi.springbootkafka.requestresponse.model.Employee;

public interface ProducerService {
    Employee sendMessage(Employee employee)throws Exception ;
}
