package com.freeloader.kafkaproducer.model;

import java.util.List;



public record UserDetails(String firstName, String lastName, String email, Address address, List<Phone> phones) {

}
