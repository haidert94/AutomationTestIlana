package stepsdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import steps.FormularioRegistroStep;
import utilities.data.DataDrivenExcel;
import utilities.data.Excel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Definición de pasos para el formulario de registro
public class FormularioRegistroDefinition {

    @Steps
    FormularioRegistroStep formularioRegistroStep;

    // Utilidades para leer datos desde Excel
    DataDrivenExcel dataDriverExcel = new DataDrivenExcel();
    Map<String, String> data = new HashMap<String, String>();

    // Acción para ingresar a la URL de DemoQA
    @Given("^Se ingresa a la url de DemoQA$")
    public void seIngresaALaUrlDeDemoQA() {
        // Implementar la lógica para abrir la página web de DemoQA
        formularioRegistroStep.formularioBrowseStep();
    }

    // Acción al dar clic en el formulario con datos de una tabla Excel
    @When("^Se da clic en form (\\d+)$")
    public void seDaClicEnForm(int row, DataTable dataExcel) {
        // Convertir la tabla de datos de Cucumber a una lista de mapas
        List<Map<String, String>> dataFeature = dataExcel.asMaps(String.class, String.class);

        // Crear objeto Excel para leer datos
        Excel excel = new Excel(dataFeature.get(0).get("Ruta Excel"), dataFeature.get(0).get("Pestana"), true, row);

        // Leer datos desde Excel y almacenarlos en el mapa "data"
        data = dataDriverExcel.leerExcel(excel);

        // Ejecutar paso correspondiente al formulario
        formularioRegistroStep.formularioFormsStep();
    }

    // Acción al hacer clic en "Practice Form"
    @When("Se hace clic en practice form")
    public void seHaceClicEnPracticeForm() {
        // Implementar la lógica para navegar a la sección "Forms" y seleccionar "Practice Form"
        formularioRegistroStep.formularioPracticeStep();
    }

    // Acción al diligenciar los campos del formulario de registro
    @When("^Se diligencia los campos del registro$")
    public void seDiligenciaLosCamposDelRegistro() {
        // Implementar la lógica para llenar el formulario con la información proporcionada
        formularioRegistroStep.formularioRegistrationStep(data.get("firtsName"), data.get("lastName"), data.get("email"), data.get("mobile"), data.get("month"), data.get("year"), data.get("day"));
    }

    // Acción al hacer clic en el botón "Submit"
    @When("Se da clic boton submit")
    public void seDaClicBotonSubmit() {
        // Implementar la lógica para navegar a la sección "Forms" y seleccionar "Practice Form"
        formularioRegistroStep.formularioSubmitStep();
    }

    // Validar el mensaje exitoso después de completar el formulario
    @Then("^Se valida el mensaje exitoso$")
    public void seValidaElMensajeExitoso() {
        // Implementar la lógica para verificar que se muestra un mensaje de éxito
        formularioRegistroStep.formularioMessageStep(data.get("message"));
    }
}