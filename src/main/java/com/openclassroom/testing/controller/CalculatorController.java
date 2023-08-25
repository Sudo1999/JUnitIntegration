package com.openclassroom.testing.controller;

//import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassroom.testing.model.Calculation;
import com.openclassroom.testing.model.CalculationModel;
import com.openclassroom.testing.model.CalculationType;
import com.openclassroom.testing.service.CalculatorService;

@Controller
public class CalculatorController {

	public static final String CALCULATOR_TEMPLATE = "calculator";

	//@Inject
	/* You can annotate fields and constructor using @Autowired to tell Spring framework to find dependencies for you. 
	 * The @Inject annotation serves the same purpose, but the main difference between them is that @Inject is a standard 
	 * annotation for dependency injection, when @Autowired is Spring specific.
	 * => @Inject ne fonctionnant pas (Bean of type CalculatorService could not be found), @Autowired va le remplacer, 
	 * et @Component va remplacer @Named sur la classe CalculatorServiceImplementation et les deux classes qu'elle appelle.*/

	@Autowired
	CalculatorService calculatorService;

	@GetMapping("/")
	public String index(Calculation calculation) {
		return "redirect:/calculator";
	}

	@GetMapping("/calculator")
	public String root(Calculation calculation) {
		return CALCULATOR_TEMPLATE;
	}

	@PostMapping("/calculator")
	public String calculate(@Valid Calculation calculation, BindingResult bindingResult, Model model) {

		final CalculationType type = CalculationType.valueOf(calculation.getCalculationType());

		final CalculationModel calculationModel = new CalculationModel(
			type, calculation.getLeftArgument(), calculation.getRightArgument());

		final CalculationModel response = calculatorService.calculate(calculationModel);

		model.addAttribute("response", response);

		return CALCULATOR_TEMPLATE;
	}
}
