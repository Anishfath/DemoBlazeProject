package Utils;

import org.testng.annotations.DataProvider;

public class DataProviderUtil {

    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"Anish@Fath", "98765"},
            
        };
    }
}

