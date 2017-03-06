package cucumberLoginCas;

import java.util.concurrent.TimeUnit;

//import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CucumberLoginCas {
	WebDriver driver = null;
	WebDriverWait wait;

	@Given("^El usuario cuenta con credenciales existentes en GDE$")
	public void openBrowser() {
		System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe");
		// Fix problema certificado
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		// Fix problema certificado
		driver = new FirefoxDriver(profile);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@When("^Ingresa a la URL \"(.*)\"$")
	public void ingresoCAS(String urlCAS) {
		System.out.println("URL: " + urlCAS);
		driver.navigate().to(urlCAS);
		wait = new WebDriverWait(driver, 10);
	}

	@When("^El usuario ingresa al sistema con sus credenciales: usuario \"(.*)\" y password \"(.*)\"$")
	public void datosLogin(String usuario, String password) {
		System.out.println("Usuario: " + usuario);
		System.out.println("Password: " + password);
		driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div[2]/div/input")).clear();
		driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div[2]/div/input")).sendKeys(usuario);
		driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div[3]/div/input")).clear();
		driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div[3]/div/input"))
				.sendKeys(password);
//		WebElement element = wait.until(ExpectedConditions
//				.elementToBeClickable(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div[4]/button")));
		driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div[4]/button")).click();
	}

	@Then("^El usuario ingresa a EU mediante la url \"(.*)\"$")
	public void ingresoEU(String urlEU) throws Exception {
		System.out.println("urlEU: " + urlEU);
		/** 
		 * Aguarda que el logo de Gobierno esté cargado
		 * */
		wait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("/html/body/div/div[1]/div/div/div[2]/table/tbody[2]/tr/td[1]/div/img")));
		System.out.println("driver.getCurrentUrl(): " + driver.getCurrentUrl());
		if (driver.getCurrentUrl().equalsIgnoreCase(urlEU)) {
			System.out.println("TEST OK");
		} else {
			System.out.println("Test ERROR");
			throw new Exception("ERROR - El usuario no llegó a la URL de EU");
		}
		driver.close();
	}
}