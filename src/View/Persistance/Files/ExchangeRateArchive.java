package View.Persistance.Files;

import Model.Currency;
import Model.ExchangeRate;
import View.Persistance.ExchangeRateLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExchangeRateArchive implements ExchangeRateLoader{
    
    private final String fileName;

    public ExchangeRateArchive(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public ExchangeRate loadAExchangeRate(Currency from, Currency to) {
        
        double rate = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.fileName)));
            while (true){
                String lineText = reader.readLine();
                if (lineText == null) break;
                
                String[] splitLine = lineText.split(",");
                if (splitLine[1].equals(from.getCode())){
                    if (splitLine[2].equals(to.getCode())){
                        rate = Double.parseDouble(splitLine[0]);
                    }
                }
            }
        }
        
        catch (FileNotFoundException excepcion) {
            System.out.println("ERROR CurrencyLoaderArchive: loadAllCurrencies FileNotFoundException, " + excepcion.getMessage());
        } 
        catch (IOException ex) {
            System.out.println("ERROR CurrencyLoaderArchive: loadAllCurrencies IOException, " + ex.getMessage());
        }
        
        return new ExchangeRate(rate, from, to);
    }
}
