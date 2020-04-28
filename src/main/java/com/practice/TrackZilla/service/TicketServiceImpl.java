package com.practice.TrackZilla.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.TrackZilla.entity.Ticket;
import com.practice.TrackZilla.exception.TicketNotFoundException;
import com.practice.TrackZilla.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
    @Override
	public List<Ticket> listTickets() {
        return (List<Ticket>) ticketRepository.findAll();
    }
    
    @Override
    public Ticket findTicket(long id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);

        if(optionalTicket.isPresent())
            return optionalTicket.get();
        else
            throw new TicketNotFoundException("Ticket Not Found");
    }
    
    @Override
	public Ticket newTicket(Ticket ticket) {
		return ticketRepository.save(ticket);
	}
    
    @Override
	public Ticket updateTicket(long id, Ticket ticket) {
		Optional<Ticket> existingTicket = ticketRepository.findById(id);
		if(existingTicket.isPresent()) {
			ticket.setId(id);
			BeanUtils.copyProperties(ticket, existingTicket.get());
			return ticketRepository.save(existingTicket.get());
		}
        else
            throw new TicketNotFoundException("Ticket Not Found");
	}
    
    @Override
	public String deleteTicket(long id) {
		Optional<Ticket> release = ticketRepository.findById(id);
		if(release.isPresent()) {
			ticketRepository.deleteById(id);
			return "Success";
		}
        else
            throw new TicketNotFoundException("Ticket Not Found");
	}
}
