package com.academy.techcenture.pages;

import com.academy.techcenture.config.ConfigReader;

import com.academy.techcenture.utilities.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static com.academy.techcenture.utilities.Utilities.randomQuote;
import static com.academy.techcenture.utilities.Utilities.rndPhoneNumber;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PatientDetailsPage extends BasePage{

    public PatientDetailsPage(WebDriver driver, SoftAssert softAssert) {
        super(driver,softAssert);
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//div[@class='row align-items-center']")
    private List<WebElement>  topSectionsElements;

    @FindBy(xpath = "//div[@class='float-sm-right']")
    private List<WebElement> patientID;

    @FindBy(className = "icon-sticky-note")
    private WebElement stickyNoteBtn;

    @FindBy(xpath = "//textarea[@placeholder='Enter a note']")
    private WebElement note;

    @FindBy(xpath = "//span[@class='icon-ok icon-white']")
    private WebElement checkBtn;

    @FindBy(xpath = "//pre[@class='preformatted-note ng-binding']")
    private WebElement createdNote;

    @FindBy(xpath = "//div[@class='toast-container toast-position-top-right']")
    private WebElement successMessage;

    @FindBy(xpath = "//div[@class='row']")
    private List<WebElement> patientInfo;

    @FindBy(xpath = "(//ul[@class='float-left'])/li")
    private List<WebElement> generalAction;

    @FindBy(xpath = "//i[@class='icon-home small']")
    private WebElement homeBtn;

    @FindBy(xpath = "//em[text()='Patient ID']/following-sibling::span")
    private WebElement patientId;





    public void verifyPDP(HashMap<String,String> data)  {
        verifytopSectionsElements();
        clickOnStickyNote();
        enterRandomNote(data);
        verifySuccessMessage();
        verifyPatientInfo();
        verifyGeneralActions();
        data.put("id", patientId.getText());//????
        clickHomeBtn();

    }

    private void verifytopSectionsElements(){
        for (int i = 0; i < topSectionsElements.size(); i++) {
            softAssert.assertTrue(topSectionsElements.get(i).isDisplayed(),"element "+topSectionsElements.get(i)+" is not displayed");
        }

        for (int i = 0; i < patientID.size(); i++) {
            softAssert.assertTrue(patientID.get(i).isDisplayed(), "element "+patientID.get(i)+" is not displayed");
        }
    }

    private void clickOnStickyNote(){
        softAssert. assertTrue(stickyNoteBtn.isEnabled(),"StickyBtn is not enabled");
        stickyNoteBtn.click();
    }

    private void enterRandomNote(HashMap<String, String> data){
        note.sendKeys(data.get("StickyNote"));
        softAssert.assertTrue(checkBtn.isEnabled(),"check btn is not enabled");
        checkBtn.click();

        softAssert.assertTrue(createdNote.isDisplayed(),"created note is not displayed");
    }

    private void verifySuccessMessage() {

        softAssert.assertTrue(successMessage.isDisplayed(),"msg is not displayed");

    }

    private void verifyPatientInfo(){
        for (int i = 0; i < patientInfo.size(); i++) {
            softAssert.assertTrue(patientInfo.get(i).isDisplayed()," "+patientInfo.get(i)+"is not displayed");
        }
    }

    private void verifyGeneralActions(){
        for (int i = 1; i < generalAction.size(); i++) {
            softAssert.assertTrue(generalAction.get(i).isEnabled(),"generalAction Btn is not enabled");
        }
    }

    private void clickHomeBtn()  {
        homeBtn.click();
    }







}


