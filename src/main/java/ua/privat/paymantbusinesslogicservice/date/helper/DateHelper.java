package ua.privat.paymantbusinesslogicservice.date.helper;

import org.apache.commons.lang3.StringUtils;
import ua.privat.paymantbusinesslogicservice.consts.DateParams;
import ua.privat.paymantbusinesslogicservice.exceptions.DateHelperException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Класс для формирования даты след. списания
 */
public class DateHelper {

    /**
     * Получить дату по выражению
     *
     * @param periodExpression выражение
     * @return Date дата
     */
    public static Date getDateByExpression(String periodExpression) {
        String amount = StringUtils.left(periodExpression, 1);
        // конечная буква, определяющая тип редактируемого значения в дате
        String type = StringUtils.right(periodExpression, 1);
        try {
            return Arrays.stream(DateParams.values())
                    .filter(dateParams -> dateParams.getValue().equals(type))
                    .map(dateParams -> gateDate(dateParams.getField(), amount))
                    .findFirst()
                    .orElseThrow(() -> new DateHelperException("No valid date type found! Available types: M - minute, h - hour, d - day, w - week, m - month, y - year"));
        } catch (NumberFormatException e) {
            throw new DateHelperException("No valid date amount found!");
        }
    }

    /**
     * Получить готовую дату
     *
     * @param field тип редактируемого значения в дате
     * @param amount значение
     * @return Date дата
     */
    private static Date gateDate(int field, String amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, Integer.parseInt(amount));
        return calendar.getTime();
    }
}
