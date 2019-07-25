package magiccards.controllers;

import magiccards.entities.Expansion;
import magiccards.repositories.ExpansionRepository;
import magiccards.responses.StandardResponseId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("expansions")
public class ExpansionsController {

	private static final String ID = "{id}";

	@Autowired
	private ExpansionRepository expansionRepository;

	@GetMapping(value = ID)
	public Expansion getExpansionById(@PathVariable("id") int expansionId) {
		return expansionRepository.findOne(expansionId);
	}

	@GetMapping
	public Page<Expansion> getExpansions(@RequestParam(value="page")int pageNumber, @RequestParam int size) {
		Pageable page = new PageRequest(pageNumber, size);
		return expansionRepository.findAll(page);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StandardResponseId> create(@RequestBody Expansion expansion) {
		expansionRepository.save(expansion);
		return ResponseEntity.status(HttpStatus.CREATED).body(new StandardResponseId(String.valueOf(expansion.getExpansionId())));
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@RequestBody Expansion expansion) {
		expansionRepository.save(expansion);
		return ResponseEntity.accepted().build();
	}

	@DeleteMapping(value = ID)
	public void delete(@PathVariable("id")int id) {
		expansionRepository.delete(id);
	}
}