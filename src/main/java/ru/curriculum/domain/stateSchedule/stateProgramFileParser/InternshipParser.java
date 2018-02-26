package ru.curriculum.domain.stateSchedule.stateProgramFileParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InternshipParser {
    private final String DATE_SEARCH_PATTERN = "(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])\\.(((19|20)\\d\\d)|(\\d\\d))";
    private final SimpleDateFormat[] formats = {
            new SimpleDateFormat("dd.MM.yy")
    };
    private Pattern datePattern;

    public InternshipParser() {
        this.datePattern = Pattern.compile(DATE_SEARCH_PATTERN);
    }

    public Date[] getStartAndFinishDates(String value) {
        Matcher matcher = datePattern.matcher(value);
        int i = 0;
        Date[] dates = new Date[2];
        while (matcher.find()) {
            if(i < 2) {
                dates[i++] = toDate(matcher.group());
            }
        }
        return dates;
    }

    public String getAddress(String value) {
        return value
                .replaceAll(DATE_SEARCH_PATTERN, "")
                .replaceFirst("[-]", "")
                .trim();
    }

    private Date toDate(String dateAsString) {
        for (SimpleDateFormat format : formats) {
            try {
                Date date = format.parse(dateAsString);
                return date;
            } catch (ParseException e) {
                System.out.println("Не верный формат даты стажировки: " + e.getMessage());
            }
        }
        return null;
    }
}
