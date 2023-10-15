package com.azure.payload;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubSectionDTO {
    private Long id;
    private String section;
    private String type;
    private String text;
    private String name;
    private List<ConditionsDTO> conditions;
    private List<QuestionSectionUserMappingDTO> questionSectionUserMapping;
}