package pl.parser.nbp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

public class Utilities {
    private static final Logger log = LogManager.getRootLogger();
    private static Scanner scanIn = new Scanner(System.in);

    // Konwersja danych dotyczącyh kursów walut, ze String na Double (pliki xml zawierają dane o kursach oddzielając część dziesiętną przecinkiem)
    public static double stringRateToDouble(String rate) throws Exception {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(rate);
        return number.doubleValue();
    }

    private static boolean isBuyOrSell(String rateType){
        return rateType.matches("Buy|Sell");
    }

    private static boolean isNotBuyOrSell(String rateType){
        return !isBuyOrSell(rateType);
    }

    public static double getMeanRateType(Parser parser) throws Exception{
        String meanRateType;
        Double meanRate = 0.0;
        log.info("Mean rate. Buy or Sell ?: ");

        while(true){
            meanRateType = scanIn.nextLine();
            if(isNotBuyOrSell(meanRateType)){
                log.info("Incorrect input\nCorrect: \"Buy\" or \"Sell\"\nTry again");
                continue;
            } else {
                break;
            }
        }

        meanRate = parser.getMeanRate(meanRateType);
        return meanRate;
    }

    public static double getRateStandardDeviation(Parser parser) throws Exception{
        String standardDeviationType;
        Double rateStandardDeviation = 0.0;
        log.info("Standarn deviation. Buy or Sell ?: ");

        while(true){
            standardDeviationType = scanIn.nextLine();
            if(isNotBuyOrSell(standardDeviationType)){
                log.info("Incorrect input\nCorrect: \"Buy\" or \"Sell\"\nTry again");
                continue;
            } else {
                break;
            }
        }

        rateStandardDeviation = parser.getRateStandardDeviation(standardDeviationType);
        return rateStandardDeviation;
    }

}
