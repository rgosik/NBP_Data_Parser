package pl.parser.nbp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Facade {

    private static final Logger log = LogManager.getRootLogger();
    private static Scanner scanIn = new Scanner(System.in);

    // Konwersja danych dotyczącyh kursów walut, ze String na Double (pliki xml zawierają dane o kursach oddzielając część dziesiętną przecinkiem)
    public static double commaStringToDouble(String rate) throws Exception {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(rate);
        return number.doubleValue();
    }

    private static boolean isBuyOrSell(String rateType){
        return rateType.matches("Buy|Sell");
    }

    public static double getMeanRateType(Parser parser) throws Exception{
        String meanRateType;
        double meanRate;
        log.info("Mean rate. Buy or Sell ?: ");

        while(true){
            meanRateType = scanIn.nextLine();
            if(isBuyOrSell(meanRateType)){
                break;
            } else {
                log.info("Incorrect input\nCorrect: \"Buy\" or \"Sell\"\nTry again");
            }
        }

        meanRate = parser.getMeanRate(meanRateType);
        return meanRate;
    }

    public static double getRateStandardDeviation(Parser parser) throws Exception{
        String standardDeviationType;
        double rateStandardDeviation;
        log.info("Standarn deviation. Buy or Sell ?: ");

        while(true){
            standardDeviationType = scanIn.nextLine();
            if(isBuyOrSell(standardDeviationType)){
                break;
            } else {
                log.info("Incorrect input\nCorrect: \"Buy\" or \"Sell\"\nTry again");
            }
        }

        rateStandardDeviation = parser.getRateStandardDeviation(standardDeviationType);
        return rateStandardDeviation;
    }

}
