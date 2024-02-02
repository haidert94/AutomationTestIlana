package pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Página que representa la funcionalidad de enlaces
public class FuncionalidadEnlacesPage extends PageObject {

    // Elementos de la página mediante Serenity y Selenium
    @FindBy(xpath = "//div/h5[text()='Elements']")
    WebElementFacade btnElements;

    @FindBy(xpath = "//span[text()='Links']")
    WebElementFacade btnLinks;

    @FindBy(xpath = "//*[@id='linkWrapper']/p/a")
    List<WebElementFacade> llbLinks;

    @FindBy(xpath = "//*[@id='linkResponse']")
    WebElementFacade lblLinks;

    // Método para hacer clic en la sección de Elements
    public void elements() {
        btnElements.click();
    }

    // Método para hacer clic en la sección de Links
    public void links() {
        btnLinks.click();
    }

    // Método para validar y comparar enlaces
    public void linksEncontrados(String Created, String NoContent, String Moved, String BadRequest, String Unauthorized, String Forbidden, String NotFound, String Home, String HomeTygnI) {

        // Obtener la ventana principal
        String ventanaPrincipal = getDriver().getWindowHandle();

        // Lista de enlaces proporcionados
        List<String> linksData = new ArrayList<>();
        linksData.add(Created);
        linksData.add(NoContent);
        linksData.add(Moved);
        linksData.add(BadRequest);
        linksData.add(Unauthorized);
        linksData.add(Forbidden);
        linksData.add(NotFound);
        linksData.add(Home);
        linksData.add(HomeTygnI);

        // Ordenar la lista de enlaces alfabéticamente
        llbLinks = llbLinks.stream()
                .sorted(Comparator.comparing(elemento -> elemento.getText().trim()))
                .collect(Collectors.toList());

        // Imprimir en consola los enlaces ordenados alfabéticamente
        System.out.println("Elementos ordenados alfabéticamente: " + llbLinks);
        System.out.println("Elementos ordenados alfabéticamente2: " + linksData);
        System.out.println("Elementos ordenados alfabéticamente2: " + Created);

        // Iterar sobre la lista de enlaces
        for (int i = 0; i < llbLinks.size(); i++) {
            // Hacer clic en el enlace actual
            llbLinks.get(i).click();

            // Iterar sobre la lista de datos de enlaces
            for (int j = 0; j < linksData.size(); j++) {
                // Validar si el texto del enlace coincide con el dato esperado
                if (lblLinks.getText().equals(linksData.get(j))) {
                    Assert.assertEquals(lblLinks.getText(), linksData.get(j));
                    System.out.println("comparacion: " + lblLinks.getText() + " // " + linksData.get(j));
                    break;
                } else if (llbLinks.get(i).getText().contains(Home)) {
                    // Validar si el enlace es de tipo "Home" y volver a la ventana principal
                    btnElements.isVisible();
                    getDriver().switchTo().window(ventanaPrincipal);
                    System.out.println("comparacionHome: " + lblLinks.getText() + linksData.get(j));
                    break;
                }
            }
        }
    }
}