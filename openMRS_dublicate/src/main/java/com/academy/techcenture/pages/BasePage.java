package com.academy.techcenture.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class BasePage {

    protected WebDriver driver;
    protected SoftAssert softAssert;
    public BasePage(WebDriver driver, SoftAssert softAssert){
        this.driver=driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@id='navbarSupportedContent']/ul/li")
    protected List<WebElement> headerBtns;


    public void verifyHeaderBtns(){
        for (int i = 0; i < headerBtns.size(); i++) {
            assertTrue(headerBtns.get(i).isEnabled(),"BTN "+headerBtns.get(i)+"is not enabled");
        }
    }
}
