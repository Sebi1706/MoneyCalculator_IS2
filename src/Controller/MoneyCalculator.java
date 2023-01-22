package Controller;

import Model.Currency;
import View.Persistance.CurrencyLoader;
import View.Persistance.ExchangeRateLoader;
import View.Persistance.Files.CurrencyLoaderArchive;
import View.Persistance.WebService.ExchangeRateWebService;

import java.util.List;

public class MoneyCalculator {

    public static void main(String[] args) {
        
        CurrencyLoader currencyLoader = new CurrencyLoaderArchive("currency.txt");
        List<Currency> listCurrencies = currencyLoader.loadAllCurrencies();
        ExchangeRateLoader exchangeRateLoader = new ExchangeRateWebService();
        MainFrame gui = new MainFrame(listCurrencies, exchangeRateLoader);
    }
}

