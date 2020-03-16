package pl.parser.nbp.services;

public interface ParserUtil {
    double commaStringToDouble(String rate) throws Exception;
    boolean isBuyOrSell(String rateType);
    String geRateType();

}
