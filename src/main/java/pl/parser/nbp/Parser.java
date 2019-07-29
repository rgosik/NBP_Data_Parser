package pl.parser.nbp;

import lombok.Data;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pl.parser.nbp.xmlmodel.Position;
import pl.parser.nbp.xmlmodel.RatesTable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class Parser {

    private static final Logger log = LogManager.getRootLogger();
    private List<RatesTable> ratesTables = new ArrayList<>();
    private InputManager inputManager;


    public Parser(InputManager inputManager){
        this.inputManager = inputManager;
    }

    // Parsowanie danych z plików XML do listy "ratesTables"

    public void unmarshalXmlFilesToObjects() throws Exception {
        JAXBContext jaxbContext;
        FilesManager filesManager = new FilesManager(inputManager);
        List<File> xmlFiles = filesManager.getXmlFiles();

        for (File xmlFile : xmlFiles) {
            try {
                jaxbContext = JAXBContext.newInstance(RatesTable.class);

                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                ratesTables.add((RatesTable) jaxbUnmarshaller.unmarshal(xmlFile));

            } catch (JAXBException e) {
                log.error("failed", e);
            }
        }
    }

    // Policzenie średniej kursu sprzedaży, bądź kupna, dla danych w liście "ratesTables"

    public double getMeanRate(String rateType) throws Exception {
        double rate = 0d;
        String kurs = null;

        for (RatesTable ratesTable : ratesTables) {
            for (Position pozycja : ratesTable.getPozycja()) {
                if (pozycja.getKod_waluty().equals(inputManager.getCurrencyCode())) {

                    if ("Buy".equals(rateType)) {
                        kurs = pozycja.getKurs_kupna();
                    } else if("Sell".equals(rateType)){
                        kurs = pozycja.getKurs_sprzedazy();
                    }
                    rate += Facade.commaStringToDouble(kurs);
                }
            }
        }
        return rate / ratesTables.size();
    }

    // Policzenie odchylenia standardowego kursu sprzedaży, bądź kupna, dla danych w liście "ratesTables" i podanego kodu waluty

    public double getRateStandardDeviation(String rateType) throws Exception {
        double tmp = 0d;
        double rateMean = getMeanRate(rateType);
        String stringKurs;
        double doubleKurs;

        for (RatesTable ratesTable : ratesTables) {
            for (Position pozycja : ratesTable.getPozycja()) {
                if (pozycja.getKod_waluty().equals(inputManager.getCurrencyCode())) {

                    stringKurs = pozycja.getKurs_sprzedazy();
                    doubleKurs = Facade.commaStringToDouble(stringKurs);
                    tmp += Math.pow(doubleKurs - rateMean, 2);
                }
            }
        }
        return Math.sqrt(tmp / ratesTables.size());
    }

}
