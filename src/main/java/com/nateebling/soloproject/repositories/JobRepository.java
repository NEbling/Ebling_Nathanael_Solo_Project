package com.nateebling.soloproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nateebling.soloproject.models.Job;

@Repository
public interface JobRepository extends CrudRepository<Job, Long> {
	List<Job> findAll();
}

