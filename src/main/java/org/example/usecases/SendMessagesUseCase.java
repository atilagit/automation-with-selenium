package org.example.usecases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.datasources.ArtistDataSource;
import org.example.entities.Artist;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import static org.example.utils.Pauser.waitSomeTime;

public class SendMessagesUseCase {

    ArtistDataSource artistDataSource;

    public SendMessagesUseCase(ArtistDataSource artistDataSource) {
        this.artistDataSource = artistDataSource;
    }

    public void execute() {
        List<Artist> artists = artistDataSource.loadArtists();
        Scanner scanner = new Scanner(System.in);
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.get("https://web.whatsapp.com/");

        // Inicializar o WebDriverWait com o WebDriver e o tempo de espera máximo
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(2));

        // Esperar até que o elemento com o ID "side" esteja presente na página
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("side")));

        waitSomeTime();

        for (Artist artist : artists) {
            driver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div[2]/div[2]/div/div[1]/p")).click();
            driver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div[2]/div[2]/div/div[1]/p")).sendKeys(artist.getArtistPhoneNumber());
            waitSomeTime();
            String xpathByTitle = "//span[@title='" + artist.getWhatsappName() +"']";
            driver.findElement(By.xpath(xpathByTitle)).click();
            waitSomeTime();
            driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p")).click();
            driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p"))
                    .sendKeys("Fala " + artist.getInformalName() +" tudo blz? Isso é uma mensagem de teste, pode ignorar.");
            driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[2]/button/span")).click();
            waitSomeTime();
        }
        driver.quit();
        scanner.close();
    }
}