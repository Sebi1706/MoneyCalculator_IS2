package View.Persistance.WebService;

import Model.Currency;
import View.Persistance.CurrencyLoader;
import java.util.ArrayList;
import java.util.List;

public class CurrencyLoaderWebService implements CurrencyLoader{
    
    private final String URL;

    public CurrencyLoaderWebService(String URL) {
        this.URL = URL;
    }

    @Override
    public List<Currency> loadAllCurrencies() {
        List<Currency> listCurrencies = new ArrayList<>();
        return listCurrencies;
    }
    
}
