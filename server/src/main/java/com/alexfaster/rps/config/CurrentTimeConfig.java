package com.alexfaster.rps.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;

@Component
@RequestScope
public class CurrentTimeConfig {

    private LocalDateTime localDateTime;

    public CurrentTimeConfig() {
        this.localDateTime = LocalDateTime.now();
    }

    public LocalDateTime getTime() {
        return localDateTime;
    }

}
