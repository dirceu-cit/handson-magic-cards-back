package magiccards.controllers;

import magiccards.entities.Card;
import magiccards.entities.Expansion;
import magiccards.repositories.CardRepository;
import magiccards.repositories.ExpansionRepository;
import magiccards.services.ExpansionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("expansions")
public class ExpansionsController {

	@Autowired
	private ExpansionRepository expansionRepository;

	@Autowired
	private ExpansionService expService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	public Expansion getExpansionById(@PathVariable("id") String expansionId) {
		Integer id = Integer.parseInt(expansionId);
		return expansionRepository.findOne(id);

	}

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public Page<Expansion> getExpansions(@RequestParam(value="page")int pageNumber, @RequestParam int size) {

		Pageable page = new PageRequest(pageNumber, size);

		return expansionRepository.findAll(page);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
	public void create(@RequestBody Expansion exp) {
		expansionRepository.save(exp);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = "application/json")
	public void update(@RequestBody Expansion exp) {
		expansionRepository.save(exp);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id")String expansionId) {
		Integer id = Integer.parseInt(expansionId);
        expService.deleteCardsRelated(id);
		expansionRepository.delete(id);
	}
}