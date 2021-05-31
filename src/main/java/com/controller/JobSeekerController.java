package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.CV;
import com.model.User;
import com.service.JobSeekerService;
import com.service.UserRegistrationService;

/**
 * 
 * Login Controller
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/jobSeeker/")
public class JobSeekerController {

	@Autowired
	JobSeekerService jobSeekerService;

	@GetMapping("get-cv")
	public CV getCv(@RequestParam int userId) {
		CV cv = null;
		cv = jobSeekerService.getCV(userId);
		return cv;
	}

	@PostMapping("add-cv")
	public Response addCV(@RequestBody String cvJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = null;
		try {
			CV cv = objectMapper.readValue(cvJson, CV.class);
			int cvID = jobSeekerService.addCV(cv);
			if (cvID != 0) {
				response = new Response(200, cvID, "CV added sucessfully", null);
			} else {
				response = new Response(204, cvID, "Operation is failed", null);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new Response(500, 0, e.getMessage(), null);
		}
		return response;
	}

	@PutMapping("update-cv")
	public Response updateCV(@RequestBody String cvJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = null;
		try {
			CV cv = objectMapper.readValue(cvJson, CV.class);
			int cvID = jobSeekerService.updateCV(cv);
			if (cvID != 0) {
				response = new Response(200, cvID, "CV updated sucessfully", null);
			} else {
				response = new Response(204, cvID, "Operation is failed", null);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new Response(500, 0, e.getMessage(), null);
		}
		return response;
	}

	@DeleteMapping("delete-cv")
	public Response deleteCV(@RequestParam int cv_id) {
		Response response = null;
		try {
			boolean isDeleted = jobSeekerService.deleteCv(cv_id);
			if (isDeleted) {
				response = new Response(200, cv_id, "CV deleted sucessfully", null);
			} else {
				response = new Response(204, cv_id, "Operation is failed", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new Response(500, 0, e.getMessage(), null);
		}
		return response;

	}
	
	


}
