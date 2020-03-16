package pl.parser.nbp;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import pl.parser.nbp.repositories.Data;
import pl.parser.nbp.repositories.DataImpl;
import pl.parser.nbp.services.*;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

public class MainClass {

    public static void main(String[] args) throws Exception {

        // Log4j configuration
        BasicConfigurator.configure();
        final Logger log = LogManager.getRootLogger();

        InputManager inputManager = new InputManagerImpl(args[0],args[1],args[2]);
        FilesManager filesManager = new FilesManager(inputManager);

        List<File> xmlFiles = filesManager.getXmlFiles();

        ParserUtil parserUtil = new ParserUtilImpl();
        Data data = new DataImpl(xmlFiles);
        Parser parser = new Parser(inputManager.getCurrencyCode(), parserUtil, data);


        if(data.unmarshalXmlFilesToObjects(xmlFiles)) {

            log.info("Mean Rate, Buy or Sell ?: ");
            Double meanRate = parser.getMeanRate();
            log.info(new DecimalFormat("#0.00000").format(meanRate));

            log.info("Standard Deviation, Buy or Sell ?: ");
            Double rateStandardDeviation = parser.getStandardDeviation();
            log.info(new DecimalFormat("#0.00000").format(rateStandardDeviation));
        } else {
            log.error("Failed to unmarshal the data");
        }
    }
}
