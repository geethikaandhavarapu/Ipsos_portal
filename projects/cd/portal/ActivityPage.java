package com.ipsos.cd.selenium.projects.cd.portal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActivityPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By createActivityButton = By.xpath("//button[contains(text(), 'Create New Activity')]");
    private By option1 = By.xpath("//button[@aria-label='Basic Discussion']");
    private By activityNameField = By.xpath("//input[@aria-label='Activity name']");
    private By descriptionField = By.xpath("//textarea[@role='textarea']");
    private By addImage = By.xpath("//div[@class='mat-img-background ng-star-inserted']");
    private By createButton = By.xpath("//button[@aria-label='create']//span[@class='mat-button-wrapper']");
    private By cancelButton = By.xpath("//button[@aria-label='cancel']//span[@class='mat-button-wrapper']");
    private By activitySearchField = By.xpath("//input[@aria-label='Search by activity name']"); // Updated
    private By activityListContainer = By.xpath("//table[@class='activity-list']"); // Adjust based on actual table container

    // Constructor
    public ActivityPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Methods
    public void clickCreateActivityButton() {
        WebElement createButtonElement = wait.until(ExpectedConditions.elementToBeClickable(createActivityButton));
        createButtonElement.click();
    }

    public void selectOption1() {
        WebElement optionElement = wait.until(ExpectedConditions.elementToBeClickable(option1));
        optionElement.click();
    }

    public void enterActivityName(String activityName) {
        WebElement nameFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(activityNameField));
        nameFieldElement.sendKeys(activityName);
    }

    public void enterDescription(String description) {
        WebElement descriptionFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionField));
        descriptionFieldElement.sendKeys(description);
    }

    public void clickAddImage() {
        WebElement addImageElement = wait.until(ExpectedConditions.elementToBeClickable(addImage));
        addImageElement.click();
    }

    public void clickCreateButton() {
        WebElement createButtonElement = wait.until(ExpectedConditions.elementToBeClickable(createButton));
        createButtonElement.click();
    }

    public void clickCancelButton() {
        WebElement cancelButtonElement = wait.until(ExpectedConditions.elementToBeClickable(cancelButton));
        cancelButtonElement.click();
    }

    public boolean isActivityCreated(String activityName) {
        try {
            WebElement searchFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(activitySearchField));
            searchFieldElement.sendKeys(activityName);

            WebElement activityListContainerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(activityListContainer));
            WebElement activityElement = activityListContainerElement.findElement(By.xpath(".//*[contains(text(), '" + activityName + "')]"));

            return activityElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
