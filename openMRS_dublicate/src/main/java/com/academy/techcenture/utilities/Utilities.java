package com.academy.techcenture.utilities;

import com.github.javafaker.Faker;
import com.github.javafaker.PhoneNumber;

import java.util.Locale;

public class Utilities {
    private Utilities(){}
    public static String generateRandomstring(int length) {
        String source = "abcdecdgjklmnoprstovwxyz";
        String result = "";
        for (int i = 0; i <= length; i++) {
            int randomIndex = (int) (Math.random() * source.length() + 1);
            result += (String.valueOf(source.charAt(randomIndex)));

        }
        return result;
    }


    public static String generateRandomstring(int length, boolean isUpperCase) {
        String source = "abcdecdgjklmnoprstovwxyz";
        String result = "";
        for (int i = 0; i <= length; i++) {
            int randomIndex = (int) (Math.random() * source.length() + 1);
            result += (String.valueOf(source.charAt(randomIndex)));

        }
        return isUpperCase ? result.toLowerCase() : result;
    }

    /**
     * This method will create random numbers beetween min and max inclusive.
     *
     */
    public static int generalRandomNumber(int min, int max){

        int random=(int)(Math.random()*((max-min)+1)+min);
        return random;
    }

    public static String randomFirstName(){
        Faker faker=new Faker();
        String randonFirstName=faker.name().firstName();
        return randonFirstName;
    }

    public static String randomLastName(){
        Faker faker=new Faker();
        String randonLastName=faker.name().lastName();
        return randonLastName;
    }

    public static String randomStreetName(){
        Faker faker=new Faker();
        String randomStreetName= faker.address().streetName();
        return randomStreetName;
    }
    public static String randomCountry(){
        Faker faker=new Faker();
        String randomCountry= faker.country().name();
        return randomCountry;
    }
//    public static String randomZip() {
//        Faker faker = new Faker();
//        String randomZip = faker.address().zipCode().substring(0,6);
//        return randomZip;
//    }
    public static String randomPhoneNumber(){
        Faker faker = new Faker();

        String phoneNumber = String.valueOf(faker.number().digits(10));
        return phoneNumber;
    }
    public static String rndPhoneNumber(){
        String rndPhoneNumber = "";
        String valueph = "123456789";
        for (int i = 0; i < 10; i++) {
            rndPhoneNumber += String.valueOf(generalRandomNumber(1,9));
        }
        return rndPhoneNumber;
    }

    public static String randomCity(){
        Faker faker=new Faker();
        String randomCity= faker.address().cityName();
        return randomCity;
    }

    public static String randomState(){
        Faker faker=new Faker();
        String randomState= faker.address().state();
        return randomState;
    }
    public static String randomZip(){
        Faker faker=new Faker(new Locale("en-us"));
        String randomZip= faker.address().zipCode();
        return randomZip;
    }

    public static String randomQuote(){
        Faker faker=new Faker();
        String randomQuote = faker.gameOfThrones().quote();
        return randomQuote;

    }




}
