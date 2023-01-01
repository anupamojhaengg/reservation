package com.example.reservation.repository;


import com.example.reservation.dto.Reservation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReservationRepository {


    private final static Map<String,Reservation> repository = new HashMap<>();

    @Autowired
    public Map<String, Reservation> getRepository(){

        return repository;
    }

}
