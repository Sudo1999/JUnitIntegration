package com.openclassroom.testing.endtoend;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPageObject {

	public static final String ADDITION_SYMBOL = "+";
	public static final String SOUSTRACTION_SYMBOL = "-";
	public static final String MULTIPLICATION_SYMBOL = "x";
	public static final String DIVISION_SYMBOL = "/";

	@FindBy(id = "leftArgument")
	private WebElement leftArgument;

	@FindBy(id = "calculationType")
	private WebElement calculationType;

	@FindBy(id = "rightArgument")
	private WebElement rightArgument;

	@FindBy(id = "submit")
	private WebElement submitButton;

	@FindBy(id = "solution")
	private WebElement solution;

	private final WebDriver webDriver;

	public CalculatorPageObject(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this); // Ce this représente une instance de CalculatorPageObject
	}

	public String add(String leftValue, String rightValue) {
		return calculate(ADDITION_SYMBOL, leftValue, rightValue);
	}

	public String multiply(String leftValue, String rightValue) {
		return calculate(MULTIPLICATION_SYMBOL, leftValue, rightValue);
	}

	public String subtract(String leftValue, String rightValue) {
		return calculate(SOUSTRACTION_SYMBOL, leftValue, rightValue);
	}

	public String divide(String leftValue, String rightValue) {
		return calculate(DIVISION_SYMBOL, leftValue, rightValue);
	}

	private String calculate(String typeValue, String leftValue, String rightValue) {
		leftArgument.sendKeys(leftValue);
		calculationType.sendKeys(typeValue);
		rightArgument.sendKeys(rightValue);
		submitButton.click();

		final WebDriverWait waiter = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		final WebElement solutionElement = waiter.until(ExpectedConditions.visibilityOf(solution));

		return solutionElement.getText();
	}
}
