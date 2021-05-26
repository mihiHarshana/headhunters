package com.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.QualificationDao;
import com.dto.JobSeekerQualificationsDTO;
import com.dto.Response;
import com.dto.SearchQualificationRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.CV;
import com.model.Qualification;
import com.model.QualificationType;
import com.pdf.generator.GeneratePdfReport;
import com.service.QualificationService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.InputStreamResource;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/qualification/")
public class QualificationController {

	@Autowired
	QualificationService qualificationService;
	
	@Autowired
	GeneratePdfReport pdfGenerator;

	@PostMapping("add-qualification")
	public Response addQualification(@RequestBody String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = null;
		try {
			Qualification qualification = objectMapper.readValue(json, Qualification.class);
			int id = qualificationService.addQualification(qualification);
			if (id != 0) {
				response = new Response(200, id, "Qualification added sucessfully", null);
			} else {
				response = new Response(204, id, "Operation is failed", null);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new Response(500, 0, e.getMessage(), null);
		}
		return response;
	}

	@PutMapping("update-qualification")
	public Response updateQualification(@RequestBody String json) {
		ObjectMapper objectMapper = new ObjectMapper();
		Response response = null;
		try {
			Qualification qualification = objectMapper.readValue(json, Qualification.class);
			int id = qualificationService.updateQualification(qualification);
			if (id != 0) {
				response = new Response(200, id, "Qualification updated sucessfully", null);
			} else {
				response = new Response(204, id, "Operation is failed", null);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new Response(500, 0, e.getMessage(), null);
		}
		return response;
	}

	@DeleteMapping("delete-qualification")
	public Response deleteQualification(@RequestParam int id) {
		Response response = null;
		try {
			boolean isDeleted = qualificationService.deleteQualification(id);
			if (isDeleted) {
				response = new Response(200, id, "Qualification deleted sucessfully", null);
			} else {
				response = new Response(204, id, "Operation is failed", null);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new Response(500, 0, e.getMessage(), null);
		}
		return response;

	}

	@PutMapping("search-qualifications")
	public List<JobSeekerQualificationsDTO> searchBy(@RequestBody String json) {
		List<JobSeekerQualificationsDTO> jobSeekerQualificationsDTOs = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			SearchQualificationRequest searchQualificationRequest = objectMapper.readValue(json,
					SearchQualificationRequest.class);
			jobSeekerQualificationsDTOs = qualificationService.searchBy(searchQualificationRequest.qualificationTypes,
					searchQualificationRequest.searchValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobSeekerQualificationsDTOs;
	}
	
	@GetMapping("get-qualification-types")
	public List<QualificationType> getQualificationTypes() {
		List<QualificationType> qualificationTypes = null;
		try {
			qualificationTypes = qualificationService.getQualificationTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qualificationTypes;
	}
	
	@RequestMapping(value = "pdfReport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> citiesReport() {

        List<QualificationType> qualificationTypes = (List<QualificationType>) qualificationService.getQualificationTypes();

        ByteArrayInputStream bis = GeneratePdfReport.citiesReport(qualificationTypes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
	
	@GetMapping("get-user-qualifications")
	public List<Qualification> getQualificationsByUserID(@RequestParam int userId) {
		List<Qualification> qualifications = null;
		try {
			qualifications = qualificationService.getQualificationsByUserID(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return qualifications;
	}
	
}
