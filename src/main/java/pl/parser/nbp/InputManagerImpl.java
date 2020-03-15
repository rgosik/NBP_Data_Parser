package pl.parser.nbp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputManagerImpl implements InputManager{

    private static final Logger log = LogManager.getRootLogger();
    private String currencyCode;
    private String startDate;
    private String endDate;

    public InputManagerImpl(String currencyCode, String startDate, String endDate) throws Exception{
        this.currencyCode = currencyCode;

        if (!currencyCode.matches("USD|EUR|CHF|GBP")) {
            log.error("Incorrect currency code");
            System.exit(1);
        }

        if(datesAreCorrect(startDate, endDate)) {
            this.startDate = startDate;
            this.endDate = endDate;
        } else {
            log.error("Input end date is sooner than input start date");
            System.exit(1);
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

    @Override
    public int getEditStartDate() {
        return Integer.parseInt(formatDate(startDate).replaceAll("[^a-zA-Z0-9]", "").substring(2));
    }

    @Override
    public int getEditEndDate() {
        return Integer.parseInt(formatDate(endDate).replaceAll("[^a-zA-Z0-9]", "").substring(2));
    }

    @Override
    public int getStartYear() {
        return Integer.parseInt(startDate.substring(0, 4));
    }

    @Override
    public int getEndYear() {
        return Integer.parseInt(endDate.substring(0, 4));
    }

    @Override
    public String getCurrencyCode() {
        return currencyCode;
    }

}
