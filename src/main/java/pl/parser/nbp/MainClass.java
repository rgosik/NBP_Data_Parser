package pl.parser.nbp;

import org.apache.log4j.BasicConfigurator;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();                                                      // Log4j configuration
        InputManager inputManager = new InputManager();
        inputManager.initCode(args[0]);
        inputManager.initDates(args[1], args[2]);
        Parser parser = new Parser(inputManager);

        parser.unmarshalXmlFilesToObjects();

        Double meanRate = Utilities.getMeanRateType(parser);
        System.out.println(new DecimalFormat("#0.00000").format(meanRate));

        Double rateStandardDeviation = Utilities.getRateStandardDeviation(parser);
        System.out.println(new DecimalFormat("#0.00000").format(rateStandardDeviation));
    }
}
