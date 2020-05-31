package com.example.blog2;


import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class DataTime {



    LocalDateTime now = LocalDateTime.now();
     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public String formatDateTime = now.format(formatter);




}
