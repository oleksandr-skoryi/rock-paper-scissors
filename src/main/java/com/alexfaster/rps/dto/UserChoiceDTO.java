package com.alexfaster.rps.dto;

import com.alexfaster.rps.model.Choice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserChoiceDTO {

    @NotNull
    private Choice choice;

}
