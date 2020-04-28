package com.practice.TrackZilla.repository;

import org.springframework.data.repository.CrudRepository;

import com.practice.TrackZilla.entity.Ticket;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

}
