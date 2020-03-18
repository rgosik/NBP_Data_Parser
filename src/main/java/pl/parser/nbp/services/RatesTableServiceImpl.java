package pl.parser.nbp.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pl.parser.nbp.model.RatesTable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RatesTableServiceImpl implements RatesTableService {

    private static final Logger log = LogManager.getRootLogger();

    private Set<RatesTable> ratesTables = new HashSet<>();

    public RatesTableServiceImpl(List<File> xmlFiles) {
       unmarshalXmlFilesToObjects(xmlFiles);
    }

    // Konwertowanie danych z plików XML do listy "ratesTables"

    @Override
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

    @Override
    public Set<RatesTable> getRatesTables() {
        return ratesTables;
    }
}
