package com.nateebling.soloproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nateebling.soloproject.models.Job;
import com.nateebling.soloproject.repositories.JobRepository;
import com.nateebling.soloproject.repositories.UserRepository;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	@SuppressWarnings("unused")
	@Autowired 
	private UserRepository userRepository;
	
	// Create
	
	public Job createJob(Job newJob) {
		return jobRepository.save(newJob);
	}
	
	// Read 
	
	public List<Job> getAllJobs() {
		return jobRepository.findAll();
	}
	
	public Job getOneJobByID(Long id) {
		Optional<Job> potentialJob = jobRepository.findById(id);
		
		if(potentialJob.isEmpty()) {
			return null;
		}
		else {
			return potentialJob.get();
		}
	}
	
	// Update
	public Job updateJob(Job updatedJob) {
		return jobRepository.save(updatedJob);
	}
	
	// Delete
	public void deleteJob(Long id) {
		jobRepository.deleteById(id);
	}
}


