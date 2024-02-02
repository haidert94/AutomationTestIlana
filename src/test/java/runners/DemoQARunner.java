package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/", // Ruta de las características (archivos .feature)
        glue = "", // Paquetes o clases que contienen los pasos (steps). Puede dejarse en blanco si los steps están en el mismo paquete que este runner.
        tags = "@DemoQA" // Etiquetas de Cucumber para ejecutar escenarios específicos
)
public class DemoQARunner {

}