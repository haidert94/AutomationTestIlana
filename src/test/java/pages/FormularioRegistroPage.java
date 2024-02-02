package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

import java.util.ArrayList;
import java.util.List;

// Página que representa la funcionalidad del formulario de registro
@DefaultUrl("https://demoqa.com/")
public class FormularioRegistroPage extends PageObject {

    // Elementos de la página mediante Serenity y Selenium
    @FindBy(xpath = "//div/h5[text()='Forms']")
    WebElementFacade btnForm;

    @FindBy(xpath = "//span[text()='Practice Form']")
    WebElementFacade btnPractice;

    @FindBy(id = "firstName")
    WebElementFacade txtFirstName;

    @FindBy(id = "lastName")
    WebElementFacade txtLastName;

    @FindBy(id = "userEmail")
    WebElementFacade txtEmail;

    @FindBy(xpath = "//*[@id='genterWrapper']/div[2]/div[1]")
    WebElementFacade rdoMale;

    @FindBy(xpath = "//*[@id='userNumber']")
    WebElementFacade txtMobile;

    @FindBy(xpath = "//*[@id='dateOfBirthInput']")
    WebElementFacade txtDate;

    @FindBy(xpath = "//*[@id='dateOfBirth']/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/select")
    WebElementFacade cmbMonth;

    @FindBy(xpath = "//*[@id='dateOfBirth']/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select")
    WebElementFacade cmbYear;

    @FindBy(xpath = "//*[@id='dateOfBirth']/div[2]/div[2]/div/div/div[2]/div[2]/div/div")
    List<WebElementFacade> lblDay;

    @FindBy(xpath = "//*[@id='submit']")
    WebElementFacade btnSubmit;

    @FindBy(xpath = "//*[@id='example-modal-sizes-title-lg']")
    WebElementFacade msqThanks;

    // Método para abrir la URL por defecto
    public void ingresoURL() {
        open();
    }

    // Método para hacer clic en la sección de Forms
    public void forms() {
        btnForm.click();
    }

    // Método para hacer clic en la sección de Practice Form
    public void practice() {
        btnPractice.click();
    }

    // Método para completar el formulario de registro
    public void registration(String firstname, String lastname, String email, String mobile, String month, String year, String day) {
        txtFirstName.sendKeys(firstname);
        txtLastName.sendKeys(lastname);
        txtEmail.sendKeys(email);
        rdoMale.click();
        txtMobile.sendKeys(mobile);
        txtDate.click();

        cmbMonth.selectByVisibleText(month);
        cmbYear.selectByVisibleText(year);

        List<String> weekdays = new ArrayList<>();
        weekdays.add("Monday");
        weekdays.add("Tuesday");
        weekdays.add("Wednesday");
        weekdays.add("Thursday");
        weekdays.add("Friday");
        weekdays.add("Saturday");
        weekdays.add("Sunday");

        for (int i = 0; i < weekdays.size(); i++) {
            for (int j = 0; j < lblDay.size(); j++) {
                String expectedAriaLabel = String.format("Choose %s, %s %sth, %s", weekdays.get(i), month, day, year);
                String actualAriaLabel = lblDay.get(j).getAttribute("aria-label");
                if (actualAriaLabel.contains(expectedAriaLabel)) {
                    lblDay.get(j).click();
                    break;
                }
            }
        }
    }

    // Método para hacer clic en el botón Submit mediante JavaScript
    public void submit() {
        JavascriptExecutor js = (JavascriptExecutor) this.getDriver();

        js.executeScript("arguments[0].scrollIntoView(true);", btnSubmit);
        js.executeScript("arguments[0].click();", btnSubmit);
    }

    // Método para validar el mensaje después de enviar el formulario
    public void message(String message) {
        String thanksMessage = msqThanks.getText();
        Assert.assertEquals(thanksMessage, message);
    }
}