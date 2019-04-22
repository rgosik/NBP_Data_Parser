package pl.parser.nbp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputManager {

    static String currencyCode;
    static int formatedStartDate;
    static int formatedEndDate;
    static int startYear;
    static int endYear;

    static void initCode(String code) {
        String inputCode = code;

        if (!inputCode.matches("USD|EUR|CHF|GBP")) {
            System.out.println("Podano nieprawidłowy kod waluty");
            System.exit(0);
        }
        currencyCode = inputCode;
    }

    static void initDates(String dateS, String dateK) throws Exception {
        String startDate = inputDate(dateS);
        String endDate = inputDate(dateK);
        Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

        if (dateEnd.before(dateStart)) {
            System.out.println("Podana data końcowa jest wcześniejsza niż data początkowa");
            System.exit(0);
        }

        formatedStartDate = Integer.parseInt(startDate.replaceAll("[^a-zA-Z0-9]", "").substring(2));
        formatedEndDate = Integer.parseInt(endDate.replaceAll("[^a-zA-Z0-9]", "").substring(2));
        startYear = Integer.parseInt(dateS.substring(0, 4));
        endYear = Integer.parseInt(dateK.substring(0, 4));
    }

    static String inputDate(String date) {
        String inputDate = date;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        try {
            format.parse(inputDate);
        } catch (ParseException e) {
            System.out.println("Data: " + inputDate + " nie jest zgodna z wymaganym formatem: " +
                    ((SimpleDateFormat) format).toPattern() + ", lub podano nieistniejącą datę");
            System.exit(0);
        }
        return inputDate;
    }

}
