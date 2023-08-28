package com.openclassroom.testing.endtoend;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import io.github.bonigarcia.wdm.WebDriverManager;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // L'application sera démarrée par un navigateur
public class MultiplicationJourneyE2E { // JourneyE2E = parcours end to end

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
	public void givenBaseUrl_aStudentCanUseTheCalculatorToMultiplyTwoBySixteen() {

		// GIVEN
		webDriver.get(baseUrl);
		final WebElement leftField = webDriver.findElement(By.id("leftArgument"));
		final WebElement typeDropdown = webDriver.findElement(By.id("calculationType"));
		final WebElement rightField = webDriver.findElement(By.id("rightArgument"));
		final WebElement submitButton = webDriver.findElement(By.id("submit"));

		// WHEN
		leftField.sendKeys("2");
		typeDropdown.sendKeys("x");
		rightField.sendKeys("16");
		submitButton.click();

		// THEN
		final WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		final WebElement solutionElement = waiter.until(ExpectedConditions.presenceOfElementLocated(By.id("solution")));
		assertThat(solutionElement.getText()).isEqualTo("32");
	}
}
