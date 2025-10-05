package ua.privat.paymantbusinesslogicservice.consts;

import lombok.Getter;

/**
 * Статусы проводок
 */
@Getter
public enum WiringStatus {
    /**
     * Активная
     */
    ACTIVE(20001L),
    /**
     * Сторнированная
     */
    REVERSED(20002L);

    // Статус
    private final Long status;

    /**
     * Конструктор
     *
     * @param status статус
     */
    WiringStatus(Long status) {
        this.status = status;
    }
}
