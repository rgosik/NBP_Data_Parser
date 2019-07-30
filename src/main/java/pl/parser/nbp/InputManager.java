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

    public InputManager(String currencyCode, String startDate, String endDate) throws Exception{

        if (!currencyCode.matches("USD|EUR|CHF|GBP")) {
            log.error("Incorrect currency code");
            System.exit(0);
        }
        this.currencyCode = currencyCode;

        initDates(startDate, endDate);
    }

    // Edytowanie podanej daty do formatu który pozwoli, w dalszej części programu, łatwo odnajdować i pobierać odpowiedni pliki

    private void initDates(String dateS, String dateK) throws Exception {
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

    // Zabezpieczenie przed podaniem daty w niewsłaciwej postaci

    private String inputDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);

        try {
            format.parse(date);
        } catch (ParseException e) {
            log.error("Date: " + date + " is not compatible with required foramt: " +
                    (format).toPattern() + ", or given date is incorrect");
            System.exit(0);
        }
        return date;
    }

}
