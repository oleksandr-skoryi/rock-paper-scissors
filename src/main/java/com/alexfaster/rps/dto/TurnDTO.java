package com.alexfaster.rps.dto;

import com.alexfaster.rps.model.Outcome;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnDTO {

    private Outcome outcome;

    private ProfileDTO profile;
}
