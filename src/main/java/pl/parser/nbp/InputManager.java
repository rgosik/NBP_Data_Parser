package pl.parser.nbp;

public interface InputManager {
    int getStartYear();
    int getEndYear();
    String getCurrencyCode();
    int getEditedStartDate();
    int getEditedEndDate();
    boolean datesAreCorrect(String startDate, String endDate) throws Exception;
    String formatDate(String date);
}
