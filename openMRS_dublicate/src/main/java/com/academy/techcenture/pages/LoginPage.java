package com.academy.techcenture.pages;

import com.academy.techcenture.config.ConfigReader;
import com.academy.techcenture.utilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPage {
    private SoftAssert softAssert;

    private static WebDriver driver;
    public LoginPage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//ul[@id='sessionLocation']/li")
    private List<WebElement> locations;

    @FindBy(id="loginButton")
    private WebElement loginBtn;

    @FindBy(id="cantLogin")
    private WebElement cantLogInBtn;

    @FindBy(xpath = "//label[@for='username']")
    private WebElement usernameLabel;

    @FindBy(xpath = "//label[@for='password']")
    private WebElement passwordLabel;

    @FindBy(id="username")
    private WebElement userNameInput;

    @FindBy(id="password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@placeholder='Enter your username']")
    private WebElement userNameInputText;

    @FindBy(xpath = "//input[@placeholder='Enter your password']")
    private WebElement passwordInputText;

    private void verifyLoginInput() throws InterruptedException {
        Thread.sleep(1000);
//        assertEquals(userNameInputText.getText().trim().toLowerCase(),"enter your username");
//        assertEquals(passwordInputText.getText().trim().toLowerCase(),"enter your password");
        softAssert.assertTrue(userNameInput.isEnabled(),"userName is not enable");
        softAssert.assertTrue(passwordInput.isEnabled(),"password is not enabled");
    }

    //3
    public void logIn() throws InterruptedException {
        verifyLocations();
        verifyTitle();
        verifyCantLoginBtn();
        verifyLoginBtn();
        verifyUserNameLabel();
        verifyLoginInput();
        enterUserName(ConfigReader.getProperty("username"));
        enterPassword(ConfigReader.getProperty("password"));
        selectLocation();
        loginBtn.click();

    }
    private void selectLocation(){
        driver.findElement(By.xpath("//li[@value='"+ Utilities.generalRandomNumber(2,7) +"']")).click();
    }
    private String enterUserName(String userName){
        userNameInput.clear();
        userNameInput.sendKeys(userName);
        return userName;
    }
    private String enterPassword(String password){
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return password;
    }


    //2.5
    private void verifyUserNameLabel(){
        softAssert.assertTrue(usernameLabel.isDisplayed(),"UserName label is not displayed");
        softAssert. assertTrue(passwordLabel.isDisplayed(),"Password label is not displayed");
    }


    //2.4
    private void verifyCantLoginBtn(){
        softAssert. assertTrue(cantLogInBtn.isDisplayed(),"Cant Login Btn is not diplayed");
        softAssert.assertTrue(cantLogInBtn.isEnabled(),"Cant Login Btn is not enabled");
    }
    //2.3

    private void verifyLoginBtn(){
        softAssert.assertTrue(loginBtn.isDisplayed(),"Login Btn is not diplayed");
        softAssert.assertTrue(loginBtn.isEnabled(),"Login Btn is not enabled");
    }

    //2.2
    private  void verifyLocations(){
        String []listOfLocations = {"inpatient ward","isolation ward","laboratory","outpatient clinic","pharmacy","registration desk"};
        for (int i = 0; i <locations.size() ; i++) {
            softAssert. assertEquals(locations.get(i).getText().trim().toLowerCase(),listOfLocations[i],"Location's name dont match");
            softAssert. assertTrue((locations.get(i).isEnabled()&&locations.get(i).isDisplayed()),"Location is not displayes or enabled");
        }
    }

    public void navigateToLoginPage(){
        driver.get(ConfigReader.getProperty("url"));
    }
    //2.1
    public  void verifyTitle(){
        softAssert.assertEquals(driver.getTitle(),"Login","Title is not correct");
    }

}
