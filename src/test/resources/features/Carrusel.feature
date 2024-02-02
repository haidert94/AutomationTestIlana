#Author: Haider Fabian Tique
#Date:  01-02-2024

@DemoQA
@Carrusel
Feature: Como usuario deseo validar la navegacion
  del Slider

  @CarruselExitos
  Scenario Outline: Se valida la navegacion
    Given Se ingresa a la url de DemoQA
    When  Clic sobre widgets <fila>
      | Ruta Excel                         | Pestana  |
      | src/test/resources/data/datos.xlsx | Carrusel |
    And   Clic en slider
    Then  Se valida la navegacion
    Examples:
      | fila  |
      |   1   |