package pl.parser.nbp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Parser {

    private static List<RatesTable> ratesTables = new ArrayList<>();

    // Parsowanie danych z plików XML do listy "ratesTables"

    static void unmarshalXmlFilesToObjects() throws Exception {
        JAXBContext jaxbContext;
        List<File> xmlFiles = FilesManager.getXmlFiles();

        for (File xmlFile : xmlFiles) {
            try {
                jaxbContext = JAXBContext.newInstance(RatesTable.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                ratesTables.add((RatesTable) jaxbUnmarshaller.unmarshal(xmlFile));

            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

    // Policzenie średniej kursu sprzedaży, bądź kupna, dla danych w liście "ratesTables"

    static double getMeanRate(String rateType) throws Exception {
        double rate = 0.0;
        String kurs = null;

        for (RatesTable ratesTable : ratesTables) {
            for (Position pozycja : ratesTable.getPozycja()) {
                if (pozycja.getKod_waluty().equals(InputManager.currencyCode)) {

                    if (rateType.equals("Buy")) {
                        kurs = pozycja.getKurs_kupna();
                    } else {
                        kurs = pozycja.getKurs_sprzedazy();
                    }
                    rate += Parser.stringRateToDouble(kurs);
                }
            }
        }
        return rate / ratesTables.size();
    }

    // Policzenie odchylenia standardowego kursu sprzedaży, bądź kupna, dla danych w liście "ratesTables" i podanego kodu waluty

    static double getRateStandardDeviation() throws Exception {
        double tmp = 0.0;
        double rateMean = getMeanRate("Sell");
        String stringKurs = null;
        double doubleKurs = 0;

        for (RatesTable ratesTable : ratesTables) {
            for (Position pozycja : ratesTable.getPozycja()) {
                if (pozycja.getKod_waluty().equals(InputManager.currencyCode)) {

                    stringKurs = pozycja.getKurs_sprzedazy();
                    doubleKurs = Parser.stringRateToDouble(stringKurs);
                    tmp += Math.pow(doubleKurs - rateMean, 2);
                }
            }
        }
        return Math.sqrt(tmp / ratesTables.size());
    }

    // Konwersja danych dotyczącyh kursów walut, ze String na Double (pliki xml zawierają dane o kursach oddzielając część dziesiętną przecinkiem)

    static private double stringRateToDouble(String rate) throws Exception {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = format.parse(rate);
        return number.doubleValue();
    }

}
