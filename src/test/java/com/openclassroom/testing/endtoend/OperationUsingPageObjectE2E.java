package com.openclassroom.testing.endtoend;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.bonigarcia.wdm.WebDriverManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OperationUsingPageObjectE2E {

	@LocalServerPort
	private Integer port;

	private String baseUrl;
	private WebDriver webDriver = null;

	@BeforeAll
	public static void setUpFirefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
	}

	@BeforeEach
	public void setUpWebDriver() {
		webDriver = new FirefoxDriver();
		baseUrl = "http://localhost:" + port + "/calculator";
	}

	@AfterEach
	public void quitWebDriver() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

	@Test
	public void givenPageObject_aStudentCanUseTheCalculatorToMultiplyFourByEight() {

		// GIVEN
		webDriver.get(baseUrl);
		final CalculatorPageObject pageObject = new CalculatorPageObject(webDriver);

		// WHEN
		final String solution = pageObject.multiply("4", "8");

		// THEN
		assertThat(solution).isEqualTo("32");
	}

	@Test
	public void givenPageObject_aStudentCanUseTheCalculatorToAddOneToOne() throws InterruptedException {

		// GIVEN
		webDriver.get(baseUrl);
		final CalculatorPageObject pageObject = new CalculatorPageObject(webDriver);

		// WHEN
		final String solution = pageObject.add("1", "1");

		// THEN
		assertThat(solution).isEqualTo("2");
	}
}
