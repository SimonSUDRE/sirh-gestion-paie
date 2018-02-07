package dev.paie.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralController {

	@ExceptionHandler(Throwable.class)
	public String genericErrorPage(Exception e) {
		return "error";
	}
}