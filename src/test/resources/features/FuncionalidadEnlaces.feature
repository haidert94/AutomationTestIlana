#Author: Haider Fabian Tique
#Date:  01-02-2024

@DemoQA
@FuncionalidadEnlaces
Feature: Como usuario deseo verificar la funcionalidad de los enlaces

  @FuncionalidadEnlacesExitos
  Scenario Outline: Se valida el enlaces exitoso
    Given Se ingresa a la url de DemoQA
    When  Se da clic sobre elements <fila>
      | Ruta Excel                         | Pestana          |
#      | src/test/resources/data/datos.xlsx | Enlaces          |
      | src/test/resources/data/datos.xlsx | RespuestaEnlaces |
    And   Se cliquea en links
    Then  Se valida todos los links
    Examples:
      | fila  |
      |   1   |