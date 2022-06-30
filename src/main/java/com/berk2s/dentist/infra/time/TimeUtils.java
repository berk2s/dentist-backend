package com.berk2s.dentist.infra.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public final class TimeUtils {

    private TimeUtils() {}

    /**
     * Adds minutes to current time
     */
    public static Date toDate(Optional<Duration> optTime) {
        if (optTime.isPresent()) {
            var time = optTime.get();
            var addedTime = LocalDateTime.now().plusMinutes(time.toMinutes());

            return Date.from(addedTime.atZone(ZoneId.systemDefault()).toInstant());
        }

        return new Date();
    }

    public static LocalDateTime toLocalDateTime(Optional<Duration> optTime) {
        if (optTime.isPresent()) {
            var time = optTime.get();

            return LocalDateTime.now().plusMinutes(time.toMinutes());
        }

        return LocalDateTime.now();
    }
}
