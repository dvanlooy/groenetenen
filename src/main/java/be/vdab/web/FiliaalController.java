package be.vdab.web;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.services.FiliaalService;

// enkele imports ...
@Controller
@RequestMapping("/filialen")
class FiliaalController {
	private static final String FILIALEN_VIEW = "filialen/filialen";
	private static final String TOEVOEGEN_VIEW = "filialen/toevoegen";
	private static final String FILIAAL_VIEW = "filialen/filiaal";
	private static final String REDIRECT_URL_NA_TOEVOEGEN = "redirect:/filialen";
	private static final Logger LOGGER = Logger.getLogger(FiliaalController.class.getName());

	private final FiliaalService filiaalService;

	FiliaalController(FiliaalService filiaalService) {
		// Spring injecteert de parameter filiaalService met de bean die de
		// interface FiliaalService implementeert: FiliaalServiceImpl
		this.filiaalService = filiaalService;
	}

	@GetMapping
	ModelAndView findAll() {
		// return FILIALEN_VIEW;
		return new ModelAndView(FILIALEN_VIEW, "filialen", filiaalService.findAll());
	}

	@GetMapping("toevoegen")
	String createForm() {
		return TOEVOEGEN_VIEW;
	}

	@GetMapping(params = "id")
	ModelAndView read(long id) {
		ModelAndView modelAndView = new ModelAndView(FILIAAL_VIEW);
		filiaalService.read(id).ifPresent(filiaal -> modelAndView.addObject(filiaal));
		return modelAndView;
	}

	@PostMapping
	String create() {
		// later voeg je een record toe aan de database
		LOGGER.info("filiaal record toevoegen aan database");
		return REDIRECT_URL_NA_TOEVOEGEN;
	}
}