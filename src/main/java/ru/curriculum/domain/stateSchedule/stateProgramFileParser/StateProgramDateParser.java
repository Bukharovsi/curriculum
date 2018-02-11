package ru.curriculum.domain.stateSchedule.stateProgramFileParser;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class StateProgramDateParser {

    private final Map<String, Integer> monthMap =
            new HashMap<String, Integer>() {
                { put("январь", 1); }
                { put("февраль", 2); }
                { put("март", 3); }
                { put("апрель", 4); }
                { put("май", 5); }
                { put("июнь", 6); }
                { put("июль", 7); }
                { put("август", 8); }
                { put("сентябрь", 9); }
                { put("октябрь", 10); }
                { put("ноябрь", 11); }
                { put("декабрь", 12); }
            };

    public Integer getStateProgramYear(XWPFDocument doc) {
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        Pattern yearPattern = Pattern.compile("[0-9]+");
        for(XWPFParagraph paragraph : doc.getParagraphs()) {
            if(paragraph.getText().toLowerCase().startsWith("казань")) {
                Matcher matcher = yearPattern.matcher(paragraph.getText());
                if(matcher.find()) {
                    year = Integer.valueOf(matcher.group(0));
                }
            }
        }

        return year;
    }

    public Date makeStartDate(Integer year, Integer month) {
        LocalDate date = LocalDate.of(year, month, 1);

        return Date.from(date.with(firstDayOfMonth()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date makeEndDate(Integer year, Integer month) {
        LocalDate date = LocalDate.of(year, month, 1);

        return Date.from(date.with(lastDayOfMonth()).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public Integer getMonth(String month) {
        return monthMap.containsKey(month.toLowerCase()) ? monthMap.get(month.toLowerCase()) : 1;
    }
}

