package pl.parser.nbp;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) throws Exception {
        InputManager.initCode(args[0]);
        InputManager.initDates(args[1], args[2]);
        Scanner scanIn = new Scanner(System.in);

        FilesManager.getXmlFiles();
        Parser.unmarshalXmlFilesToObjects();

        System.out.println("Mean rate, Buy or Sell: ");
        String  meanRateType = scanIn.nextLine();
        Double meanRate = Parser.getMeanRate(meanRateType);

        System.out.println("Standarn deviation, Buy or Sell: ");
        String  standardDeviationType= scanIn.nextLine();
        Double rateStandardDeviation = Parser.getRateStandardDeviation(standardDeviationType);

        scanIn.close();
        System.out.println(new DecimalFormat("#0.0000").format(meanRate));
        System.out.println(new DecimalFormat("#0.0000").format(rateStandardDeviation));
    }
}
