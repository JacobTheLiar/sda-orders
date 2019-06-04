package orders.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TDateTimeToString {

    //spospby formatowania
    // https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html

    /**
     * domyslny format daty i czasu
     */
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * Zamiana daty i czasu na tekst
     * @param aDatetime czas jaki ma byc przekonwertowany
     * @param aFormat format jaki ma byc zastosowany do prezentacji czasu
     * @return dataczas jako string
     */
    public static String DateTimeToString(LocalDateTime aDatetime, String aFormat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(aFormat);
        return aDatetime.format(formatter);
    }

    /**
     * zamana daty i czasu na tekst wg domyslnego formatu
     * @param aDatetime czas jaki ma byc przekonwertowany
     * @return dataczas jako string wg domyslnego wzorca
     */
    public static String DateTimeToString(LocalDateTime aDatetime){
        return DateTimeToString(aDatetime, dateTimeFormat);
    }

    /**
     * konwersja tekstu na dateczas
     * @param aStringDateTime czas zapisany jako string
     * @param aFormat format daty który jest przekazywany
     * @return dataczas
     */
    public static LocalDateTime StringToDateTime(String aStringDateTime, String aFormat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(aFormat);
        return  LocalDateTime.parse(aStringDateTime, formatter);
    }

    /**
     * konwersja tekstu na dateczas przy uzyciu domyslnego formatu
     * @param aStringdateTime
     * @return dataczas
     */
    public static LocalDateTime StringToDateTime(String aStringdateTime){
        return  StringToDateTime(aStringdateTime, dateTimeFormat);
    }

    /**
     * zwraca obecną godzinę z datę jako string przy uzyciu domyslnego formatu
     * @return dataczas string
     */
    public static String NowString(){
        return DateTimeToString(LocalDateTime.now());
    }

}
