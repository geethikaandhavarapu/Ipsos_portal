package com.ipsos.cd.selenium.projects.cd.portal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ActivityTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private Homepage homePage;
    private ActivityPage activityPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D://chromedriver-win64//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://portal-int.ipsos-cd-dev.com/");


        loginPage = new LoginPage(driver);
        homePage = new Homepage(driver);
        activityPage = new ActivityPage(driver);


        /*loginPage.enterEmail("Sharmila@prodifyllp.com");
        loginPage.clickNextButton();
        loginPage.enterPassword("Ipsos@2019");
        loginPage.clickLoginButton();*/
    }

    @BeforeMethod
    public void reset() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    @Test
    public void testCreateActivity() {
        loginPage.enterEmail("Sharmila@prodifyllp.com");
        loginPage.clickNextButton();
        loginPage.enterPassword("Ipsos@2019");
        loginPage.clickLoginButton();

        homePage.clickSiteNavigationButton();
        homePage.clickActiveProjectsButton();
        homePage.clickActivitiesIcon();


        activityPage.clickCreateActivityButton();
        activityPage.selectOption1();
        String activityName = "Test Activity";
        String description = "This is a test activity.";

        activityPage.enterActivityName(activityName);
        activityPage.enterDescription(description);
        activityPage.clickAddImage();
        activityPage.clickCreateButton();

        // Verify the activity is created
        Assert.assertTrue(activityPage.isActivityCreated(activityName), "Activity creation failed.");
    }

    @Test
    public void testCancelCreateActivity() {
        loginPage.enterEmail("Sharmila@prodifyllp.com");
        loginPage.clickNextButton();
        loginPage.enterPassword("Ipsos@2019");
        loginPage.clickLoginButton();
        // Navigate to activity page
        homePage.clickSiteNavigationButton();
        homePage.clickActiveProjectsButton();
        homePage.clickActivitiesIcon();


        activityPage.clickCreateActivityButton();
        activityPage.selectOption1();

        String activityName = "Test Activity";
        String description = "This is a test activity.";

        activityPage.enterActivityName(activityName);
        activityPage.enterDescription(description);
        activityPage.clickCancelButton();

        // Verify the activity was not created
        Assert.assertFalse(activityPage.isActivityCreated(activityName), "Activity was created even after cancellation.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
