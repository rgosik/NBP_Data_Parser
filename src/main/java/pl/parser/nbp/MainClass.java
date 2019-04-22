package pl.parser.nbp;

import java.text.DecimalFormat;

public class MainClass {

    public static void main(String[] args) throws Exception {
        InputManager.initCode(args[0]);
        InputManager.initDates(args[1], args[2]);

        FilesManager.getXmlFiles();

        Parser.unmarshalXmlFilesToObjects();
        Double meanBuyRate = Parser.getMeanRate("Buy");
        Double sellRateStandardDeviation = Parser.getRateStandardDeviation();

        System.out.println(new DecimalFormat("#0.0000").format(meanBuyRate));
        System.out.println(new DecimalFormat("#0.0000").format(sellRateStandardDeviation));
    }
}
