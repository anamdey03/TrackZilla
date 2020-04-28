package com.practice.TrackZilla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.practice.TrackZilla.entity.Application;
import com.practice.TrackZilla.entity.Release;
import com.practice.TrackZilla.entity.Ticket;
import com.practice.TrackZilla.exception.ApplicationNotFoundException;
import com.practice.TrackZilla.exception.ReleaseNotFoundException;
import com.practice.TrackZilla.exception.TicketNotFoundException;
import com.practice.TrackZilla.service.ApplicationService;
import com.practice.TrackZilla.service.ReleaseService;
import com.practice.TrackZilla.service.TicketService;

@RestController
public class TrackZillaRestController {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private ReleaseService releaseService;

	@GetMapping("/v1/tickets")
	public ResponseEntity<List<Ticket>> retrieveTickets() {
		return new ResponseEntity<List<Ticket>>(ticketService.listTickets(), HttpStatus.OK);
	}

	@GetMapping("/v1/tickets/{id}")
	public ResponseEntity<Ticket> getTicket(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<Ticket>(ticketService.findTicket(id), HttpStatus.OK);
		} catch (TicketNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

	@PostMapping("/v1/tickets")
	public ResponseEntity<Ticket> newTicket(@RequestBody Ticket ticket) {
		try {
			Application application = new Application();
			application = applicationService.findApplication(ticket.getApplication().getId());
			ticket.setApplication(application);
			Release release = new Release();
			release = releaseService.findRelease(ticket.getRelease().getId());
			ticket.setRelease(release);
			return new ResponseEntity<Ticket>(ticketService.newTicket(ticket), HttpStatus.CREATED);
		} catch (TicketNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

	@RequestMapping(value = "/v1/tickets/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Ticket> updateTicket(@PathVariable long id, @RequestBody Ticket ticket) {
		try {
			Application application = new Application();
			application = applicationService.findApplication(ticket.getApplication().getId());
			ticket.setApplication(application);
			Release release = new Release();
			release = releaseService.findRelease(ticket.getRelease().getId());
			ticket.setRelease(release);
			return new ResponseEntity<Ticket>(ticketService.updateTicket(id, ticket), HttpStatus.OK);
		} catch (TicketNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

	@RequestMapping(value = "/v1/tickets/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteTicket(@PathVariable long id) {
		try {
			return new ResponseEntity<String>(ticketService.deleteTicket(id), HttpStatus.OK);
		} catch (TicketNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

	@GetMapping("/v1/releases")
	public ResponseEntity<List<Release>> retrieveReleases() {
		return new ResponseEntity<List<Release>>(releaseService.listReleases(), HttpStatus.OK);
	}

	@GetMapping("/v1/releases/{id}")
	public ResponseEntity<Release> getRelease(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<Release>(releaseService.findRelease(id), HttpStatus.OK);
		} catch (ReleaseNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

	@PostMapping("/v1/releases")
	public ResponseEntity<Release> newRelease(@RequestBody Release release) {
		try {
			return new ResponseEntity<Release>(releaseService.newRelease(release), HttpStatus.CREATED);
		} catch (ReleaseNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

	@RequestMapping(value = "/v1/releases/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Release> updateRelease(@PathVariable long id, @RequestBody Release release) {
		try {
			return new ResponseEntity<Release>(releaseService.updateRelease(id, release), HttpStatus.OK);
		} catch (ReleaseNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

	@RequestMapping(value = "/v1/releases/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteRelease(@PathVariable long id) {
		try {
			return new ResponseEntity<String>(releaseService.deleteRelease(id), HttpStatus.OK);
		} catch (ReleaseNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

	@GetMapping("/v1/applications")
	public ResponseEntity<List<Application>> retrieveApplications() {
		return new ResponseEntity<List<Application>>(applicationService.listApplications(), HttpStatus.OK);
	}

	@GetMapping("/v1/applications/{id}")
	public ResponseEntity<Application> getApplication(@PathVariable("id") long id) {
		try {
			return new ResponseEntity<Application>(applicationService.findApplication(id), HttpStatus.OK);
		} catch (ApplicationNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

	@PostMapping("/v1/applications")
	public ResponseEntity<Application> newApplication(@RequestBody Application application) {
		try {
			return new ResponseEntity<Application>(applicationService.newApplication(application), HttpStatus.CREATED);
		} catch (ApplicationNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

	@RequestMapping(value = "/v1/applications/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Application> updateApplication(@PathVariable long id, @RequestBody Application application) {
		try {
			return new ResponseEntity<Application>(applicationService.updateApplication(id, application),
					HttpStatus.OK);
		} catch (ApplicationNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

	@RequestMapping(value = "/v1/applications/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteApplication(@PathVariable long id) {
		try {
			return new ResponseEntity<String>(applicationService.deleteApplication(id), HttpStatus.OK);
		} catch (ApplicationNotFoundException exception) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
		}
	}

}
