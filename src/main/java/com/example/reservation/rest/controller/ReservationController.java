package com.example.reservation.rest.controller;


import com.example.reservation.dto.Reservation;
import com.example.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/reservation" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;


    @GetMapping(value = "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Reservation> findAllReservation() {
        return reservationRepository.getRepository().values().stream().collect(Collectors.toList());
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE ,  produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation createReservation(@RequestBody Reservation reservation){
        reservationRepository.getRepository().put(reservation.getName(),reservation);
        return reservationRepository.getRepository().get(reservation.getName());
    }

    @DeleteMapping(consumes = MediaType.ALL_VALUE ,  produces = MediaType.ALL_VALUE)
    public String deleteReservation(@RequestBody String name){
        Reservation deletedReservation = reservationRepository.getRepository().remove(name);
        //return Optional.ofNullable(deletedReservation).map()
        if(deletedReservation == null) return "value with key : "+name + " not present";
        else return "delete successful";
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE ,  produces = MediaType.APPLICATION_JSON_VALUE)
    public Reservation modifyReservation(@RequestBody Reservation reservation){
        Reservation retrievedReservation = reservationRepository.getRepository().get(reservation.getName());
        retrievedReservation.setPhoneNumber(reservation.getPhoneNumber());
        reservationRepository.getRepository().put(reservation.getName(), reservation);
        return reservationRepository.getRepository().get(reservation.getName());
    }

}
