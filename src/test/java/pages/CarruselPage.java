package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

// Página que representa la funcionalidad del carrusel
public class CarruselPage extends PageObject {

    // Elementos de la página mediante Serenity y Selenium
    @FindBy(xpath = "//div/h5[text()='Widgets']")
    WebElementFacade btnWidgets;

    @FindBy(xpath = "//span[text()='Slider']")
    WebElementFacade btnSlider;

    @FindBy(xpath = "//*[@id='sliderContainer']/div[1]/span/input")
    WebElementFacade btnCarrusel;

    @FindBy(xpath = "//*[@id='sliderContainer']/div[1]/span/div/div[1]")
    WebElementFacade btnCarruselValue;

    @FindBy(xpath = "//*[@id='sliderValue']")
    WebElementFacade lblValorCarrusel;

    // Método para hacer clic en la sección de Widgets
    public void widgets() {
        btnWidgets.click();
    }

    // Método para hacer clic en la sección de Slider
    public void slider() {
        btnSlider.click();
    }

    // Método para manipular el valor del slider y validar el resultado
    public void sliderValue(String slidervalue) throws InterruptedException {

        // JavascriptExecutor para manipular elementos con JavaScript
        JavascriptExecutor js = (JavascriptExecutor) this.getDriver();

        // Esperar antes de manipular el slider
        Thread.sleep(5000);

        // Establecer el valor del slider a 0
        js.executeScript("arguments[0].value = '0';", btnCarrusel);
        Thread.sleep(5000);

        // Establecer el valor del slider al valor deseado
        js.executeScript("arguments[0].value = arguments[1];", btnCarrusel, slidervalue);

        // Alternativamente, se puede establecer el valor directamente con JavaScript
        js.executeScript("document.getElementById('sliderValue').value = " + slidervalue + ";");
        Thread.sleep(5000);

        // Obtener el valor actual del atributo 'value' del slider
        String valorAtributoValue = lblValorCarrusel.getAttribute("value");

        // Validar que el valor del atributo 'value' sea igual al valor esperado
        Assert.assertEquals(valorAtributoValue, slidervalue);
    }
}