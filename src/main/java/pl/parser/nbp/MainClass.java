package pl.parser.nbp;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws Exception {
        InputManager.initCode(args[0]);
        InputManager.initDates(args[1], args[2]);
        Scanner scanIn = new Scanner(System.in);

        System.out.println("Mean rate, Buy or Sell: ");
        String  meanRateType = scanIn.nextLine();
        Double meanRate = null;
        if(Utilities.isBuyOrSell(meanRateType)){
            meanRate = Parser.getMeanRate(meanRateType);
        } else {
            System.out.println("Incorrect input\nCorrect: \"Buy\" or \"Sell\"\nExiting the program");
            System.exit(0);
        }

        System.out.println("Standarn deviation, Buy or Sell: ");
        String  standardDeviationType= scanIn.nextLine();
        Double rateStandardDeviation = null;
        if(Utilities.isBuyOrSell(standardDeviationType)){
            rateStandardDeviation = Parser.getRateStandardDeviation(standardDeviationType);
        } else {
            System.out.println("Incorrect input\nCorrect: \"Buy\" or \"Sell\"\nExiting the program");
            System.exit(0);
        }

        FilesManager.getXmlFiles();
        Parser.unmarshalXmlFilesToObjects();

        scanIn.close();
        System.out.println(new DecimalFormat("#0.00000").format(meanRate));
        System.out.println(new DecimalFormat("#0.00000").format(rateStandardDeviation));
    }
}
