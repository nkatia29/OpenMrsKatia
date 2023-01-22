package com.academy.techcenture.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;


public class FindPatientRecordPage extends BasePage{

    public FindPatientRecordPage(WebDriver driver, SoftAssert softAssert) {
        super(driver, softAssert);
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath ="(//h2[normalize-space()='Find Patient Record'])[1]")
    private WebElement findPatientRecordText;

    @FindBy(className = "form-control")
    private WebElement searchBox;

    @FindBy(xpath = "//tr[@class='odd']/td[4]")
    private WebElement age;

    @FindBy(xpath = "//tr[@class='odd']/td[3]")
    private WebElement gender;

    @FindBy(xpath = "//tr[@class='odd']/td[2]")
    private WebElement fullName;

    @FindBy(xpath = "//tr[@class='odd']/td[5]")
    private WebElement dob;

    @FindBy(xpath = "//i[@class='icon-home small']")
    private WebElement homeBtn;

    @FindBy(linkText = "Logout")
    private WebElement logOutBtn;





    public void verifyFindPatientRecord(HashMap<String, String> data)  {
        verifyTitle();
        verifyTopPageElements();
        searchById(data);
        verifyPatientInfo(data);
        clickHomeBtn();
        clickLogOut();
    }

    private void verifyPatientInfo(HashMap<String, String> data) {
        LocalDate today = LocalDate.now();
        // Today's date is 10th Jan 2022
        if(data.get("Day").isEmpty()) {
            int estimated_month = Integer.parseInt(data.get("Estimated Month"));
            int estimated_years = Integer.parseInt(data.get("Estimated Year"));
           // ??????  calculate age based on years and month
            int actualAge = Integer.parseInt(age.getText());


            softAssert.assertEquals(estimated_years, actualAge, "age is wrong");

        }else{
            int year = ((int)Double.parseDouble(data.get("Year")));
            int month = ((int)Float.parseFloat(data.get("Month")));
            int date = ((int)Double.parseDouble(data.get("Day")));
            LocalDate birthday = LocalDate.of(year, month, date); // Birth date

            Period p = Period.between(birthday, today);

            int years = p.getYears();
            int actualAge = Integer.parseInt(age.getText());
            softAssert.assertEquals(years, actualAge, "age is wrong");
        }


        softAssert.assertEquals(data.get("Gender"),gender.getText(), "Gender doesn't match");

        if(data.get("Middle").isBlank()){
            softAssert.assertEquals(data.get("Given")+" "+data.get("Family Name"),fullName.getText(), "Names don't match");
        }else{
            softAssert.assertEquals(data.get("Given")+" "+ data.get("Middle")+" "+ data.get("Family Name"),fullName.getText(), "Names don't match");
        }



//
////        if(ConfigReader.getProperty("rndNumber").equals("1")){
////           // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd mmm yyyy");
////         //   String expectedDob = dob.getText().substring(12).trim();
////            String expectedDob=dob.getText().trim();
////
////            String date = ConfigReader.getProperty("date");
////            if(Integer.parseInt(ConfigReader.getProperty("date"))<10){
////                date= "0"+date;
////            }
////
////            String actualDob = date+"."+ConfigReader.getProperty("formattedMonth")+"."+ConfigReader.getProperty("year");
////            assertEquals(actualDob,expectedDob,"DOB doesn't match");
////        }else{
////          //  String expectedDob=dob.getText().trim();
////            Month month = Month.of(13-(Integer.parseInt(ConfigReader.getProperty("rndMonth"))));
////            String convertMonth = String.valueOf(month).substring(0,3).toLowerCase();
////            String expectedDob = dob.getText().substring(5).trim().toLowerCase(); //from application
////            String year=ConfigReader.getProperty("estYears");
////            int year1 = 2022-Integer.parseInt(year);
////
////            String actualDob = convertMonth+"."+year1;  //ours
////            softAssert.assertEquals(actualDob,expectedDob,"DOB doesn't match");
//
//
//        }

    }

    private void clickHomeBtn(){

        homeBtn.click();
    }
    private void verifyTitle(){
        softAssert.assertTrue(driver.getTitle().trim().equalsIgnoreCase("openMRS electronic medical record"),"title is not correct");
    }

    private void verifyTopPageElements(){
        softAssert.assertTrue(findPatientRecordText.isDisplayed(),"Text is not displayed");
        softAssert.assertTrue((searchBox.isDisplayed()&&searchBox.isEnabled()),"Search box is not enabled or displayed");
    }

    private void clickLogOut(){

        logOutBtn.click();
    }

    private void searchById(HashMap<String, String> data) {
        searchBox.click();
        searchBox.sendKeys(data.get("id"));
    }





}
