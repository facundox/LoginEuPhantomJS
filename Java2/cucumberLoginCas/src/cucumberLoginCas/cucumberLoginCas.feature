Feature: CucumberJUnit

Scenario: Ingreso a CAS

Given El usuario cuenta con credenciales existentes en GDE

When Ingresa a la URL "https://cas.nac.everis.int/"
When El usuario ingresa al sistema con sus credenciales: usuario "NILRACOLOS" y password "Zaq12w"
Then El usuario ingresa a EU mediante la url "http://eu.nac.everis.int/eu-web/"