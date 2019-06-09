package pl.parser.nbp;

import java.text.NumberFormat;
import java.util.Locale;

public class Utilities {

    // Konwersja danych dotyczącyh kursów walut, ze String na Double (pliki xml zawierają dane o kursach oddzielając część dziesiętną przecinkiem)
    static double stringRateToDouble(String rate) throws Exception {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(rate);
        return number.doubleValue();
    }

    static boolean 

}
