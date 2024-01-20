package org.example.usecases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.entities.Artist;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.Scanner;

import static org.example.utils.Pauser.waitSometime;

public class SendMessagesUseCase {

    public void execute(List<Artist> artists){
        Scanner scanner = new Scanner(System.in);
        WebDriver driver = WebDriverManager.chromedriver().create();
        driver.get("https://web.whatsapp.com/");

        System.out.println("Faça autenticação via QRcode e pressione Enter no console para continuar...");
        scanner.nextLine();

        for (Artist artist : artists) {
            driver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div[2]/div[2]/div/div[1]/p")).click();
            driver.findElement(By.xpath("//*[@id=\"side\"]/div[1]/div/div[2]/div[2]/div/div[1]/p")).sendKeys(artist.getArtistPhoneNumber());
            waitSometime();
            String xpathByTitle = "//span[@title='" + artist.getArtistName() +"']";
            driver.findElement(By.xpath(xpathByTitle)).click();
            waitSometime();
            driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p")).click();
            driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p")).sendKeys("Fala " + artist.getArtistName() +" tudo blz? Isso é uma mensagem de teste, pode ignorar.");
            driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[2]/button/span")).click();
            waitSometime();
        }
        driver.quit();
        scanner.close();
    }
}