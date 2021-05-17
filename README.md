# Selenium-parameters-test 

Este proyecto se encarga de realizar un conjunto de tests (parametizable en MockedTest) sobre una aplicación (API o FEND) que está corriendo (Selenium)
En este caso, solo apunta a un sitio X (example.com) para probar que se pueden hacer peticiones GET al destino y responde.

Las opciones de ejecución son a través de los siguientes dos modelos:
- Visual. Usa el chromedriver, levanta el web-browser y hace todos los requests
- Hidden. Usa el chromedriver, pero lo hace en background, siendo ideal para hacer CICD pipelines e integración 

# Configuraciones

## Configure Application Properties file
--------------------------------------
```java
# How many tests (up to 5) will fail?
tests.fail=1/0
# Should selenium display the browser?
display.browser=true/false
--------------------------------------
```
## Esas propiedades la lee el file PropertiesReader.java 

## Los tests se editan en MockedTests.java 
