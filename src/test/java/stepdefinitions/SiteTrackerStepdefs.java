package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class SiteTrackerStepdefs {

    WebDriver driver;

    @Given("(.*) launch (Chrome|FireFox|Safari) browser$")
    public void launchbrowserwithUrl(String URL, String option) throws InterruptedException {
        if(option.equalsIgnoreCase("CHROME")){

           System.setProperty("webdriver.chrome.driver", "\\chromedriver.exe");
           driver= new ChromeDriver();
           driver.manage().window().maximize();
           driver.get(URL);
           Thread.sleep(15000);
        }
        if(option.equalsIgnoreCase("FireFox")){
        //TO DO
        }
        if(option.equalsIgnoreCase("Safari")){
        // To DO
        }

    }


    @When("I switch to Tab (.*) and search for (.*) in quick find$")
    public void switchToTabandSearch(String tabName, String searchString) throws InterruptedException {

        //driver.findElement(By.xpath("//span[text()='"+tabName+"']")).click();
        WebElement tabelement = driver.findElement(By.xpath("//*[contains(text(),'"+tabName+"')]"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();",tabelement);
        Thread.sleep(3000);
        WebElement QuickFind = driver.findElement(By.xpath("//input[@name='Quick Find']"));
        executor.executeScript("arguments[0].click();",QuickFind);
        executor.executeScript("arguments[0].value='"+searchString+"'",QuickFind);
        Thread.sleep(4000);

    }

    @And("I click on (.*) on left side panel and select datable from inline edit from drop down$")
    public void iClickOnDatatableOnLeftSidePanelAndClickSelectDatableFromInlineEditFromDropDown(String searchString) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement datatable = driver.findElement(By.xpath("//*[text()='Lightning Web Components']/following::*[text()='"+searchString+"'][1]"));
        executor.executeScript("arguments[0].click();",datatable);
        Thread.sleep(5000);
        WebElement ExampleTab = driver.findElement(By.xpath("//a[text()='Example']"));
        WebElement Examplepicklist = driver.findElement(By.xpath("//*[@name='example']"));
        executor.executeScript("arguments[0].click();",ExampleTab);
        executor.executeScript("arguments[0].click();",Examplepicklist);
        Thread.sleep(3000);
        WebElement Examplepicklistvalue = driver.findElement(By.xpath("//span[text()='Data Table with Inline Edit']"));
        executor.executeScript("arguments[0].click();",Examplepicklistvalue);
        Thread.sleep(3000);
    }


    @And("I click run and update third row data by clicking on pencil icon with fields (.*) (.*) (.*) (.*)$")
    public void iClickRunAndUpdateThirdRowDataByClickingOnPenicilIconWithFollowingFields(String label,String webSite,
                                                                                         String phonenumber,String balance ) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebElement rubbutton = driver.findElement(By.xpath("//*[text()='Run']"));
        executor.executeScript("arguments[0].click();",rubbutton);
        Thread.sleep(18000);
        driver.switchTo().frame(1);
        driver.switchTo().frame(0);
        WebElement labeledit = driver.findElement(By.xpath("//table//tr[3]/td[2]/following::th[1]//*[@data-key='edit']"));
        labeledit.click();
        WebElement inlineedit = driver.findElement(By.xpath("//input[@name='dt-inline-edit-text']"));
        inlineedit.clear();
        inlineedit.sendKeys(label);
        WebElement websiteedit = driver.findElement(By.xpath("//table//tr[3]/td[3]//*[@data-key='edit']"));
        websiteedit.click();
        WebElement inlineediturl = driver.findElement(By.xpath("//input[@name='dt-inline-edit-url']"));
        inlineediturl.clear();
        inlineediturl.sendKeys(webSite);
        WebElement phone = driver.findElement(By.xpath("//table//tr[3]/td[4]//*[@data-key='edit']"));
        phone.click();
        WebElement inlineeditphone = driver.findElement(By.xpath("//input[@name='dt-inline-edit-phone']"));
        inlineeditphone.clear();
        inlineeditphone.sendKeys(phonenumber);
        WebElement closeat = driver.findElement(By.xpath("//table//tr[3]/td[5]//*[@data-key='edit']"));
        closeat.click();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d,yyyy");
        LocalDateTime now = LocalDateTime.now();
        WebElement InlineEditDate = driver.findElement(By.xpath("//lightning-datepicker//input[@name='dt-inline-edit-datetime']"));
        InlineEditDate.clear();
        InlineEditDate.sendKeys(dtf.format(now));
        WebElement InlineEdittime = driver.findElement(By.xpath("//lightning-timepicker//input[@name='dt-inline-edit-datetime']"));
        InlineEdittime.clear();
        InlineEdittime.sendKeys("12:57 PM");
        WebElement balanceedit = driver.findElement(By.xpath("//table//tr[3]/td[6]//*[@data-key='edit']"));
        balanceedit.click();
        WebElement InlineEditbalance = driver.findElement(By.xpath("//input[@name='dt-inline-edit-currency']"));
        InlineEditbalance.clear();
        InlineEditbalance.sendKeys(balance);
    }

    @Then("I verify fields are updated correctly (.*) (.*) (.*) (.*)$")
    public void iVerifyFieldsAreUpdatedCorrectly(String label,String webSite,
                                                 String phonenumber,String balance ) {

      String labelactual = driver.findElement(By.xpath("//table//tr[3]/td[2]/following::th[1]//div")).getText();
      Assert.assertEquals(labelactual,label);
      String websiteactual = driver.findElement(By.xpath("//table//tr[3]/td[3]//div")).getText();
      Assert.assertEquals(webSite,websiteactual);
      String phoneactual = driver.findElement(By.xpath("//table//tr[3]/td[4]//div")).getText();
      Assert.assertEquals(phonenumber,phoneactual);

    }
}

