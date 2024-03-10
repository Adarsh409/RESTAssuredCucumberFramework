package com.qa.booker.runner;




import org.junit.runner.RunWith;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = ".//Features",
					glue = "com.qa.booker.stepdefs", 
					dryRun = false, 
					monochrome = true, 
					plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }
					//tags= "@CreateBooking or @UpdateBooking"
				)
public class TestRunner {

	
}

