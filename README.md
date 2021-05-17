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
        tests.fail=1 # -> 0 para que todo corra ; >0 falla 
        # Should selenium display the browser?
        display.browser=true # -> true con GUI ; false para CICD 
        destination.url=https://example.org # -> URL a pegarle (básico)
--------------------------------------
```
## La configuración del XML para que le diga a Selenium que clase corre selenium es testing.xml 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="Default Suite">
    <test name="users-suite">
        <classes>
            <class name="MockedTests"/>
        </classes>
    </test>
</suite>
```
## Esas propiedades la lee el file PropertiesReader.java 
Que las deja preparadas para ser usada por MockedTests.java

## Los tests se editan en MockedTests.java 
Con esta función se determina que hay que usar el formato headless <- setHeadlessDriver();
this.driver = new ChromeDriver(new ChromeOptions().setHeadless(true));

