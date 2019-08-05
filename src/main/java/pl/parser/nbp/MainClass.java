package pl.parser.nbp;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

public class MainClass {

    public static void main(String[] args) throws Exception {

        BasicConfigurator.configure();                                                      // Log4j configuration
        final Logger log = LogManager.getRootLogger();
        InputManager inputManager = new InputManager(args[0],args[1],args[2]);
        FilesManager filesManager = new FilesManager(inputManager);
        Parser parser = new Parser();

        List<File> xmlFiles = filesManager.getXmlFiles();
        parser.unmarshalXmlFilesToObjects(xmlFiles);

        Double meanRate = Facade.getMeanRateType(parser, inputManager.getCurrencyCode());
        log.info(new DecimalFormat("#0.00000").format(meanRate));

        Double rateStandardDeviation = Facade.getRateStandardDeviation(parser, inputManager.getCurrencyCode());
        log.info(new DecimalFormat("#0.00000").format(rateStandardDeviation));
    }
}
