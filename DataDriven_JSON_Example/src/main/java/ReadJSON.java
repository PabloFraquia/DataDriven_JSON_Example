import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReadJSON {

	WebDriver driver;
	String url, UserName, Password;
	JSONParser parser = new JSONParser();

	@BeforeTest
	public void setup() throws FileNotFoundException, IOException, ParseException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pablo\\Desktop\\selenium\\DataDriven_JSON_Example\\chromedriver.exe");

		Object obj = parser.parse(new FileReader("C:\\Users\\pablo\\Desktop\\selenium\\DataDriven_JSON_Example\\objectRepository.json"));

		JSONObject jsonObject = (JSONObject) obj;

		url = (String) jsonObject.get("URL");

		UserName = (String) jsonObject.get("UserName");

		Password = (String) jsonObject.get("Password");

		driver = new ChromeDriver();

		driver.get(url);

	}

	@Test
	public void testSearch() throws FileNotFoundException, IOException, ParseException, InterruptedException {
		
		driver.findElement(By.id("form1")).sendKeys(UserName);

		//driver.findElement(By.id("pass")).sendKeys(Password);

		driver.findElement(By.id("proceed-button")).click();
		Thread.sleep(10000);
		
		driver.findElement(By.id("form-submit")).click();
	}
}
