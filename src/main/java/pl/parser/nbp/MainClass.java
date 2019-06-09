package pl.parser.nbp;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws Exception {
        InputManager inputManager = new InputManager();
        inputManager.initCode(args[0]);
        inputManager.initDates(args[1], args[2]);
        Scanner scanIn = new Scanner(System.in);
        Parser parser = new Parser(inputManager);

        parser.unmarshalXmlFilesToObjects();

        System.out.println("Mean rate, Buy or Sell: ");
        String  meanRateType = scanIn.nextLine();
        Double meanRate = 0.0;
        if(Utilities.isBuyOrSell(meanRateType)){
            meanRate = parser.getMeanRate(meanRateType);
        } else {
            System.out.println("Incorrect input\nCorrect: \"Buy\" or \"Sell\"\nExiting the program");
            System.exit(0);
        }

        System.out.println("Standarn deviation, Buy or Sell: ");
        String  standardDeviationType= scanIn.nextLine();
        Double rateStandardDeviation = 0.0;
        if(Utilities.isBuyOrSell(standardDeviationType)){
            rateStandardDeviation = parser.getRateStandardDeviation(standardDeviationType);
        } else {
            System.out.println("Incorrect input\nCorrect: \"Buy\" or \"Sell\"\nExiting the program");
            System.exit(0);
        }
        scanIn.close();

        System.out.println(new DecimalFormat("#0.00000").format(meanRate));
        System.out.println(new DecimalFormat("#0.00000").format(rateStandardDeviation));
    }
}
