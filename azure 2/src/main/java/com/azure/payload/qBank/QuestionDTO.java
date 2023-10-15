package com.azure.payload.qBank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionDTO {
    private String code;
    private String name;
    private String lens;
    private String category;
    private String subCategory;
    private String question;
    private String statement;

    private String context;
    private RecommendationDTO recommendation;
    private String questionDescription;
    private List<QuestionConditionDTO> questionConditions;
    private String color;
    private String textFont;
    private String type;
    private boolean disabled;
    private List<ChoiceDTO> choices;
    private List<QuestionValidationDTO> questionValidation;
    private List<SubQuestionDTO> subQuestionsList;

}