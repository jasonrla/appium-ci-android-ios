package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UtilFunctions {

    private static AppiumDriver driver;

    public UtilFunctions(AppiumDriver driver) {
        this.driver = driver;
    }

    public static JSONObject readJSON(String data) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("data/AndroidData.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONObject validData = jsonObject.getJSONObject("ValidData");
        return validData.getJSONObject(data);
    }

    public static WebElement createElement(AppiumDriver driver, String elementString, String text) {
        String xpath = String.format(elementString, text);
        return driver.findElement(By.xpath(xpath));
    }

    public static String formatDateString(String inputDate, String pattern) {
        SimpleDateFormat inputFormat;
        if (inputDate.matches("\\d{1,2}/\\d{1,2}/\\d{4}")) {
            inputFormat = new SimpleDateFormat("d/M/yyyy");
        } else if (inputDate.matches("\\d{1,2}/\\d{1,2}/\\d{2}")) {
            inputFormat = new SimpleDateFormat("d/M/yy");
        } else {
            throw new IllegalArgumentException("Invalid date format");
        }

        try {
            Date date = inputFormat.parse(inputDate);
            SimpleDateFormat outputFormat = new SimpleDateFormat(pattern);
            return outputFormat.format(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date", e);
        }
    }

    public static String formatMonthString(String inputMonth) {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM", Locale.ENGLISH);
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM", Locale.ENGLISH);
            return outputFormat.format(inputFormat.parse(inputMonth));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid month", e);
        }
    }

    public static String getFromTaskItemElement(AppiumDriver driver, String elementValue, String elementNumber) {
        WebElement element = UtilFunctions.createElement(driver, elementValue, elementNumber);
        return element.getText();
    }

    public static String getCurrentDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }
}
