package com.alexfaster.rps.dto;

import com.alexfaster.rps.model.Choice;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserChoiceDTO {

    @NotNull
    private Choice choice;

}
