package View.Persistance.Files;

import Model.Currency;
import View.Persistance.CurrencyLoader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyLoaderArchive implements CurrencyLoader {
    
    private final String filePathname;
    
    public CurrencyLoaderArchive(String filePathName){
        this.filePathname = filePathName;
    }
    
    private Currency currencyOf(String lineText) {
        String[] splitLine = lineText.split(",");
        return new Currency(splitLine[0], splitLine[1], splitLine[2]);
    }

    @Override
    public List<Currency> loadAllCurrencies() {
        List<Currency> listCurrencies = new ArrayList<>();
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File(this.filePathname)));
            while (true){
                String lineText = reader.readLine();
                if (lineText == null) break;
                listCurrencies.add(currencyOf(lineText));
            }
        }
        
        catch (FileNotFoundException excepcion) {
            System.out.println("ERROR CurrencyLoaderArchive: loadAllCurrencies FileNotFoundException, " + excepcion.getMessage());
        } 
        catch (IOException ex) {
            System.out.println("ERROR CurrencyLoaderArchive: loadAllCurrencies IOException, " + ex.getMessage());
        }
        return listCurrencies;
    }
}
