package org.example.usecases;

import org.openqa.selenium.WebDriver;

import static org.example.utils.WebDriverSingleton.closeWebDriverInstance;
import static org.example.utils.WebDriverSingleton.getWebDriverInstance;

public class LogoutUseCase {

    static WebDriver driver = getWebDriverInstance();

    public void execute() {
        disconnect();
        closeWebDriverInstance();
    }

    private void disconnect() {
        //TODO
    }
}