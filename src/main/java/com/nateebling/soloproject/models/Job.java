package com.nateebling.soloproject.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="jobs")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Title is required")
	@Size(min=3, message="Title must be at least 3 characters")
	private String title;
	
	@NotBlank(message="Description is required")
	@Size(min=10, message="Description must be at least 10 characters")
	private String description;
	
	@NotBlank(message="Location is required")
	private String location;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User postedBy;
	
	private Boolean isClaimed;
	
	private Long claimedBy;
		
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
		this.updatedAt = new Date(); 
	}
	
	public Job() {

	}

	public Job(
			@NotBlank(message = "Title is required") @Size(min = 3, message = "Title must be at least 3 characters") String title,
			@NotBlank(message = "Description is required") @Size(min = 10, message = "Description must be at least 10 characters") String description,
			@NotBlank(message = "Location is required") String location, User postedBy, Boolean isClaimed,
			Long claimedBy) {
		super();
		this.title = title;
		this.description = description;
		this.location = location;
		this.postedBy = postedBy;
		this.isClaimed = isClaimed;
		this.claimedBy = claimedBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}

	public Boolean getIsClaimed() {
		return isClaimed;
	}

	public void setIsClaimed(Boolean isClaimed) {
		this.isClaimed = isClaimed;
	}

	public Long getClaimedBy() {
		return claimedBy;
	}

	public void setClaimedBy(Long claimedBy) {
		this.claimedBy = claimedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
