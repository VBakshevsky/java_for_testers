package ru.stqa.addressbook.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char) ('a' + rnd.nextInt(26));
        }
        return result;
    }

    public static String randomDay() {
        var rnd = new Random();
        var n = rnd.nextInt(31) + 1;
        return "" + n;
    }

    public static String randomMonth() {
        var rnd = new Random();
        var n = rnd.nextInt(12);
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December",};
        return months[n];
    }

    public static String randomYear() {
        var rnd = new Random();
        int year = rnd.nextInt(501) + 2000;
        return "" + year;
    }

    public static String randomMail(){
        String alphabet = RandomStringUtils.randomAlphabetic(8);
        String email = alphabet + "@mail.ru";
        return email;
    }

    public static String randomMobileNumber(){
        Random rnd = new Random();
        var mobile = "";
        for (int i = 0; i < 10; i++) {
            mobile = mobile + (rnd.nextInt(10));
        }
        return "+7" + mobile;
    }

    public static String randomFile (String dir) {
        var fileNames = new File(dir).list();
        Random rnd = new Random();
        var index = rnd.nextInt(fileNames.length);
        return Paths.get(dir,fileNames[index]).toString();
    }

    public static String randomFile1(String dir) {
        File directory = new File(dir);
        String[] fileNames = directory.list();

        if (fileNames == null || fileNames.length == 0) {
            System.out.println("В указанном каталоге нет файлов: " + dir);
            return null;
        }

        Random rnd = new Random();
        int index = rnd.nextInt(fileNames.length);
        return Paths.get(dir, fileNames[index]).toString();
    }
}
