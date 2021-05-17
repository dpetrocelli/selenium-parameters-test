import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.PropertiesReader;

public class MockedTests {

    //public static final String URL = "https://example.org";
    private Integer failingTests;
    private String URL;

    private Integer alreadyFailed;
    private WebDriver driver;
    private boolean displayBrowser;

    @BeforeSuite
    public void setup() {
        PropertiesReader propsReader = new PropertiesReader();
        try {
            this.failingTests = Integer.valueOf(propsReader.getProps().getProperty("tests.fail"));
            displayBrowser = Boolean.parseBoolean(propsReader.getProps().getProperty("display.browser"));
            this.URL = propsReader.getProps().getProperty("destination.url");
        }
        catch (Exception e) {
            this.failingTests = 0;
            this.displayBrowser = false;
        }
        this.alreadyFailed = 0;
    }

    /**
     * Determines whether the test needs to fail. It's intended to be used in a assertTrue(determineFailure()) fashion
     * @return TRUE if the test doesn't have to fail - FALSE if it does
     */
    private Boolean determineFailure() {
        if (failingTests > alreadyFailed) {
            alreadyFailed += 1;
            return false;
        };
        return true;
    }

    @BeforeTest
    public void instantiateDriver() {
        try {
            // Set driver's path
            System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");

            if (displayBrowser) {
                this.driver = new ChromeDriver();
            }
            else {
                setHeadlessDriver();
            }
        }
        catch (Exception ignored){
            setHeadlessDriver();
        }
    }

    private void setHeadlessDriver() {
        this.driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
    }

    @Test(priority = 5)
    public void someTest() {
        this.driver.get(URL);
        Assert.assertTrue(determineFailure());
    }

    @Test(priority = 10)
    public void someOtherTest() {
        this.driver.get(URL);
        Assert.assertTrue(determineFailure());
    }

    @Test(priority = 15)
    public void yetSomeOtherTest() {
        this.driver.get(URL);
        Assert.assertTrue(determineFailure());
    }

    @Test(priority = 20)
    public void almostTheLastTest() {
        this.driver.get(URL);
        Assert.assertTrue(determineFailure());
    }

    @Test(priority = 25)
    public void theLastTest() {
        this.driver.get(URL);
        Assert.assertTrue(determineFailure());
    }

    @AfterSuite
    public void tearDown() {
        this.driver.close();
    }
}