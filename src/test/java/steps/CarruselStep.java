package steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import pages.CarruselPage;
import pages.FuncionalidadEnlacesPage;

public class CarruselStep {

    CarruselPage carruselPage;


    @Step
    public void widgetsStep() {
        carruselPage.widgets();
    }

    @Step
    public void sliderStep() {
        carruselPage.slider();
    }

    @Step
    public void sliderValueStep(String slidervalue) throws InterruptedException {
        carruselPage.sliderValue(slidervalue);
    }
}
