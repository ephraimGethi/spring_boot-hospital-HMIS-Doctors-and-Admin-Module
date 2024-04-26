package com.Hospital.Management.System.doclogin.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.Hospital.Management.System.doclogin.entity.Medicine;
import com.Hospital.Management.System.doclogin.repository.MedicineRepository;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/v3")
public class MedicineController {
MedicineRepository medicineRepository;

public MedicineController(MedicineRepository medicineRepository) {
	super();
	this.medicineRepository = medicineRepository;
}

@PostMapping("/medicines")
public Medicine createMedicine(@RequestBody Medicine medicine) {
	return medicineRepository.save(medicine);
}

@GetMapping("/medicines")
public List<Medicine> getAllMedicine(){
	return medicineRepository.findAll();
}

@GetMapping("/medicines/{id}")
public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) throws AttributeNotFoundException{
	Medicine medicine = medicineRepository.findById(id).orElseThrow(()->new AttributeNotFoundException("attribbute not found"+id));
	return ResponseEntity.ok(medicine);
}

@PutMapping("/medicines/{id}")
public ResponseEntity<Medicine> updateMedicine(@PathVariable Long id,@RequestBody Medicine medicinedeatails) throws AttributeNotFoundException{
	Medicine medicine = medicineRepository.findById(id).orElseThrow(()->
	new AttributeNotFoundException("attribbute not found"+id));
	
	medicine.setDrugName(medicinedeatails.getDrugName());
	medicine.setId(medicinedeatails.getId());
	medicine.setStock(medicinedeatails.getStock());
	
Medicine updatedMedicine = medicineRepository.save(medicine);
return ResponseEntity.ok(updatedMedicine);
}
@CrossOrigin(origins = "http://localhost:4200/")
@DeleteMapping("/medicines/{id}")
public ResponseEntity<Map<String, Boolean>> deleteMedicine(@PathVariable Long id) throws AttributeNotFoundException{
	Medicine medicine = medicineRepository.findById(id).orElseThrow(()->
	new AttributeNotFoundException("attribbute not found"+id));
	
	medicineRepository.delete(medicine);
	
	Map<String, Boolean> response = new HashMap<>();
	response.put("deleted", Boolean.TRUE);
	return ResponseEntity.ok(response);
}
}
