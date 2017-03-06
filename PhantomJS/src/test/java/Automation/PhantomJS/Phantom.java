package Automation.PhantomJS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Phantom {

	WebDriver driver = null;

	public void headless() throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"C:/Users/fbonifac/Documents/phantomjs-2.1.1-windows/bin/phantomjs.exe");
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] { "--web-security=no",
				"--ignore-ssl-errors=yes", "--ignore-ssl-errors=true", "--ssl-protocol=TLSv1" });

		try {
			driver = new PhantomJSDriver(caps);
		} catch (Exception e) {
			System.out.println("Fallo");
		}
	}

	@Test
	public void ingresoCas() throws Exception {
		this.headless();
		Thread.sleep(3000);
		String urlStr = "https://cas.nac.everis.int/acceso/login/";
		driver.get(urlStr);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		List<WebElement> lista = new ArrayList<WebElement>();
		lista = driver.findElements(By.xpath("//input[contains(@class, 'form-control z-textbox')]"));
		WebElement user = lista.get(0);
		user.sendKeys("fbonifac");
		WebElement pass = lista.get(1);
		pass.sendKeys("Boni1245");
		WebElement btnacceso = driver.findElement(By.xpath("//button[contains(., 'Acceder')]"));
		btnacceso.click();
		System.out.println("Paso");
	}

}
