package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.json.JSONObject;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Month;
import java.util.Arrays;
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

    public static String splitStringMonthNumberFromDate(String date) {
        String[] dateParts = date.split("-");
        return dateParts[1];
    }

    public static String splitStringDayNumberFromDate(String date) {
        String[] dateParts = date.split("-");
        return dateParts[2];
    }

    public static String convertStringMonthNumberToMonthName(String monthNumber) {
        int monthInt = Integer.parseInt(monthNumber);
        Month month = Month.of(monthInt);
        return month.name().substring(0, 1) + month.name().substring(1).toLowerCase();
    }

    public static String convertDay(String day) {
        int dayInt = Integer.parseInt(day);
        return String.valueOf(dayInt);
    }

    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(new Date());
    }

    public static void swipeLeftUntilElementFound(AppiumDriver driver, WebElement element, String elementToFind) {
        while (true) {
            try {
                if (driver.findElement(By.xpath(elementToFind)).isDisplayed()) {
                    break;
                }
            } catch (NoSuchElementException e) {
                swipeLeftOnElement(driver, element);
            }
        }
    }

    public static void swipeLeftOnElement(AppiumDriver driver , WebElement element) {
        Dimension size = element.getSize();

        int startx = element.getLocation().getX() + (size.width);
        int endx = element.getLocation().getX() - (size.width);
        int y = element.getLocation().getY();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endx, y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    public static String getCurrentMonthName() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        return sdf.format(new Date());
    }
}
