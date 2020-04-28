package com.practice.TrackZilla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.practice.TrackZilla.entity.Application;
import com.practice.TrackZilla.entity.Release;
import com.practice.TrackZilla.entity.Ticket;
import com.practice.TrackZilla.exception.ApplicationNotFoundException;
import com.practice.TrackZilla.exception.ReleaseNotFoundException;
import com.practice.TrackZilla.exception.TicketNotFoundException;
import com.practice.TrackZilla.service.ApplicationService;
import com.practice.TrackZilla.service.ReleaseService;
import com.practice.TrackZilla.service.TicketService;

@Controller
public class TrackZillaController {

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private ReleaseService releaseService;

	@GetMapping("/applications")
	public String retrieveApplications(Model model) {
		model.addAttribute("applications", applicationService.listApplications());
		return "applications";
	}

	@GetMapping("/applications/new")
	public String newApplications(Model model) {
		model.addAttribute("application", new Application());
		return "newApplication";
	}

	@PostMapping("/applications/Application")
	public String postNewApplications(@ModelAttribute("application") Application application, Model model) {
		applicationService.newApplication(application);
		model.addAttribute("applications", applicationService.listApplications());
		return "redirect:/applications";
	}

	@GetMapping(value = "/applications/edit/{Id}")
	public String editApplications(@PathVariable(value = "Id") long id, Model model) {
		Application application = applicationService.findApplication(id);
		model.addAttribute("application", application);
		model.addAttribute("id", application.getId());
		return "editApplication";
	}
	
	@PostMapping(value = "/applications/updateApplication/{Id}")
	public String updateApplications(@ModelAttribute("application") Application application,
			@PathVariable(value = "Id") long id,
			Model model) {
		applicationService.updateApplication(id, application);
		model.addAttribute("applications", applicationService.listApplications());
		return "redirect:/applications";
	}
	
	@GetMapping(value = "/applications/delete/{Id}")
	public String deleteApplications(@PathVariable(value = "Id") long id, Model model) {
		List<Ticket> tickets = applicationService.findApplication(id).getTicket();
		for (Ticket ticket : tickets) {
			ticketService.deleteTicket(ticket.getId());
		}
		applicationService.deleteApplication(id);
		model.addAttribute("applications", applicationService.listApplications());
		return "redirect:/applications";
	}

	@GetMapping("/tickets")
	public String retrieveTickets(Model model) {
		model.addAttribute("tickets", ticketService.listTickets());
		return "tickets";
	}

	@GetMapping("/tickets/new")
	public String newTickets(Model model) {
		model.addAttribute("ticket", new Ticket());
		return "newTicket";
	}
	
	@PostMapping("/tickets/Ticket")
	public String postNewTickets(@ModelAttribute("ticket") Ticket ticket,
			@RequestParam(value = "application_id", required = true) String applicationId,
			@RequestParam(value = "release_id", required = true) String releaseId, Model model) {
		Application application = new Application();
		application = applicationService.findApplication(Long.parseLong(applicationId));
		Release release = new Release();
		release = releaseService.findRelease(Long.parseLong(releaseId));
		ticket.setApplication(application);
		ticket.setRelease(release);
		ticketService.newTicket(ticket);
		model.addAttribute("tickets", ticketService.listTickets());
		return "redirect:/tickets";
	}
	
	@GetMapping(value = "/tickets/edit/{Id}")
	public String editTickets(@PathVariable(value = "Id") long id, Model model) {
		Ticket ticket = ticketService.findTicket(id);
		model.addAttribute("ticket", ticket);
		model.addAttribute("id", ticket.getId());
		return "editTicket";
	}

	@PostMapping(value = "/tickets/updateTicket/{Id}")
	public String updateTickets(@ModelAttribute("ticket") Ticket ticket, 
			@PathVariable(value = "Id") long id,
			Model model) {
		Application application = new Application();
		application = applicationService.findApplication(ticket.getApplication().getId());
		ticket.setApplication(application);
		Release release = new Release();
		release = releaseService.findRelease(ticket.getRelease().getId());
		ticket.setRelease(release);
		ticketService.updateTicket(id, ticket);
		model.addAttribute("tickets", ticketService.listTickets());
		return "redirect:/tickets";
	}
	
	@GetMapping("/tickets/delete/{Id}")
	public String deleteTickets(@PathVariable(value = "Id") long id, Model model) {
		ticketService.deleteTicket(id);
		model.addAttribute("tickets", ticketService.listTickets());
		return "redirect:/tickets";
	}

	@GetMapping("/releases")
	public String retrieveReleases(Model model) {
		model.addAttribute("releases", releaseService.listReleases());
		return "releases";
	}

	@GetMapping("/releases/new")
	public String newReleases(Model model) {
		model.addAttribute("release", new Release());
		return "newRelease";
	}

	@PostMapping("/releases/Release")
	public String postNewReleases(@ModelAttribute("release") Release release, Model model) {
		releaseService.newRelease(release);
		model.addAttribute("releases", releaseService.listReleases());
		return "redirect:/releases";
	}
	
	@GetMapping(value = "/releases/edit/{Id}")
	public String editReleases(@PathVariable(value = "Id") long id, Model model) {
		Release release = releaseService.findRelease(id);
		model.addAttribute("release", release);
		model.addAttribute("id", release.getId());
		return "editRelease";
	}
	
	@PostMapping(value = "/releases/updateRelease/{Id}")
	public String updateReleases(@ModelAttribute("release") Release release,
			@PathVariable(value = "Id") long id, Model model) {
		releaseService.updateRelease(id, release);
		model.addAttribute("releases", releaseService.listReleases());
		return "redirect:/releases";
	}
	
	@GetMapping("/releases/delete/{Id}")
	public String deleteReleases(@PathVariable(value = "Id") long id, Model model) {
		List<Ticket> tickets = releaseService.findRelease(id).getTicket();
		for (Ticket ticket : tickets) {
			ticketService.deleteTicket(ticket.getId());
		}
		releaseService.deleteRelease(id);
		model.addAttribute("releases", releaseService.listReleases());
		return "redirect:/releases";
	}
	
	@ExceptionHandler({ApplicationNotFoundException.class})
	public ModelAndView getSuperheroesUnavailable(ApplicationNotFoundException ex) {
	    return new ModelAndView("index", "error", ex.getMessage());
	}
	
	@ExceptionHandler({ReleaseNotFoundException.class})
	public ModelAndView getSuperheroesUnavailable(ReleaseNotFoundException ex) {
	    return new ModelAndView("index", "error", ex.getMessage());
	}
	
	@ExceptionHandler({TicketNotFoundException.class})
	public ModelAndView getSuperheroesUnavailable(TicketNotFoundException ex) {
	    return new ModelAndView("index", "error", ex.getMessage());
	}
}
