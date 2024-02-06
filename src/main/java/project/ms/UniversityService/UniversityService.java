package project.ms.UniversityService;

import project.ms.UniversityService.model.University;
import java.util.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

//CONTROLLER
@Service
@SpringBootApplication
@RequestMapping("/universityService")
public class UniversityService {
	private final List<University> universities = new ArrayList<>();

	
	@GetMapping("/connect")
    public String connect() {
        return "Communication Establish with UniversityService!";
    }
	
	@PostMapping("/add")
	public ResponseEntity<String> addUniversity(@RequestBody University uni) {
		universities.add(uni);
		return new ResponseEntity<>("Success: University added!", HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<University>> getAllUniversities() {
		return new ResponseEntity<>(universities, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<University> getUniversityByID(@PathVariable Long id) {
		for (University uni : universities) {
			if (uni.getId().equals(id)) {
				return new ResponseEntity<>(uni, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeUniversity(@PathVariable Long id) {
		universities.removeIf(uni -> uni.getId().equals(id));
		return new ResponseEntity<>("Success: University removed!", HttpStatus.OK);
	}
}
