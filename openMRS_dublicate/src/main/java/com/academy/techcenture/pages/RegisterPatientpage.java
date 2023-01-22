package com.academy.techcenture.pages;

import com.academy.techcenture.config.ConfigReader;
import com.academy.techcenture.driver.Driver;
import com.academy.techcenture.utilities.Utilities;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import static com.academy.techcenture.utilities.Utilities.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class RegisterPatientpage extends BasePage{

    public RegisterPatientpage(WebDriver driver, SoftAssert softAssert) {
        super(driver, softAssert);

        PageFactory.initElements(driver,this);
    }

    @FindBy(name = "givenName")
    private WebElement firstName;
    @FindBy(name = "familyName")
    private WebElement lastName;

    @FindBy(id="next-button")
    private WebElement nextBtn;

    @FindBy(id = "gender-field")
    private WebElement genders;

    @FindBy(id="birthdateDay-field")
    private WebElement day;

    @FindBy(id="birthdateMonth-field")
    private WebElement month;

    @FindBy(id="birthdateYear-field")
    private WebElement year;

    @FindBy(id="birthdateYears-field")
    private WebElement estimatedYears;

    @FindBy(id="birthdateMonths-field")
    private WebElement estimatedMoths;

    @FindBy(id="address1")
    private WebElement streetName;

    @FindBy(id="cityVillage")
    private WebElement cityName;

    @FindBy(id="stateProvince")
    private WebElement stateName;

    @FindBy(id="country")
    private WebElement countryName;

    @FindBy(id="postalCode")
    private WebElement postalCodeName;

    @FindBy(name="phoneNumber")
    private WebElement phoneNumber;

    @FindBy(id="relationship_type")
    private WebElement relationship;

    @FindBy(xpath = "//input[@placeholder='Person Name']")
    private WebElement relativesName;

    @FindBy(xpath = "//div/p/span[@class='title']")
    private  List<WebElement> confirmPageElements;

    @FindBy(xpath = "//div[@id='dataCanvas']/div/p")
    private List<WebElement> value;

    @FindBy(id="cancelSubmission")
    private WebElement cancelBtn;

    @FindBy(id="submit")
    private WebElement confirmBtn;
    @FindBy(id = "address2")
    private WebElement address_2;
    @FindBy (id="checkbox-unknown-patient")
    private WebElement unidentifiedPatient;
    @FindBy(xpath = "//select[@class='required focused']")
    private WebElement genderBox;
    @FindBy(name="middleName")
    private WebElement middleName;

    public void fillOutForm(HashMap<String,String> data) throws InterruptedException {
        Thread.sleep(2_000);
        verifyTitle();
        verifyPatientRegistration(data);

        }

   private void verifyPatientRegistration (HashMap <String,String> data){
       enterFullName(data);
       selectRandomGender(data);
       if (unidentifiedPatient.isSelected()){
           clickSubmitBtn();
       }else{
           fillOutDOB(data);fillAddress(data);fillPhoneNumber(data); fillRelationship(data);clickSubmitBtn();
       }



    }


    private void enterFullName(HashMap<String,String> data ){
        if (data.get("Family Name").isEmpty()) {
            unidentifiedPatient.click();
        } else {
            firstName.clear();
            firstName.sendKeys(data.get("Given"));
            middleName.clear();
            middleName.sendKeys(data.get("Middle"));
            lastName.sendKeys(data.get("Family Name"));
            softAssert.assertTrue(nextBtn.isEnabled(), "Btn next is not enabled");
            nextBtn.click();
        }

    }

    private void verifyTitle(){
    softAssert.assertTrue(driver.getTitle().equalsIgnoreCase("OpenMRS Electronic Medical Record"), "title is not correct");

    }

    private void selectRandomGender(HashMap<String,String> data) {
       if(unidentifiedPatient.isSelected()){
           Select select = new Select(genders);
           select.selectByValue(data.get("Gender"));
           nextBtn.click();


       }else{
           Select select = new Select(genders);
           select.selectByValue(data.get("Gender"));
           nextBtn.click();
       }


    }
    private void fillOutDOB(HashMap<String,String> data) {
     if (data.get("Day").isEmpty()){
         estimatedYears.sendKeys(data.get("Estimated Years"));
         estimatedMoths.sendKeys(data.get("Estimated Month"));
        ///TODO: parse doesnt work

//        String estYears = (String.valueOf((int)Float.parseFloat(data.get("Estimated Years"))));
//        String estMonth = (String.valueOf(((int)Float.parseFloat(data.get("Estimated Month"))))); /// TODO: check this part
//         estimatedYears.sendKeys(estYears);
//         estimatedMoths.sendKeys(estMonth);

     } else {
         day.sendKeys(data.get("Day"));
         Select select = new Select(month);
         select.selectByIndex(((int)Double.parseDouble(data.get("Month"))));
         year.sendKeys(data.get("Year"));
        }
        nextBtn.click();
    }
        private void fillAddress(HashMap<String,String> data){

            streetName.sendKeys(data.get("Address"));
            address_2.sendKeys(data.get("Address 2"));
            cityName.sendKeys(data.get("City/Village"));
            stateName.sendKeys(data.get("State/Province"));
            countryName.sendKeys(data.get("Country"));
            postalCodeName.sendKeys(data.get("Postal Code"));
            nextBtn.click();
        }

    private void fillPhoneNumber(HashMap<String,String> data){
        String phNum = String.valueOf((long)(Double.parseDouble(data.get("Phone Number"))));
        phoneNumber.sendKeys(phNum);
        nextBtn.click();
    }

        private void fillRelationship(HashMap<String,String> data){
        //relationship.click();
        Select select = new Select(relationship);
        select.selectByVisibleText(data.get("RelationshipType"));
        relativesName.sendKeys(data.get("RelativesName"));
        nextBtn.click();

        }

//    private void verifyCustomerInfo(){
//
//        for (int i = 0; i < confirmPageElements.size(); i++) {
//            String coulumNamesubstr = confirmPageElements.get(i).getText().trim();
//            String columNames = coulumNamesubstr.substring(0,coulumNamesubstr.length()-1).toLowerCase(); ///  name:
//
//            String expectedValue = value.get(i).getText().trim().toLowerCase();
//            String actualValue = ConfigReader.getProperty(columNames).toLowerCase();
//
//            softAssert.assertEquals(actualValue.toLowerCase(),expectedValue,"value is not correct");
//
//        }
//
//    }

    private void clickSubmitBtn(){
        softAssert.assertTrue(cancelBtn.isEnabled(),"cancel button is not enabled");
        softAssert.assertTrue(confirmBtn.isEnabled(),"confirm button is not enabled");
        confirmBtn.click();
    }


}






    








//7. On the registration page, very the title and fill out the demographics, contact info, relationships sections
//8. Once you have filled out the sections, verify once again the patient information is correct before clicking the confirm button.