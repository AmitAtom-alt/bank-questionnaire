package com.azure.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireDTO {
    private String type;
    private String text;
    private String name;
    private List<SectionDTO> sectionQList;
}