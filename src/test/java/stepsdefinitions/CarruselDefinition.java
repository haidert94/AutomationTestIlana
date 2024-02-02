package stepsdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.CarruselStep;
import utilities.data.DataDrivenExcel;
import utilities.data.Excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Definición de los pasos relacionados con el carrusel
public class CarruselDefinition {

    @Steps
    CarruselStep carruselStep;

    // Utilidades para leer datos desde Excel
    DataDrivenExcel dataDriverExcel = new DataDrivenExcel();
    Map<String, String> data = new HashMap<String, String>();

    // Acción cuando se hace clic sobre widgets con datos de una tabla Excel
    @When("^Clic sobre widgets (\\d+)$")
    public void clicSobreWidgets(int row, DataTable dataExcel) {
        // Convertir la tabla de datos de Cucumber a una lista de mapas
        List<Map<String, String>> dataFeature = dataExcel.asMaps(String.class, String.class);

        // Crear objeto Excel para leer datos
        Excel excel = new Excel(dataFeature.get(0).get("Ruta Excel"), dataFeature.get(0).get("Pestana"), true, row);

        // Leer datos desde Excel y almacenarlos en el mapa "data"
        data = dataDriverExcel.leerExcel(excel);
        // Ejecutar paso correspondiente al widget
        carruselStep.widgetsStep();
    }

    // Acción cuando se hace clic en el slider
    @When("Clic en slider")
    public void clicEnSlider() {
        carruselStep.sliderStep();
    }

    // Validar la navegación y manipulación del slider
    @Then("Se valida la navegacion")
    public void seValidaLaNavegacion() throws InterruptedException {
        // Implementar la lógica para navegar a la sección "Forms" y seleccionar "Practice Form"
        carruselStep.sliderValueStep(data.get("slidervalue"));
    }
}
