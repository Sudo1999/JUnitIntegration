package com.openclassroom.testing.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.openclassroom.testing.domain.Calculator;
import com.openclassroom.testing.service.CalculatorService;
import com.openclassroom.testing.service.SolutionFormatter;

@WebMvcTest(controllers = { CalculatorController.class, CalculatorService.class }) // Beans initialisés par Spring
@ExtendWith(SpringExtension.class) // SpringExtension assiste JUnit comme MockitoExtension le fait dans le cas des tests unitaires
public class CalculatorControllerSIT { // SIT = System Integration Test

	@Inject // Spring va gérer les dépendances des deux classes testées (les beans de l'annotation @WebMvcTest)
	private MockMvc mockMvc; // Ces dépendances vont être mockées par l'annotation @MockBean

	@MockBean
	private Calculator calculator;
	@MockBean
	private SolutionFormatter formatter;

	@Test
	public void givenCalculator_whenRequestToAdd_thenSolutionIsShown() throws Exception {
			
		// GIVEN
		when(calculator.add(2, 3)).thenReturn(5);
		
		// WHEN
		final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/calculator")
				.param("leftArgument", "2")
				.param("rightArgument", "3")
				.param("calculationType", "ADDITION"))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
			//.andExpect(MockMvcResultMatchers.content().string("id=\"solution\""))
            //.andExpect(MockMvcResultMatchers.content().string(">5</span>"));
		
		// THEN
		assertThat(result.getResponse().getContentAsString()).contains("id=\"solution\"").contains(">5</span");
		verify(calculator).add(2, 3);
		verify(formatter).format(5);
	}
}
