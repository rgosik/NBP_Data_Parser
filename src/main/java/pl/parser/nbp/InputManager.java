package pl.parser.nbp;

import lombok.Data;

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

        if(datesAreCorrect(startDate, endDate)) {
            this.editedStartDate = Integer.parseInt(formatDate(startDate).replaceAll("[^a-zA-Z0-9]", "").substring(2));
            this.editedEndDate = Integer.parseInt(formatDate(endDate).replaceAll("[^a-zA-Z0-9]", "").substring(2));
            this.startYear = Integer.parseInt(startDate.substring(0, 4));
            this.endYear = Integer.parseInt(endDate.substring(0, 4));
        } else {
            log.error("Input end date is sooner than start date");
            System.exit(0);
        }

    }

    // Zabezpieczenie przed podaniem daty w niewsłaciwej postaci

    private boolean datesAreCorrect(String startDate, String endDate) throws Exception {
        Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

        return dateStart.before(dateEnd);
    }

    // Formatowanie podanej daty do formatu który pozwoli, w dalszej części programu, łatwo odnajdywać i pobierać odpowiedni pliki

    private String formatDate(String date) {
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
