package View.Persistance.WebService;

import Model.Currency;
import Model.ExchangeRate;
import View.Persistance.ExchangeRateLoader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExchangeRateWebService implements ExchangeRateLoader{
    
    public ExchangeRateWebService() {}

    @Override
    public ExchangeRate loadAExchangeRate(Currency from, Currency to) {
        
        double rate = 0;
        try {
            URL url = new URL("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/latest/currencies/" + from.getCode().toLowerCase()
                    + "/" + to.getCode().toLowerCase() + ".json");
            
            URLConnection urlConnection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = "";
            
            for (int i = 0; i < 3; i++) {
                line = reader.readLine();
                if (line == null) break;
            }
            
            line = line.substring(line.indexOf(":")+2, line.length());
            rate = Double.parseDouble(line);

        } catch (MalformedURLException ex) {
            Logger.getLogger(ExchangeRateWebService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExchangeRateWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ExchangeRate(rate, from, to);
    }
}
