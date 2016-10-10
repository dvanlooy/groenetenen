package be.vdab.web;
// enkele imports

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FoutController {
	public static final String VIEW = "fout";

	@ExceptionHandler(Exception.class)
	public String foutPagina() {
		return VIEW;
	}
}