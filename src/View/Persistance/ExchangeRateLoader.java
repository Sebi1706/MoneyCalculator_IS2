package View.Persistance;

import Model.Currency;
import Model.ExchangeRate;

public interface ExchangeRateLoader {
    
    public ExchangeRate loadAExchangeRate(Currency to, Currency from);
}
