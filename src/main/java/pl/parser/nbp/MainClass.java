package pl.parser.nbp;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();                                                      // Log4j configuration
        final Logger log = LogManager.getRootLogger();

        InputManager inputManager = new InputManager();
        inputManager.initCode(args[0]);
        inputManager.initDates(args[1], args[2]);
        Parser parser = new Parser(inputManager);

        parser.unmarshalXmlFilesToObjects();

        Double meanRate = Utilities.getMeanRateType(parser);
        log.info(new DecimalFormat("#0.00000").format(meanRate));

        Double rateStandardDeviation = Utilities.getRateStandardDeviation(parser);
        log.info(new DecimalFormat("#0.00000").format(rateStandardDeviation));
    }
}
