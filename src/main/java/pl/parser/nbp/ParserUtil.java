package pl.parser.nbp;

public interface ParserUtil {
    double commaStringToDouble(String rate) throws Exception;
    boolean isBuyOrSell(String rateType);
    String geRateType();

}
