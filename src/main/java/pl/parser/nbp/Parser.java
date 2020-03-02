package pl.parser.nbp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pl.parser.nbp.model.Position;
import pl.parser.nbp.model.RatesTable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final Logger log = LogManager.getRootLogger();
    private ParserUtil parserUtil;
    private List<RatesTable> ratesTables = new ArrayList<>();
    private String currencyCode;

    public Parser(String currencyCode, ParserUtil parserUtil){
        this.currencyCode = currencyCode;
        this.parserUtil = parserUtil;
    }

    // Parsowanie danych z plików XML do listy "ratesTables"

    public boolean unmarshalXmlFilesToObjects(List<File> xmlFiles) {
        JAXBContext jaxbContext;

        for (File xmlFile : xmlFiles) {
            try {
                jaxbContext = JAXBContext.newInstance(RatesTable.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                ratesTables.add((RatesTable) jaxbUnmarshaller.unmarshal(xmlFile));

            } catch (JAXBException e) {
                log.error("failed", e);
                return false;
            }
        }

        return true;
    }

    // Policzenie średniej kursu sprzedaży, bądź kupna, dla danych w liście "ratesTables"

    public double getMeanRate() throws Exception {
        double rate = 0d;
        String kurs = null;
        String rateType = parserUtil.geRateType();

        for (RatesTable ratesTable : ratesTables) {
            for (Position pozycja : ratesTable.getPozycja()) {
                if (pozycja.getKod_waluty().equals(currencyCode)) {

                    if ("Buy".equals(rateType)) {
                        kurs = pozycja.getKurs_kupna();
                    } else if("Sell".equals(rateType)){
                        kurs = pozycja.getKurs_sprzedazy();
                    }
                    rate += parserUtil.commaStringToDouble(kurs);
                }
            }
        }
        return rate / ratesTables.size();
    }

    // Policzenie odchylenia standardowego kursu sprzedaży, bądź kupna, dla danych w liście "ratesTables" i podanego kodu waluty

    public double getStandardDeviation() throws Exception {
        double tmp = 0d;
        double rateMean = getMeanRate();
        String stringKurs;
        double doubleKurs;

        for (RatesTable ratesTable : ratesTables) {
            for (Position pozycja : ratesTable.getPozycja()) {
                if (pozycja.getKod_waluty().equals(currencyCode)) {

                    stringKurs = pozycja.getKurs_sprzedazy();
                    doubleKurs = parserUtil.commaStringToDouble(stringKurs);
                    tmp += Math.pow(doubleKurs - rateMean, 2);
                }
            }
        }
        return Math.sqrt(tmp / ratesTables.size());
    }

}
