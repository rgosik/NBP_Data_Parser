package pl.parser.nbp.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class ParserUtilImpl implements ParserUtil{

    private static final Logger log = LogManager.getRootLogger();
    private static Scanner scanIn = new Scanner(System.in);

    // Konwersja danych dotyczącyh kursów walut, ze String na Double (pliki xml zawierają dane o kursach oddzielając część dziesiętną przecinkiem)

    @Override
    public double commaStringToDouble(String rate) throws Exception {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(rate);
        return number.doubleValue();
    }

    @Override
    public boolean isBuyOrSell(String rateType){
        return rateType.matches("Buy|Sell");
    }

    @Override
    public String geRateType() {
        String rateType;

        while(true){
            rateType = scanIn.nextLine();
            if(isBuyOrSell(rateType)){
                break;
            } else {
                log.info("Incorrect input\nCorrect ones are \"Buy\" or \"Sell\"\nTry again");
            }
        }
        return rateType;
    }

}
