package com.nateebling.soloproject.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nateebling.soloproject.models.Job;
import com.nateebling.soloproject.services.JobService;
import com.nateebling.soloproject.services.UserService;

@Controller
public class JobController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JobService jobService;
	
	// GET Requests
	@GetMapping("/home")
    public String dashboardPage(HttpSession session, Model model) {
    	
    	if(session.getAttribute("id") == null) {
    		return "redirect:/logout";
    	}
    	
    	Long userId = (Long) session.getAttribute("id");
    	model.addAttribute("thisUser", userService.findUserById(userId));
    	model.addAttribute("jobs", jobService.getAllJobs());
    	return "dashboard.jsp";    		
    }
	
	@GetMapping("/addJob")
	public String newJobPage(HttpSession session, @ModelAttribute("newJob") Job newJob, Model model) {
		if(session.getAttribute("id") == null) {
    		return "redirect:/logout";
    	}
		Long userId = (Long) session.getAttribute("id");
    	model.addAttribute("thisUser", userService.findUserById(userId));
		return "newjob.jsp";
	}
	
	@GetMapping("/view/{id}")
	public String showOneJob(HttpSession session, @PathVariable("id") Long id, Model model) {
		if(session.getAttribute("id") == null) {
    		return "redirect:/logout";
    	}
		Long userId = (Long) session.getAttribute("id");
		model.addAttribute("userId", userId);
		model.addAttribute("thisJob", jobService.getOneJobByID(id));
		return "showonejob.jsp";
	}
	
	@GetMapping("/edit/{id}")
	public String editOneJob(HttpSession session, @PathVariable("id") Long id, Model model) {
		if(session.getAttribute("id") == null) {
    		return "redirect:/logout";
    	}
		Long userId = (Long) session.getAttribute("id");
    	model.addAttribute("thisUser", userService.findUserById(userId));
		model.addAttribute("thisJob", jobService.getOneJobByID(id));
		return "editjob.jsp";
	}
	// POST Requests (and PUT)
	@PostMapping("/jobs/new")
	public String createJobInDB(@Valid @ModelAttribute("newJob") Job newJob, BindingResult result) {
		if(result.hasErrors()) {
			return "newjob.jsp";
		}
		jobService.createJob(newJob);
		return "redirect:/home";
	}
	
	@PutMapping("/jobs/{id}/edit")
	public String editGameInDB(@Valid @ModelAttribute("thisJob") Job updatedJob, BindingResult result, @PathVariable("id") Long id) {
		if(result.hasErrors()) {
			return "editjob.jsp";
		}
		jobService.updateJob(updatedJob);
		return "redirect:/home";
	}
	
	@GetMapping("/acceptJob/{id}")
	public String accpetJob(HttpSession session, @PathVariable("id") Long id) {
		if(session.getAttribute("id") == null) {
    		return "redirect:/logout";
    	}
		Job thisJob = jobService.getOneJobByID(id);
		thisJob.setIsClaimed(true);
		thisJob.setClaimedBy((Long) session.getAttribute("id"));
		jobService.updateJob(thisJob);
		return "redirect:/home";
	}
	// DELETE Requests
	@RequestMapping(value="/delete/{id}", method={RequestMethod.GET, RequestMethod.DELETE})
	public String deleteGameFromDB(@PathVariable("id") Long id) {
		jobService.deleteJob(id);
		return "redirect:/home";
	}
	
}
