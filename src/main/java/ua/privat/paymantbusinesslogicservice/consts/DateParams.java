package ua.privat.paymantbusinesslogicservice.consts;

import lombok.Getter;

import java.util.Calendar;

/**
 * Возможны параметры для форматирования даты
 */
@Getter
public enum DateParams {
    /**
     * Минуты
     */
    MIN("M", Calendar.MINUTE),
    /**
     * Часы
     */
    H("h", Calendar.HOUR),
    /**
     * Дни
     */
    D("d", Calendar.DATE),
    /**
     * Недели
     */
    W("w", Calendar.WEEK_OF_MONTH),
    /**
     * Месяца
     */
    M("m", Calendar.MONTH),
    /**
     * Года
     */
    Y("y", Calendar.YEAR);
    // Значение
    private final String value;
    // Тип редактируемого значения в дате
    private final int field;

    /**
     * Конструктор
     *
     * @param value значение
     * @param field тип редактируемого значения в дате
     */
    DateParams(String value, int field) {
        this.value = value;
        this.field = field;
    }
}
