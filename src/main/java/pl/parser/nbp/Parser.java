package pl.parser.nbp;

import pl.parser.nbp.model.Position;
import pl.parser.nbp.model.RatesTable;
import pl.parser.nbp.services.RatesTableService;

public class Parser {

    private ParserUtil parserUtil;
    private String currencyCode;
    private RatesTableService ratesTableService;

    public Parser(String currencyCode, ParserUtil parserUtil, RatesTableService ratesTableService){
        this.currencyCode = currencyCode;
        this.parserUtil = parserUtil;
        this.ratesTableService = ratesTableService;
    }

    // Policzenie średniej kursu sprzedaży, bądź kupna, dla danych w liście "ratesTables"

    public double getMeanRate() throws Exception {
        double rate = 0d;
        String kurs = null;
        String rateType = parserUtil.geRateType();

        for (RatesTable entity : ratesTableService.getRatesTables()) {
            for (Position pozycja : entity.getPosition()) {
                if (pozycja.getCurrencyCode().equals(currencyCode)) {

                    if ("Buy".equals(rateType)) {
                        kurs = pozycja.getBuyingRate();
                    } else if("Sell".equals(rateType)){
                        kurs = pozycja.getSellingRate();
                    }
                    rate += parserUtil.commaStringToDouble(kurs);
                }
            }
        }
        return rate / ratesTableService.getRatesTables().size();
    }

    // Policzenie odchylenia standardowego kursu sprzedaży, bądź kupna, dla danych w liście "ratesTables" i podanego kodu waluty

    public double getStandardDeviation() throws Exception {
        double tmp = 0d;
        double rateMean = getMeanRate();
        String stringKurs;
        double doubleKurs;

        for (RatesTable entity : ratesTableService.getRatesTables()) {
            for (Position pozycja : entity.getPosition()) {
                if (pozycja.getCurrencyCode().equals(currencyCode)) {

                    stringKurs = pozycja.getSellingRate();
                    doubleKurs = parserUtil.commaStringToDouble(stringKurs);
                    tmp += Math.pow(doubleKurs - rateMean, 2);
                }
            }
        }
        return Math.sqrt(tmp / ratesTableService.getRatesTables().size());
    }

}
