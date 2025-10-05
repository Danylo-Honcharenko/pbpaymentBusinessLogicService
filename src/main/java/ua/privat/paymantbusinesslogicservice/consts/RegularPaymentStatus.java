package ua.privat.paymantbusinesslogicservice.consts;

import lombok.Getter;

/**
 * Статусы инструкций регулярных платежей
 */
@Getter
public enum RegularPaymentStatus {
    /**
     * Активная
     */
    ACTIVE(10001L),
    /**
     * Отключена
     */
    OFF(10002L),
    /**
     * Ошибка
     */
    ERROR(10003L);
    // Статус
    private final Long status;

    /**
     * Конструктор
     *
     * @param status статус
     */
    RegularPaymentStatus(Long status) {
        this.status = status;
    }
}
