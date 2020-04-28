package com.practice.TrackZilla.service;

import java.util.List;

import com.practice.TrackZilla.entity.Ticket;

public interface TicketService {

	List<Ticket> listTickets();
	Ticket findTicket(long id);
	Ticket newTicket(Ticket ticket);
	Ticket updateTicket(long id, Ticket ticket);
	String deleteTicket(long id);
}