package com.academy.techcenture;

import com.academy.techcenture.driver.Driver;
import com.academy.techcenture.pages.*;
import com.academy.techcenture.utilities.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class OpenMrsPatientRegistrationE2ETest {
    private WebDriver driver;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() {
        Driver.quitDriver();
        softAssert.assertAll();
    }

    @Test(dataProvider = "openMrs")
    public void verifyRegistration(HashMap<String, String> data) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, softAssert);
        HomePage homePage = new HomePage(driver, softAssert);
        RegisterPatientpage registerPatientpage = new RegisterPatientpage(driver, softAssert);
        PatientDetailsPage patientDetailsPage = new PatientDetailsPage(driver, softAssert);
        FindPatientRecordPage findPatientRecordPage = new FindPatientRecordPage(driver, softAssert);

       loginPage.navigateToLoginPage();
       loginPage.logIn();
       homePage.verifyFunctionalities();
       registerPatientpage.fillOutForm(data);
       patientDetailsPage.verifyPDP(data);
       homePage.clickFindPatientRecord();
       findPatientRecordPage.verifyFindPatientRecord(data);
    }

    @DataProvider(name = "openMrs")
    public Object[][] getWebOrdersData() {
        ExcelReader excelReader = new ExcelReader("src/main/resources/OpenMrsData.xlsx", "patient");
        Object[][] data = excelReader.getData();
        return data;

    }
}
