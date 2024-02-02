package steps;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import pages.FormularioRegistroPage;
import pages.FuncionalidadEnlacesPage;

public class FuncionalidadEnlacesStep {

    FuncionalidadEnlacesPage funcionalidadEnlacesPage;


    @Step
    public void formularioElementosStep() {
        funcionalidadEnlacesPage.elements();
    }

    @Step
    public void formularioLinksStep() {
        funcionalidadEnlacesPage.links();
    }

    @Step
    public void formularioLinksEncontradosStep(String Created, String NoContent, String Moved, String BadRequest, String Unauthorized, String Forbidden, String NotFound, String Home, String HomeTygnI) {
        funcionalidadEnlacesPage.linksEncontrados(Created, NoContent, Moved, BadRequest, Unauthorized, Forbidden, NotFound, Home, HomeTygnI);
    }
}
