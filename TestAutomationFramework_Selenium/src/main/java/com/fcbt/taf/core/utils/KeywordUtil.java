package com.fcbt.taf.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fcbt.taf.core.driver.DriverManager;
import com.google.common.base.Function;


/**
 * @author TX
 *
 */
public class KeywordUtil {
	public static String cucumberTagName;
	private static final int DEFAULT_WAIT_SECONDS = 120;
	protected static final int FAIL = 0;
	static WebElement webElement;
	protected static String url = "";
	private static String userDir = "user.dir";
	private static String text = "";
	public static final String VALUE = "value";
	public static String lastAction = "";

	static String result_FolderName = System.getProperty("user.dir") + "\\ExecutionReports\\ExecutionReports";

	

	

	public static boolean scrollingToElementofAPage(By locator, String logStep) throws InterruptedException {
		Thread.sleep(5000);
		WebElement element = DriverManager.getDriver().findElement(locator);
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView();", element);
		// highLightElement(driver, element);
		

		return true;
	}

	public static String getCurrentDateTime() {

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		System.out.println(strDate);
		return strDate;
	}

	/**
	 * @param locator
	 * @return
	 */
	public static void navigateToUrl(String url) {
		try {
			KeywordUtil.lastAction = "Navigate to: " + url;
			
			DriverManager.getDriver().navigate().to(url);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static String getCurrentUrl() {
		return DriverManager.getDriver().getCurrentUrl();
	}

	public static WebElement waitForClickable(By locator) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), DEFAULT_WAIT_SECONDS);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(WebDriverException.class);

		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * @param locator
	 * @return
	 */
	public static WebElement waitForPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), DEFAULT_WAIT_SECONDS);
		wait.ignoring(ElementNotVisibleException.class);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * @param locator
	 * @return
	 */
	public static WebElement waitForVisible(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), DEFAULT_WAIT_SECONDS);
			// wait.ignoring(ElementNotVisibleException.class);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean waitForInVisibile(By locator) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 30);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public static WebElement waitForVisibleIgnoreStaleElement(By locator) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), DEFAULT_WAIT_SECONDS);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(ElementNotVisibleException.class);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * @param locator
	 * @param seconds
	 * @param poolingMil
	 * @return
	 * @throws Exception
	 */
	public static WebElement findWithFluintWait(By locator, int seconds, int poolingMil) throws Exception {
		// Because if implicit wait is set then fluint wait will not work

		DriverManager.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		WebElement element = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverManager.getDriver()).withTimeout(seconds, TimeUnit.SECONDS)
					.pollingEvery(poolingMil, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class)
					.ignoring(StaleElementReferenceException.class).ignoring(ElementNotVisibleException.class)
					.ignoring(WebDriverException.class);
			element = wait.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(locator);
				}
			});

		} catch (Exception t) {
			throw new Exception("Timeout reached when searching for element! Time: " + seconds + " seconds " + "\n"
					+ t.getMessage());
		} finally {
			// DriverManager.getDriver().manage().timeouts().implicitlyWait(Utility.getIntValue("implicitlyWait"),
			// TimeUnit.SECONDS);
		}

		return element;
	}// End FindWithWait()

	/**
	 * @param locator
	 * @return
	 * @throws Exception
	 */
	public static WebElement findWithFluintWait(By locator) throws Exception {
		DriverManager.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		// Because if implict wait is set then fluint wait will not work
		KeywordUtil.lastAction = "Find Element: " + locator.toString();
		// DriverManager.getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebElement element = null;

		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(DriverManager.getDriver())
					.withTimeout(DEFAULT_WAIT_SECONDS, TimeUnit.SECONDS).pollingEvery(200, TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
					.ignoring(ElementNotVisibleException.class);

			element = wait.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply(WebDriver driver) {
					return driver.findElement(locator);
				}
			});

		} catch (Exception t) {
			throw new Exception("Timeout reached when searching for element! Time: " + DEFAULT_WAIT_SECONDS
					+ " seconds " + "\n" + t.getMessage());
		} finally {
			// DriverManager.getDriver().manage().timeouts().implicitlyWait(Utility.getIntValue("implicitlyWait"),
			// TimeUnit.SECONDS);
		}

		return element;
	}// End FindWithWait()

	public static WebElement getWebElement(By locator) throws Exception {
		KeywordUtil.lastAction = "Find Element: " + locator.toString();
		
		return findWithFluintWait(locator);
	}
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/*
	 * Web driver common functions
	 * ===========================================================
	 */

	/**
	 * @param locator
	 * @return
	 */
	public static boolean click(By locator, String logStep) {

		KeywordUtil.lastAction = "Click: " + locator.toString();
		
		WebElement elm = waitForClickable(locator);
		if (elm == null) {
			return false;
		} else {
			((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView();", elm);
			elm.click();
			
			return true;
		}
	}

	
	
	
	public static boolean acceptAlert() {

		Alert alert = DriverManager.getDriver().switchTo().alert();
		alert.accept();
		return true;

	}

	/**
	 * @param linkText
	 * @return
	 */
	public static boolean clickLink(String linkText, String logStep) {
		KeywordUtil.lastAction = "Click Link: " + linkText;

		WebElement elm = waitForClickable(By.linkText(linkText));
		if (elm == null) {
			return false;
		} else {
			elm.click();

			return true;
		}
	}

	/**
	 * @param locator
	 * @return
	 */
	public static String getElementText(By locator) {
		KeywordUtil.lastAction = "Get Element text: " + locator.toString();
		
		WebElement elm = waitForClickable(locator);
		return elm.getText().trim();
	}

	public static String getImageTitle(By locator) {
		WebElement elm = waitForVisible(locator);
		return elm.getAttribute("title");

	}

	/**
	 * @param locator
	 * @return
	 */
	public static boolean isWebElementVisible(By locator, String logStep) {
		try {
			KeywordUtil.lastAction = "Check Element visible: " + locator.toString();
			
			WebElement elm = waitForVisible(locator);
			

			return elm.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean isWebElementEnable(By locator, String logStep) {
		KeywordUtil.lastAction = "Check Element visible: " + locator.toString();
		
		WebElement elm = waitForVisible(locator);
		

		return elm.isEnabled();
	}

	/**
	 * @param locator
	 * @return
	 */
	public static List<WebElement> getListElements(By locator, String logStep) {
		KeywordUtil.lastAction = "Get List of Elements: " + locator.toString();
		

		try {
			findWithFluintWait(locator, 60, 300);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return DriverManager.getDriver().findElements(locator);

	}

	public static boolean isWebElementPresent(By locator, String logStep) {
		KeywordUtil.lastAction = "Check Element present: " + locator.toString();
		
		List<WebElement> elements = DriverManager.getDriver().findElements(locator);
		if (elements.isEmpty()) {
			return false;
		}
		

		return true;
	}

	public static boolean hoverOnElement(By by) throws InterruptedException {

		WebElement element = DriverManager.getDriver().findElement(by);
		// highLightElement(driver,element );
		// Thread.sleep(5000);

		Actions act = new Actions(DriverManager.getDriver());
		act.moveToElement(element).build().perform();

		Thread.sleep(3000);

		return true;

	}

	/**
	 * @param locator
	 * @return
	 */
	public static boolean isWebElementNotPresent(By locator) {
		KeywordUtil.lastAction = "Check Element not present: " + locator.toString();
		
		List<WebElement> elements = (new WebDriverWait(DriverManager.getDriver(), DEFAULT_WAIT_SECONDS))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		if (elements.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean inputText(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Input Text: " + data + " - " + locator.toString();
		
		WebElement elm = waitForVisible(locator);
		if (elm == null) {
			return false;
		} else {
			elm.clear();
			elm.sendKeys(data);
			
			return true;
		}
	}

	public static void pressTabKey(By locator) {
		WebElement elm = waitForVisible(locator);
		elm.sendKeys(Keys.TAB);
	}

	public static void pressEnter(By locator) {
		WebElement elm = waitForVisible(locator);
		elm.sendKeys(Keys.ENTER);
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 * @throws InterruptedException 
	 */
	public static boolean inputTextJS(By locator, String data, String logStep) throws InterruptedException {
		KeywordUtil.lastAction = "Input Text: " + data + " - " + locator.toString();
		
		WebElement element = waitForVisible(locator);
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].value = arguments[1]", element, data);
		Thread.sleep(200);
		Actions action = new Actions(DriverManager.getDriver()).doubleClick(element);
		action.build().perform();
		//((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].dblclick();", element);
		if (element.getText().equalsIgnoreCase(data)) {
			
			return true;
		} else
			return false;
	}

	
	/**
	 * @param locator
	 * @return
	 */
	public static boolean isRadioSelected(By locator, String logStep) {
		KeywordUtil.lastAction = "Is Radio Selected: " + locator.toString();
		
		WebElement element = waitForVisible(locator);
		

		return element.isSelected();
	}

	/**
	 * @param locator
	 * @return
	 */
	public static boolean isRadioNotSelected(By locator, String logStep) {
		KeywordUtil.lastAction = "Is Radio Not Selected: " + locator.toString();
		
		boolean check = isRadioSelected(locator, logStep);
		

		return (!check);
	}

	/**
	 * @param locator
	 * @return
	 */
	public static boolean clearInput(By locator) {
		WebElement element = waitForVisible(locator);
		element.clear();
		element = waitForVisible(locator);
		return element.getAttribute(VALUE).isEmpty();
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean verifyCssProperty(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify CSS : " + data + " - " + locator.toString();
		

		String[] property = data.split(":", 2);
		String expProp = property[0];
		String expValue = property[1];
		boolean flag = false;
		String prop = (waitForPresent(locator)).getCssValue(expProp);
		if (prop.trim().equals(expValue.trim())) {
			flag = true;
			

			return flag;
		} else {
			return flag;
		}
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean verifyInputText(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify Input Expected Text: " + data + " - " + locator.toString();
		
		WebElement element = waitForVisible(locator);
		String actual = element.getAttribute(VALUE);
		
		

		return actual.equalsIgnoreCase(data);

	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean verifyInputTextJS(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify Input Expected Text: " + data + " - " + locator.toString();
		
		WebElement element = waitForVisible(locator);

		String message = String.format("Verified text expected \"%s\" actual \"%s\" ", data, element.getText());
		
		

		return data.equalsIgnoreCase(element.getText());
	}

	/**
	 * <h1>Log results</h1>
	 * <p>
	 * This function will write results to the log file.
	 * </p>
	 * 
	 * @param locator
	 * @param data
	 * @return
	 */
	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean verifyText(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify Expected Text: " + data + " - " + locator.toString();
		
		WebElement element = waitForVisible(locator);
		String message = String.format("Verified text expected \"%s\" actual \"%s\" ", data, element.getText());
		
		

		return element.getText().equalsIgnoreCase(data);

	}

	public static boolean verifyTextContains(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify Text Contains: " + data + " - " + locator.toString();
		
		WebElement element = waitForVisible(locator);
		String message = new String(
				String.format("Verified text expected \"%s\" actual \"%s\" ", data, element.getText()));
		
		

		return element.getText().toUpperCase().contains(data.toUpperCase());

	}

	/**
	 * @param locator
	 * @return
	 */
	public static boolean verifyDisplayAndEnable(By locator, String logStep) {
		KeywordUtil.lastAction = "Is Element Displayed and Enable : " + locator.toString();
		
		WebElement element = waitForVisible(locator);
		
		return element.isDisplayed() && element.isEnabled();
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean clickJS(By locator, String logStep) {
		KeywordUtil.lastAction = "Click : " + locator.toString();
		
		WebElement element = waitForVisible(locator);
		Object obj = ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
		

		return obj == null;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	/*
	 * Handling selects
	 * ===========================================================
	 */

	/**
	 * @param locator
	 * @param index
	 * @return
	 */
	public static boolean selectByIndex(By locator, int index, String logStep) {
		KeywordUtil.lastAction = "Select dropdown by index : " + index + " - " + locator.toString();
		
		Select sel = new Select(DriverManager.getDriver().findElement(locator));
		sel.selectByIndex(index);

		// Check whether element is selected or not
		sel = new Select(DriverManager.getDriver().findElement(locator));
		if (sel.getFirstSelectedOption().isDisplayed()) {
			
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param locator
	 * @param value
	 * @return
	 */
	public static boolean selectByValue(By locator, String value, String logStep) {
		KeywordUtil.lastAction = "Select dropdown by value : " + value + " - " + locator.toString();
		
		Select sel = new Select(DriverManager.getDriver().findElement(locator));
		sel.selectByValue(value);

		// Check whether element is selected or not
		sel = new Select(DriverManager.getDriver().findElement(locator));
		if (sel.getFirstSelectedOption().isDisplayed()) {
			

			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param locator
	 * @param value
	 * @return
	 */
	public static boolean selectByVisibleText(By locator, String value, String logStep) {
		try {
			KeywordUtil.lastAction = "Select dropdown by text : " + value + " - " + locator.toString();
			
			Select sel = new Select(DriverManager.getDriver().findElement(locator));
			sel.selectByVisibleText(value);
			

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		// Check whether element is selected or not
		// sel = new Select(DriverManager.getDriver().findElement(locator));
		// if (sel.getFirstSelectedOption().getText().equalsIgnoreCase(value)) {
		// return true;
		// } else {
		// return false;
		// }
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 * @throws Throwable
	 */
	public static boolean verifyAllValuesOfDropDown(By locator, String data, String logStep) throws Throwable {
		KeywordUtil.lastAction = "Verify Dropdown all values: " + data + " - " + locator.toString();
		
		boolean flag = false;
		WebElement element = findWithFluintWait(locator);
		List<WebElement> options = element.findElements(By.tagName("option"));
		String[] allElements = data.split(",");
		String actual;
		for (int i = 0; i < allElements.length; i++) {
			

			actual = options.get(i).getText().trim();
			if (actual.equalsIgnoreCase(allElements[i].trim())) {
				

				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public static boolean verifyDropdownSelectedValue(By locator, String data, String logStep) {
		KeywordUtil.lastAction = "Verify Dropdown selected option: " + data + " - " + locator.toString();
		
		Select sel = new Select(waitForVisible(locator));
		String defSelectedVal = sel.getFirstSelectedOption().getText();
		
		return defSelectedVal.trim().equals(data.trim());
	}

	/**
	 * @param locator
	 * @param size
	 * @return
	 */
	public static boolean verifyElementSize(By locator, int size, String logStep) {
		KeywordUtil.lastAction = "Verify Element size: " + size + " - " + locator.toString();
		
		List<WebElement> elements = DriverManager.getDriver().findElements(locator);
		if (elements.size() == size) {
			
			return true;
		} else {
			
			return false;
		}
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean writeInInputCharByChar(By locator, String data, String logStep) throws InterruptedException {
		WebElement element = waitForVisible(locator);
		element.clear();
		String[] b = data.split("");
		for (int i = 0; i < b.length; i++) {
			element.sendKeys(b[i]);
			Thread.sleep(250);
		}
		

		return true;
	}

	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//
	// /**
	// * @param check
	// * @param className
	// * @param logstep
	// * @throws Exception
	// */
	// public static void executeStep(Boolean check, String logstep) throws
	// Exception {
	// if (check) {
	// logStepPass(logstep);
	// } else {
	// logStepFail(logstep);
	// throw new Exception("Step failed - " + logstep);
	// }
	// }

	/**
	 * @param check
	 * @param className
	 * @param logstep
	 * @throws Exception
	 *             //
	 */
	// public static void verifyStep(Boolean check, String logstep) throws
	// TestStepFailedException {
	// if (check) {
	// logStepPass(logstep);
	// } else {
	// logStepFail(logstep);
	// throw new TestStepFailedException("Varification failed-->" + logstep );
	// }
	// }

	// Get Tag name and locator value of Element
	public static String getElementInfo(By locator) throws Exception {
		return " Locator: " + locator.toString();
	}

	public static String getElementInfo(WebElement element) throws Exception {
		String webElementInfo = "";
		webElementInfo = webElementInfo + "Tag Name: " + element.getTagName() + ", Locator: ["
				+ element.toString().substring(element.toString().indexOf("->") + 2);
		return webElementInfo;

	}

	/**
	 * @param time
	 * @throws InterruptedException
	 */
	public static void delay(long time) throws InterruptedException {
		Thread.sleep(time);
	}

	/**
	 * @param locator
	 * @return
	 */
	public boolean verifyCurrentDateInput(By locator, String logStep) {
		boolean flag = false;
		WebElement element = waitForVisible(locator);
		String actual = element.getAttribute(VALUE).trim();
		DateFormat dtFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		dtFormat.setTimeZone(TimeZone.getTimeZone("US/Central"));
		String expected = dtFormat.format(date).trim();
		if (actual.trim().contains(expected)) {
			

			flag = true;

		}
		return flag;
	}

	/**
	 * @param locator
	 * @param data
	 * @return
	 * @throws InterruptedException
	 */
	public static boolean uploadFilesUsingSendKeys(By locator, String data, String logStep)
			throws InterruptedException {
		WebElement element = waitForVisible(locator);
		element.clear();
		element.sendKeys(System.getProperty(userDir) + "\\src\\test\\resources\\uploadFiles\\" + data);
		

		return true;
	}

	// /**
	// * @param data
	// * @param page
	// * @param fileName
	// * @return
	// * @throws IOException
	// */
	// public static boolean verifyPDFData(String data, int page, String
	// fileName) throws IOException {
	// FileInputStream fis = null;
	// String dwnFile = null;
	// try {
	//
	// String dirName = System.getProperty(userDir) +
	// "\\src\\test\\resources\\downloadFile\\";
	// File dir = new File(dirName);
	// File[] path1 = dir.listFiles();
	//
	// for (int k = 0; k < path1.length; k++) {
	// dwnFile = path1[k].toString();
	// if (dwnFile.contains(fileName)) {
	// break;
	// }
	//
	// continue;
	// }
	// File file = new File(dwnFile);
	// fis = new FileInputStream(file.getAbsolutePath());
	// PdfReader text = new PdfReader(fis);
	// String expected = PdfTextExtractor.getTextFromPage(text, page);
	//
	// String[] b = data.split(",");
	// fis.close();
	// for (int i = 0; i < b.length; i++) {
	// if (expected.contains(b[i]))
	// continue;
	// }
	// } catch (Exception e) {
	// LogUtil.errorLog(KeywordUtil.class, e.getMessage(), e);
	// }
	// return true;
	// }

	/**
	 * @return
	 */
	public boolean delDirectory() {
		File delDestination = new File(System.getProperty(userDir) + "\\src\\test\\resources\\downloadFile");
		if (delDestination.exists()) {
			File[] files = delDestination.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					delDirectory();
				} else {
					files[i].delete();
				}
			}
		}
		return delDestination.delete();
	}

	// public static void hoverElement(By locator) throws InterruptedException{
	// KeywordUtil.lastAction="Hover Element: "+locator.toString();
	// 
	//
	// WebElement element = waitForClickable(locator);
	// Point p =element.getLocation();
	// Actions builder = new Actions(DriverManager.getDriver());
	// builder.moveToElement(element, p.getX(), p.getY()).build().perform();
	// pause(1000);
	//
	// }
	public static boolean doubleClick(By locator, String logStep) {
		boolean result = false;
		try {
			KeywordUtil.lastAction = "Double click: " + locator.toString();
			
			WebElement element = DriverManager.getDriver().findElement(locator);
			Actions action = new Actions(DriverManager.getDriver()).doubleClick(element);
			action.build().perform();
			

			result = true;

		} catch (StaleElementReferenceException e) {
			
			result = false;
		} catch (NoSuchElementException e) {
			
			result = false;
		} catch (Exception e) {
			
			result = false;
		}
		return result;
	}

	public static boolean switchToFrame(String frameName) {

		try {
			DriverManager.getDriver().switchTo().frame(frameName);
			return true;

		} catch (Exception e) {
			
			return false;
		}
	}
	
	
	
	
	public static boolean switchToFrameToBeAvailable(String frameName) {
	  
	  try {
		   WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 60);
	  
		   wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.tagName(frameName))); 
		   return true;
	  
	  } 
	  catch (Exception e) {
			 
			  return false; 
		  } 
	  }
	 
	 

	public static String createZipFile() throws IOException {
		result_FolderName = result_FolderName.replace("\\", "/");
		String outputFile = result_FolderName + ".zip";
		FileOutputStream fos = new FileOutputStream(outputFile);
		ZipOutputStream zos = new ZipOutputStream(fos);
		packCurrentDirectoryContents(result_FolderName, zos);
		zos.closeEntry();
		zos.close();
		fos.close();
		return outputFile;
	}

	public static void packCurrentDirectoryContents(String directoryPath, ZipOutputStream zos) throws IOException {
		for (String dirElement : new File(directoryPath).list()) {
			String dirElementPath = directoryPath + "/" + dirElement;
			if (new File(dirElementPath).isDirectory()) {
				packCurrentDirectoryContents(dirElementPath, zos);
			} else {
				ZipEntry ze = new ZipEntry(dirElementPath.replaceAll(result_FolderName + "/", ""));
				zos.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(dirElementPath);
				byte[] bytesRead = new byte[512];
				int bytesNum;
				while ((bytesNum = fis.read(bytesRead)) > 0) {
					zos.write(bytesRead, 0, bytesNum);
				}

				fis.close();
			}
		}
	}

}// End class

class TestStepFailedException extends Exception {
	TestStepFailedException(String s) {
		super(s);
	}

}
