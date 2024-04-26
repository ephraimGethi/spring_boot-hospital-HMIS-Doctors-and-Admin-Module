package com.Hospital.Management.System.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.AttributeNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.Hospital.Management.System.entity.Patient;
import com.Hospital.Management.System.repository.PatientRepository;

import jakarta.persistence.EntityNotFoundException;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v1")
public class PatientController {
	
private PatientRepository patientRepository;

public PatientController(PatientRepository patientRepository) {
	super();
	this.patientRepository = patientRepository;
}
@PostMapping("/patients")
public Patient insert(@RequestBody Patient patient) {
return patientRepository.save(patient);	
}
@GetMapping("/patients")
public List<Patient> getPatients(){
	return patientRepository.findAll();
}
@DeleteMapping("/patients/{id}")
public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable Long id) throws AttributeNotFoundException{
	Patient patient = patientRepository.findById(id).orElseThrow(
			()->new AttributeNotFoundException("entity not found of id" + id));
	patientRepository.delete(patient);
	Map<String,Boolean> response = new HashMap<>();
	response.put("response okay", Boolean.TRUE);
	return ResponseEntity.ok(response);	
}
@GetMapping("/patients/{id}")
public ResponseEntity<Patient> getpatientById(@PathVariable Long id) throws AttributeNotFoundException{
	Patient patient = patientRepository.findById(id).orElseThrow(
			()->new AttributeNotFoundException("entity not found of id" + id));
	return ResponseEntity.ok(patient);
}
@PutMapping("/patients/{id}")
public ResponseEntity<Patient> updatePatient(@RequestBody Patient patientdetails,@PathVariable Long id) throws AttributeNotFoundException{
	Patient patient = patientRepository.findById(id).orElseThrow(
			()->new AttributeNotFoundException("entity not found of id" + id));
	
	patient.setAge(patientdetails.getAge());
	patient.setName(patientdetails.getName());
	patient.setBlood(patientdetails.getBlood());
	patient.setPrescription(patientdetails.getPrescription());
	patient.setDose(patientdetails.getDose());
	patient.setFees(patientdetails.getFees());
	patient.setUrgency(patientdetails.getUrgency());
	
	Patient UpdatedPatient = patientRepository.save(patient);
	return ResponseEntity.ok(UpdatedPatient);
	
}
}
