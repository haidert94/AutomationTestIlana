#Author: Haider Fabian Tique
#Date:  01-02-2024

@DemoQA
@FormularioRegistro
Feature: Como usuario deseo realizar el registro
  en la pagina

  @FormularioRegistroExitos
  Scenario Outline: Se valida el registro exitoso
    Given Se ingresa a la url de DemoQA
    When  Se da clic en form <fila>
      | Ruta Excel                         | Pestana            |
      | src/test/resources/data/datos.xlsx | FormularioRegistro |
    And   Se hace clic en practice form
    And   Se diligencia los campos del registro
    And   Se da clic boton submit
    Then  Se valida el mensaje exitoso
    Examples:
      | fila  |
      |   1   |


#  @FormularioRegistroExitos
#  Scenario Outline: Se valida el registro exitoso
#    Given Se ingresa a la url de DemoQA
#    When  Se da clic en form
#    And   Se hace clic en practice form
#    And   Se diligencia los campos del registro
#    And   Se da clic boton submit
#    Then  Se valida el mensaje exitoso
#    Examples:
#      | fila  |
#      |   1   |