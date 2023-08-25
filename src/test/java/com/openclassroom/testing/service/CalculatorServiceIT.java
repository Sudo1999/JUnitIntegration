package com.openclassroom.testing.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.openclassroom.testing.domain.Calculator;
import com.openclassroom.testing.model.CalculationModel;
import com.openclassroom.testing.model.CalculationType;

public class CalculatorServiceIT {

	// Mise en place d'objets réels non mockés
	private final Calculator calculator = new Calculator();
	private final SolutionFormatter formatter = new SolutionFormatterImplementation();

	// Initialisation de la classe à tester
	private final CalculatorService service = new CalculatorServiceImplementation(calculator, formatter);

	@Test
	void testCalculate_shouldCalculateASolution_whenGivenACalculationModel() {

		// WHEN
		final int result = service.calculate(new CalculationModel(CalculationType.ADDITION, 1, 2)).getSolution();

		// THEN
		assertThat(result).isEqualTo(3);
	}
}
