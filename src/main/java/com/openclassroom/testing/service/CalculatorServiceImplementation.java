package com.openclassroom.testing.service;

import javax.inject.Named;

import org.springframework.stereotype.Component;

import com.openclassroom.testing.domain.Calculator;
import com.openclassroom.testing.model.CalculationModel;
import com.openclassroom.testing.model.CalculationType;

@Named
@Component
public class CalculatorServiceImplementation implements CalculatorService {

	private Calculator calculator;
	private SolutionFormatter formatter;

	public CalculatorServiceImplementation(Calculator calculator, SolutionFormatter formatter) {
		this.calculator = calculator;
		this.formatter = formatter;
	}

	@Override
	public CalculationModel calculate(CalculationModel calculationModel) {

		final CalculationType type = calculationModel.getType();
		Integer response = null;

		switch (type) {
		case ADDITION:
			response = calculator.add(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case SOUSTRACTION:
			response = calculator.subtract(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case MULTIPLICATION:
			response = calculator.multiply(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case DIVISION:
			response = calculator.divide(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		default:
			throw new UnsupportedOperationException("Unsupported calculation");
		}

		calculationModel.setSolution(response);
		calculationModel.setFormattedSolution(formatter.format(response));
		return calculationModel;
	}
}
