package com.argopi.springbootkafka.requestresponse.service;


import com.argopi.springbootkafka.requestresponse.model.Employee;

public interface ConsumerService {

    Employee read(Employee employee);


}
