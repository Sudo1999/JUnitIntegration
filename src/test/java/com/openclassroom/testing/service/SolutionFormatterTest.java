package com.openclassroom.testing.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SolutionFormatterTest {

	private SolutionFormatter formatter;

	@Test
	void testFormat_shouldFormatSolution_forPositiveInteger() {

		// GIVEN
		formatter = new SolutionFormatterImplementation();

		// WHEN
		final String formattedNumber = formatter.format(154870500);

		// THEN
		// (Attention les espaces ne sont pas des espaces standards !!!)
		assertThat(formattedNumber).isEqualTo("154 870 500");
	}

	@Test
	void testFormat_shouldFormatSolution_forNegativeInteger() {

		// GIVEN
		formatter = new SolutionFormatterImplementation();

		// WHEN
		final String formattedNumber = formatter.format(-154870500);

		// THEN
		// (Attention les espaces ne sont pas des espaces standards !!!)
		assertThat(formattedNumber).isEqualTo("-154 870 500");
	}
}
