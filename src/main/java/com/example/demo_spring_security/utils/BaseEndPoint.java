package com.example.demo_spring_security.utils;

import org.springframework.stereotype.Component;

@Component
public interface BaseEndPoint {

    String Route = "${app.end-point}";

}
