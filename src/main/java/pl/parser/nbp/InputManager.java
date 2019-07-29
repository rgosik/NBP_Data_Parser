package pl.parser.nbp;

import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


@Data
public class InputManager {

    private static final Logger log = LogManager.getRootLogger();
    private String currencyCode;
    private int editedStartDate;
    private int editedEndDate;
    private int startYear;
    private int endYear;

    // Zabezpieczenie przed podaniem nieprawidłowego kodu waluty

    public void initCode(String code) {
        String inputCode = code;

        if (!inputCode.matches("USD|EUR|CHF|GBP")) {
            log.error("Incorrect currency code");
            System.exit(0);
        }
        currencyCode = inputCode;
    }

    // Zabezpieczenie przed podaniem daty w niewsłaciwej postaci

    public String inputDate(String date) {
        String inputDate = date;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        try {
            format.parse(inputDate);
        } catch (ParseException e) {
            log.error("Date: " + inputDate + " is not compatible with required foramt: " +
                    ((SimpleDateFormat) format).toPattern() + ", or given date is incorrect");
            System.exit(0);
        }
        return inputDate;
    }

    // Edytowanie podanej daty do formatu który pozwoli, w dalszej części programu, łatwo odnajdować i pobierać odpowiedni pliki

    public void initDates(String dateS, String dateK) throws Exception {
        String startDate = inputDate(dateS);
        String endDate = inputDate(dateK);
        Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

        if (dateEnd.before(dateStart)) {
            log.info("Inputed end date is sooner than start date");
            System.exit(0);
        }

        editedStartDate = Integer.parseInt(startDate.replaceAll("[^a-zA-Z0-9]", "").substring(2));
        editedEndDate = Integer.parseInt(endDate.replaceAll("[^a-zA-Z0-9]", "").substring(2));
        startYear = Integer.parseInt(dateS.substring(0, 4));
        endYear = Integer.parseInt(dateK.substring(0, 4));
    }

}
