package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class OrangeHRMLoginTest extends BaseTest {

	@Test
	public void validLoginTest() {
	    driver.get("https://opensource-demo.orangehrmlive.com/");

	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.enterUsername("Admin");
	    loginPage.enterPassword("admin123");
	    loginPage.clickLogin();

	    // WAIT for dashboard
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("dashboard"));

	    Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"),
	            "Login failed!");
	}

	@Test
	public void invalidPasswordTest() {
	    driver.get("https://opensource-demo.orangehrmlive.com/");

	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.enterUsername("Admin");
	    loginPage.enterPassword("wrongpassword");
	    loginPage.clickLogin();

	    Assert.assertTrue(driver.getCurrentUrl().contains("login"),
	            "Login should fail with invalid password");
	}

	@Test
	public void emptyCredentialsTest() {
	    driver.get("https://opensource-demo.orangehrmlive.com/");

	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.clickLogin();

	    Assert.assertTrue(driver.getCurrentUrl().contains("login"),
	            "Login should not proceed with empty credentials");
	}
	@Test
	public void logoutTest() {
	    driver.get("https://opensource-demo.orangehrmlive.com/");

	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.enterUsername("Admin");
	    loginPage.enterPassword("admin123");
	    loginPage.clickLogin();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("dashboard"));

	    driver.findElement(By.cssSelector("span.oxd-userdropdown-tab")).click();
	    driver.findElement(By.linkText("Logout")).click();

	    Assert.assertTrue(driver.getCurrentUrl().contains("login"),
	            "Logout failed!");
	}
	@Test
	public void invalidUsernameTest() {
	    driver.get("https://opensource-demo.orangehrmlive.com/");

	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.enterUsername("WrongUser");
	    loginPage.enterPassword("admin123");
	    loginPage.clickLogin();

	    Assert.assertTrue(driver.getCurrentUrl().contains("login"),
	            "Login should fail with invalid username");
	}

	
    }

