package com.labcorp.career;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;

import java.util.concurrent.TimeUnit;
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CareerTest {
    static WebDriver driver;
    static Map<String, Object> vars;
    //  String jobID;

    static String jobLocation2;
    static String jobID;
    JavascriptExecutor js;



    @BeforeClass
    public  static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dutta003\\eclipse-workspace1\\ADEPCentral\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @AfterClass
    public  static void  tearDown() {
        driver.quit();
    }

    public  String waitForWindow(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> whNow = driver.getWindowHandles();
        Set<String> whThen = (Set<String>) vars.get("window_handles");
        if (whNow.size() > whThen.size()) {
            whNow.removeAll(whThen);
        }
        return whNow.iterator().next();
    }

    public  String getElementText(By locator) {
        WebElement locatorElement = driver.findElement(locator);
        return locatorElement.getText();
    }

    @Test
    @Order(1)
    public  void CareersTest() throws InterruptedException {

        driver.get("https://www.labcorp.com/");
        driver.manage().window().setSize(new Dimension(1920, 1032));
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Careers")));
        assertThat(driver.findElement(By.linkText("Careers")).getText(), is("Careers"));
        driver.findElement(By.linkText("Careers")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("typehead")));
        driver.findElement(By.id("typehead")).click();
        driver.findElement(By.id("typehead")).sendKeys("java developer");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("typehead")));
        driver.findElement(By.id("typehead")).sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[2]/section[3]/div/div/div/div[2]/div[2]/ul/li[1]/div[1]/span/a/div/span")));

        SeleniumTest obt1 = new SeleniumTest();
        //SeleniumTest testObj = new SeleniumTest();
        jobID = getElementText(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[2]/section[3]/div/div/div/div[2]/div[2]/ul/li[1]/div[1]/p/span[5]/span/span[2]"));
        System.out.println("jobID:: " + jobID);

        String jobLocation = getElementText(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[2]/section[3]/div/div/div/div[2]/div[2]/ul/li[1]/div[1]/p/span[3]/span"));
        System.out.println("jobLocation:: " + jobLocation);
        String jobLocation1 = jobLocation.substring(9, 15);
        jobLocation2 = jobLocation1 + " NC";

        String jobTitle = getElementText(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[2]/section[3]/div/div/div/div[2]/div[2]/ul/li[1]/div[1]/span/a/div/span"));
        System.out.println("jobTitle: :" + jobTitle);

        // WebElement button2 =driver.findElement(By.xpath(" //*[@id=\"input-43\"]"));

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[2]/section[3]/div/div/div/div[2]/div[2]/ul/li[1]/div[1]/span/a/div/span")));

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[2]/section[3]/div/div/div/div[2]/div[2]/ul/li[1]/div[1]/span/a/div/span")).click();

    }


    @Test
    @Order(2)
    public  void appicationformTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        assertThat(driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/div[2]/section/div/div/div/div[1]/h1")).getText(), is("Senior Java Engineer"));
        vars.put("jobTitle", driver.findElement(By.cssSelector(".job-info > .job-title")).getText());
        assertEquals(vars.get("jobTitle").toString(), "Senior Java Engineer");


        //String test1 = driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/div[2]/section/div/div/div/div[1]/section/div/div[1]/span[2]/span"))).getText();

        assertThat(driver.findElement(By.cssSelector(".au-target > b")).getText(), is("Job Id :"));
        vars.put("jobID", driver.findElement(By.cssSelector(".au-target > b")).getText());
        assertEquals(vars.get("jobID").toString(), "Job Id :");
        vars.put("location", driver.findElement(By.cssSelector(".au-target:nth-child(3) > .job-location")).getText());
        String text1 = "Most importantly, you'll be engaged in meaningful work for an organization that is committed to \"helping people live longer and healthier lives\" every day.";
        assertThat(driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div/div[1]/section[1]/div/div/div[2]/div[2]/p[2]")).getText(), is(text1));
        vars.put("desc3Text", driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div/div[1]/section[1]/div/div/div[2]/div[2]/p[2]")).getText());
        assertThat(driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div/div[1]/section[1]/div/div/div[2]/div[2]/ul[1]/li[2]")).getText(), is("Work with the development team, project managers, business analysts, Quality Assurance and users across the organization and create agile, intuitive and easy-to-use software."));
        vars.put("2BulletPoint", driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div/div[1]/section[1]/div/div/div[2]/div[2]/ul[1]/li[2]")).getText());
        js.executeScript("window.scrollTo(0,412)");
        assertThat(driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div/div[1]/section[1]/div/div/div[2]/div[2]/ul[2]/li[1]")).getText(), is("10+ year experience and expert knowledge of Java (7+) enterprise development and integration middleware development."));
        vars.put("1Requirement", driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[2]/div/div/div[1]/section[1]/div/div/div[2]/div[2]/ul[2]/li[1]")).getText());
        {
            List<WebElement> elements = driver.findElements(By.cssSelector(".Sub-Actions > .btn > ppc-content"));
            assert (elements.size() > 0);
        }
        vars.put("window_handles", driver.getWindowHandles());
        driver.findElement(By.linkText("Apply Now")).click();

    }

    @Test
    @Order(3)
    public  void emailDetailsTes() throws InterruptedException {
        vars.put("win4563", waitForWindow(2000));
        driver.switchTo().window(vars.get("win4563").toString());

        driver.findElement(By.xpath("//*[@id=\"input-4\"]")).sendKeys("shanthi.dutta@gmail.com");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"input-5\"]")).sendKeys("Test123456@");
        driver.findElement(By.xpath("//*[@id=\"input-5\"]")).sendKeys(Keys.TAB);
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        System.out.println("33333");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"wd-Authentication-NO_METADATA_ID-uid6\"]/div/div[1]/div/form/div[3]/div/div/div/div/div")).click();

        System.out.println("0000");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
        System.out.println("${Apply Manually}");
        driver.findElement(By.cssSelector("html")).click();
        {
            WebElement element = driver.findElement(By.cssSelector("html"));
            Actions builder = new Actions(driver);
            builder.doubleClick(element).perform();
        }
    }

    @Test
    @Order(4)
    public  void myInformationTes() throws InterruptedException {
        assertThat(driver.findElement(By.xpath("//label[contains(.,\'How Did You Hear About Us?*\')]")).getText(), is("How Did You Hear About Us?*"));
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,5000)");
        Thread.sleep(2000);
        System.out.println("${check]");
        WebElement button = driver.findElement(By.id("input-17"));
        js.executeScript("arguments[0].click();", button);
        Thread.sleep(2000);
        driver.findElement(By.id("input-17")).click();
        System.out.println("${save and contine}");
        driver.findElement(By.xpath("//button[contains(.,\'Save and Continue\')]")).click();
        System.out.println("1");

    }

    @Test
    @Order(5)
    public  void myExperienceTest() {
        System.out.println("${My Experience}");

        assertThat(driver.findElement(By.xpath("//button[contains(.,\'Save and Continue\')]")).getText(), is("Save and Continue"));
        driver.findElement(By.xpath("//button[contains(.,\'Save and Continue\')]")).click();
        System.out.println("2");
    }

    @Test
    @Order(6)
    public  void applicationQuestionsTes() throws InterruptedException {
        System.out.println("Application Questions");
        Thread.sleep(5000);
        assertThat(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/h2")).getText(), is("Application Questions"));
        assertThat(driver.findElement(By.cssSelector(".css-7t35fz:nth-child(1) b")).getText(), is("Do you currently have the legal authorization to work in United States?"));
        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"mainContent\"]/div/div[5]/div/ul/div[3]/div/div/button")));
        assertThat(driver.findElement(By.xpath("//button[contains(.,\'Save and Continue\')]")).getText(), is("Save and Continue"));
        WebElement button1 = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[5]/div/ul/div[3]/div/div/button"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button1);

        System.out.println("3");

    }

    @Test
    @Order(7)
    public  void voluntaryDisclosuresTes() throws InterruptedException {
        System.out.println("Voluntary Disclosures");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        System.out.println("4");
        js.executeScript("window.scrollBy(0,1000)");
        System.out.println("5");
        Thread.sleep(5000);
        assertThat(driver.findElement(By.cssSelector("div:nth-child(4) > .css-7t35fz > .css-1rncms5")).getText(), is("I have read, understand, and agree with the Privacy Policy.*"));
        System.out.println("6");
        WebElement button12 = driver.findElement(By.xpath(" //*[@id=\"input-43\"]"));
        js.executeScript("arguments[0].click();", button12);
        System.out.println("7");

        System.out.println("8");
        assertThat(driver.findElement(By.xpath("//button[contains(.,\'Save and Continue\')]")).getText(), is("Save and Continue"));
        driver.findElement(By.xpath("//button[contains(.,\'Save and Continue\')]")).click();
        System.out.println("5");
        Thread.sleep(5000);

    }

    @Test
    @Order(8)
    public  void SelfIdentifyTes() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        System.out.println("Self Identify");
        assertThat(driver.findElement(By.xpath("//h2")).getText(), is("Self Identify"));
        System.out.println("11");
        assertThat(driver.findElement(By.xpath("//label[contains(.,\'Name*\')]")).getText(), is("Name*"));
        System.out.println("12");
        driver.findElement(By.xpath("//*[@id=\"input-48\"]")).click();
        System.out.println("13");
        driver.findElement(By.xpath("//*[@id=\"input-48\"]")).sendKeys("Shanthi");
        System.out.println("14");
        assertThat(driver.findElement(By.xpath("//*[@id=\"label-50\"]")).getText(), is("Date*"));
        System.out.println("15");
        WebElement button4 = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div[3]/div[2]/div[2]/div[6]/div/div[1]/div[2]/div[2]/span"));
        js.executeScript("arguments[0].click();", button4);


        System.out.println("1000");
        WebElement button5 = driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/table/tbody/tr[1]/td[2]/button"));
        js.executeScript("arguments[0].click();", button5);


        System.out.println("16");
        assertThat(driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[2]/div[2]/div[9]/div/fieldset/legend")).getText(), is("Please check one of the boxes below:*"));
        WebElement button3 = driver.findElement(By.id("64cbff5f364f10000af3af293a050000"));
        js.executeScript("arguments[0].click();", button3);
        System.out.println("17");
        assertTrue(driver.findElement(By.id("64cbff5f364f10000af3af293a050000")).isSelected());
        driver.findElement(By.cssSelector(".css-12izqjd")).click();
        assertThat(driver.findElement(By.cssSelector(".css-1ko66zc")).getText(), is("Save and Continue"));
        driver.findElement(By.cssSelector(".css-1ko66zc")).click();
        assertThat(driver.findElement(By.xpath("//button[contains(.,\'Submit\')]")).getText(), is("Submit"));

    }

    @Test
    @Order(9)
    public  void ReviewTes() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement button13 = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[5]/div/ul/div[3]/div/div/button"));
        js.executeScript("arguments[0].click();", button13);
        Thread.sleep(5000);

    }

    @Test
    @Order(10)
    public  void dialogBoxTes() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // popwindow
        SeleniumTest testObj = new SeleniumTest();
        WebDriverWait wait = new WebDriverWait(driver, 2000);
        WebElement dialogPresent = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/h1"));
        wait.until(ExpectedConditions.elementToBeClickable(dialogPresent));


        String popName = testObj.getElementText(By.xpath("/html/body/div[2]/div/div/div/div/div/h1"));
        System.out.println("popName:: " + popName);
        // cancel

        WebElement dialog = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/button"));
        js.executeScript("arguments[0].click();", dialog);

    }

    @Test
    @Order(11)
    public  void myApplicationsoxTest() throws InterruptedException {
        // popwindow

        System.out.println("My Applications");
        String jobName = driver.findElement(By.xpath("//*[@id=\"tabpanel-wi8i0-0\"]/table/tbody/tr[1]/th/div/div/a")).getText();
        Assertions.assertEquals(jobName, vars.get("jobTitle").toString());
        //Senior Java Engineer
        String jobID1 = driver.findElement(By.xpath("/html/body/div/div/div/div[3]/div/div/div/div[2]/div[1]/section[2]/div[4]/table/tbody/tr[1]/td[1]")).getText();
        Assertions.assertEquals(jobID1, SeleniumTest.jobID);
        //2357467
        String joblocation = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div/div/div/div[2]/div[1]/section[3]/div[2]/div/div[1]/div[1]/div[2]/div/dl/dd")).getText();
        //Durham NC
        Assertions.assertEquals(jobID1, SeleniumTest.jobLocation2);

        // String active = driver.findElement(By.xpath("//*[@id=\"rslr0-0\"]")).getText();
        String active = driver.findElement(By.xpath("//*[@id=\"wi8i0-0\"]")).getText();
        Assertions.assertEquals(active, "Active (3)");

    }


}

