package pl.parser.nbp;

public interface InputManager {
    int getStartYear();
    int getEndYear();
    String getCurrencyCode();

    boolean datesAreCorrect(String startDate, String endDate) throws Exception;
    String formatDate(String date);
    int getEditStartDate();
    int getEditEndDate();
}
