package Controller;

import Model.Currency;
import Model.Money;
import View.Persistance.ExchangeRateLoader;
import View.ui.Dialog;
import View.ui.Swing.DialogSwing;
import View.ui.Display;
import View.ui.Swing.DisplaySwing;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame{
    
    private Display dp;
    private Dialog dl;
    private final ExchangeRateLoader loader;

    public MainFrame(List<Currency> currencies, ExchangeRateLoader loader) {
        
        this.loader = loader;
        this.setTitle("MoneyCalculator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(342, 370);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        this.add((DialogSwing) setDialog(currencies));
        this.add((DisplaySwing) setDisplay());
        this.setVisible(true);
    }
    
    private Dialog setDialog(List<Currency> currencies){
        this.dl = new DialogSwing(currencies, this);
        return dl;
    }
    
    private Display setDisplay(){
        this.dp = new DisplaySwing();
        return dp;
    }
    
    public void updateDisplay(){
        
        Money money = this.dl.getMoney();
        if (money.getAmount() > 0){
            Currency currencyTo = this.dl.getCurrencyTo();
            Money newMoney = calculateAmount(money, currencyTo);
            this.dp.display(newMoney);
        }
    }
    
    public void DisplayError(){
        this.dp.resetDisplay();
    }

    private Money calculateAmount(Money money, Currency currencyTo) {
        return new Money(money.getAmount() * loader.loadAExchangeRate(money.getCurrency(), currencyTo).getRate(), currencyTo);
    }
}
