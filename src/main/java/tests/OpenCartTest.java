package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class OpenCartTest extends BaseTest {

    WebDriverWait wait;

    @Test
    public void openHomePageTest() {
        driver.get("https://opencart.abstracta.us/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.titleContains("Your Store"));
        Assert.assertTrue(driver.getTitle().contains("Your Store"));
    }


    @Test
    public void searchProductTest() {
        driver.get("https://opencart.abstracta.us/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("search")));
        searchBox.sendKeys("MacBook");

        driver.findElement(By.cssSelector("button.btn.btn-default")).click();

        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.linkText("MacBook")));
        Assert.assertTrue(result.isDisplayed());
    }

    @Test
    public void addToCartTest() {
        driver.get("https://opencart.abstracta.us/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("search")));
        searchBox.sendKeys("MacBook");

        driver.findElement(By.cssSelector("button.btn.btn-default")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@onclick,'cart.add')]"))).click();

        WebElement successMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));

        Assert.assertTrue(successMsg.getText().contains("Success"));
    }

    @Test
    public void removeFromCartTest() {
        driver.get("https://opencart.abstracta.us/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("search")));
        searchBox.sendKeys("MacBook");

        driver.findElement(By.cssSelector("button.btn.btn-default")).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@onclick,'cart.add')]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("cart-total"))).click();
       // wait.until(ExpectedConditions.elementToBeClickable(By.title("Remove"))).click();
       // wait.until(ExpectedConditions.elementToBeClickable(By.title("Remove"))).click();

        Assert.assertTrue(driver.getPageSource().contains("Your shopping cart is empty"));
    }
}

