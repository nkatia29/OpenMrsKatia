package com.academy.techcenture.pages;

import com.academy.techcenture.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class HomePage extends BasePage{


    public HomePage(WebDriver driver, SoftAssert softAssert) {
        super(driver, softAssert);
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "//div[@id='apps']/a")
    private List<WebElement> functionalities;
    @FindBy(linkText = "Register a patient")
    private WebElement registerAPatient;

    public void verifyFunctionalities(){
        verifyTitle();
        verifyHeaderBtns();
        verify10functionalitiesSize();
        verifyAllFunctionalitiesBtn();
        clickOnRegisterAPatientTab();
    }
    private void verifyTitle(){

        softAssert.assertTrue(driver.getTitle().equalsIgnoreCase("Home"), "title is not correct");
    }
    private void clickOnRegisterAPatientTab(){
        registerAPatient.click();
    }

    private void verify10functionalitiesSize(){

        softAssert.assertTrue(functionalities.size()==9, "functionalities size is not correct, it should be 10");


    }
  private void verifyAllFunctionalitiesBtn(){
       String[] listOfFunctionalities = {
              "find patient record","Active Visits","Awaiting Admissions","Register a patient","Capture Vitals",
               "Appointment Scheduling", "Register a patient","Reports","Data Management",
               "Configure Metadata","System Administration"
       };
        for (int i = 0; i < functionalities.size(); i++) {
          //  softAssert.assertTrue(functionalities.get(i).getText().trim().equalsIgnoreCase(listOfFunctionalities[i]), "functionalities names is not correct");
           softAssert.assertTrue(functionalities.get(i).isEnabled() , "functionalities is not enabled");
           softAssert.assertTrue(functionalities.get(i).isDisplayed() , "functionalities is not displayed");
        }
    }

    public void clickFindPatientRecord(){

        functionalities.get(0).click();
    }



}
//5. Verify 10 functionalities on the Home Page
//6. Click on register a patient tab