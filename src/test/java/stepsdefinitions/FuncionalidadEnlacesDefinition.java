package stepsdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.FuncionalidadEnlacesStep;
import utilities.data.DataDrivenExcel;
import utilities.data.Excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Definición de pasos para la funcionalidad de enlaces
public class FuncionalidadEnlacesDefinition {

    @Steps
    FuncionalidadEnlacesStep funcionalidadEnlacesStep;

    // Utilidades para leer datos desde Excel
    DataDrivenExcel dataDriverExcel = new DataDrivenExcel();
    Map<String, String> data = new HashMap<String, String>();

    // Acción al dar clic sobre elementos con datos de una tabla Excel
    @Given("^Se da clic sobre elements (\\d+)$")
    public void seIngresaALaUrlDeDemoQA(int row, DataTable dataExcel) {
        // Convertir la tabla de datos de Cucumber a una lista de mapas
        List<Map<String, String>> dataFeature = dataExcel.asMaps(String.class, String.class);

        // Crear objeto Excel para leer datos
        Excel excel = new Excel(dataFeature.get(0).get("Ruta Excel"), dataFeature.get(0).get("Pestana"), true, row);

        // Leer datos desde Excel y almacenarlos en el mapa "data"
        data = dataDriverExcel.leerExcel(excel);

        // Ejecutar paso correspondiente a la funcionalidad de enlaces
        funcionalidadEnlacesStep.formularioElementosStep();
    }

    // Acción al cliquear en links
    @When("Se cliquea en links")
    public void seDaClicEnForm() {
        // Implementar la lógica para navegar a la sección "Forms" y seleccionar "Practice Form"
        funcionalidadEnlacesStep.formularioLinksStep();
    }

    // Validar todos los links encontrados
    @Then("Se valida todos los links")
    public void seHaceClicEnPracticeForm() {
        // Implementar la lógica para validar los links encontrados
        funcionalidadEnlacesStep.formularioLinksEncontradosStep(
                data.get("created"), data.get("noContent"), data.get("moved"),
                data.get("badRequest"), data.get("unauthorized"), data.get("forbidden"),
                data.get("notFound"), data.get("home"), data.get("homeTygnI")
        );
    }
}