import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;

public class CashWithdrawalPageTest {
    public static void main(String[] args){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sujat\\Downloads\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://qawithdrawal.ccbp.tech/");

        WebElement userNameEl = driver.findElement(By.cssSelector("div[class *= 'details'] > p"));

        String expectedName = "Sarah Williams";
        String currName = userNameEl.getText();

        if(expectedName.equals(currName)){
            System.out.println("User name is correct.");
        }else{
            System.out.println("User name is incorrect.");
        }

        WebElement balanceEl = driver.findElement(By.cssSelector("p.balance"));

        String expectedBalance = "2000";
        String currBalance = balanceEl.getText();

        if(expectedBalance.equals(currBalance)){
            System.out.println("Initial balance is correct.");
        }else{
            System.out.println("Initial balance is incorrect.");
        }

        List<WebElement> denominations = driver.findElements(By.cssSelector("ul[class ^= 'denominations'] button"));

        int i = 0;

        for(i=0;i<denominations.size();i++){
            WebElement denominationEl = denominations.get(i);

            int expectedAmount = Integer.parseInt(currBalance) - Integer.parseInt(denominationEl.getText())*2;

            int amount = Integer.parseInt(currBalance);

            for(int j=0;j<2;j++){
                denominationEl.click();
                amount = amount - Integer.parseInt(denominationEl.getText());
            }
            if(expectedAmount != amount){
                System.out.println("Mismatch found in balance");
                break;
            }

        }

        if(i == denominations.size()){
            System.out.println("Withdrawal App working as expected.");
        }

        driver.quit();


    }
}





















