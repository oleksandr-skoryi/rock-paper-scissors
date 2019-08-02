package com.alexfaster.rps.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Min;

@Configuration
@ConfigurationProperties(prefix = "skynet")
@Getter
@Setter
public class SkynetConfiguration {

    @Min(1)
    private Integer chainLength;

    @Min(1)
    private Integer patternLength;

    @Min(1)
    private Integer minChunkSize;

    @Min(1)
    private Integer maxChunkSize;

}
