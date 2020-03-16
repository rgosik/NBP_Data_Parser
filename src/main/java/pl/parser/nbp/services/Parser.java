package pl.parser.nbp.services;

import pl.parser.nbp.model.Position;
import pl.parser.nbp.repositories.Data;
import pl.parser.nbp.repositories.RatesTableRepository;

public class Parser {

    private ParserUtil parserUtil;
    private String currencyCode;
    private Data data;

    public Parser(String currencyCode, ParserUtil parserUtil, Data data){
        this.currencyCode = currencyCode;
        this.parserUtil = parserUtil;
        this.data = data;
    }

    // Policzenie średniej kursu sprzedaży, bądź kupna, dla danych w liście "ratesTables"

    public double getMeanRate() throws Exception {
        double rate = 0d;
        String kurs = null;
        String rateType = parserUtil.geRateType();

        for (RatesTableRepository entity : data.getRatesTables()) {
            for (Position pozycja : entity.getPozycja()) {
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
        return rate / data.getRatesTables().size();
    }

    // Policzenie odchylenia standardowego kursu sprzedaży, bądź kupna, dla danych w liście "ratesTables" i podanego kodu waluty

    public double getStandardDeviation() throws Exception {
        double tmp = 0d;
        double rateMean = getMeanRate();
        String stringKurs;
        double doubleKurs;

        for (RatesTableRepository entity : data.getRatesTables()) {
            for (Position pozycja : entity.getPozycja()) {
                if (pozycja.getCurrencyCode().equals(currencyCode)) {

                    stringKurs = pozycja.getSellingRate();
                    doubleKurs = parserUtil.commaStringToDouble(stringKurs);
                    tmp += Math.pow(doubleKurs - rateMean, 2);
                }
            }
        }
        return Math.sqrt(tmp / data.getRatesTables().size());
    }

}
