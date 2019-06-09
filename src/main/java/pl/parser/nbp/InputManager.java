package pl.parser.nbp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputManager {

    static String currencyCode;
    static int editedStartDate;
    static int editedEndDate;
    static int startYear;
    static int endYear;

    // Zabezpieczenie przed podaniem nieprawidłowego kodu waluty

    static void initCode(String code) {
        String inputCode = code;

        if (!inputCode.matches("USD|EUR|CHF|GBP")) {
            System.out.println("Incorrect currency code");
            System.exit(0);
        }
        currencyCode = inputCode;
    }

    // Zabezpieczenie przed podaniem daty w niewsłaciwej postaci

    static String inputDate(String date) {
        String inputDate = date;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        try {
            format.parse(inputDate);
        } catch (ParseException e) {
            System.out.println("Date: " + inputDate + " is not compatible with required foramt: " +
                    ((SimpleDateFormat) format).toPattern() + ", or given date is incorrect");
            System.exit(0);
        }
        return inputDate;
    }

    // Edytowanie podanej daty do formatu który pozwoli, w dalszej części programu, łatwo odnajdować i pobierać odpowiedni pliki

    static void initDates(String dateS, String dateK) throws Exception {
        String startDate = inputDate(dateS);
        String endDate = inputDate(dateK);
        Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

        if (dateEnd.before(dateStart)) {
            System.out.println("Inputed end date is sooner than start date");
            System.exit(0);
        }

        editedStartDate = Integer.parseInt(startDate.replaceAll("[^a-zA-Z0-9]", "").substring(2));
        editedEndDate = Integer.parseInt(endDate.replaceAll("[^a-zA-Z0-9]", "").substring(2));
        startYear = Integer.parseInt(dateS.substring(0, 4));
        endYear = Integer.parseInt(dateK.substring(0, 4));
    }

}
