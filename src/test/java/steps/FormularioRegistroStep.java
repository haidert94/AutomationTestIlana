package steps;

import net.thucydides.core.annotations.Step;
import pages.FormularioRegistroPage;

import java.util.Map;

public class FormularioRegistroStep {
    FormularioRegistroPage formularioRegistroPage;

    @Step
    public void formularioBrowseStep() {
        formularioRegistroPage.ingresoURL();
    }

    @Step
    public void formularioFormsStep() {
        formularioRegistroPage.forms();
    }

    @Step
    public void formularioPracticeStep() {
        formularioRegistroPage.practice();
    }

    @Step
    public void formularioRegistrationStep(String firstname, String lastname, String email, String mobile, String month, String year, String day) {
        formularioRegistroPage.registration(firstname, lastname, email, mobile, month, year, day);
    }

    @Step
    public void formularioSubmitStep() {
        formularioRegistroPage.submit();
    }
    @Step
    public void formularioMessageStep(String message) {
        formularioRegistroPage.message(message);
    }
}
