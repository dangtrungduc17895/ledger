package com.pet.ledger.constant;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeConstant {
    public static final long TIME_CHECK_EXPIRED = 300000;
    public static final long EXPIRED_SESSION = System.currentTimeMillis()+ 1800000;
    public static final long CURRENT_TIME_LONG_TYPE = Instant.now().toEpochMilli();
    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    public static final String CURRENT_TIME_STRING_TYPE = LocalDate.now().toString();
    private DateTimeConstant() {}
}
