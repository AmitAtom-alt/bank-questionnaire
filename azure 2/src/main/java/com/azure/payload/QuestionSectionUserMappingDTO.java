package com.azure.payload;
import com.azure.entity.qBank.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSectionUserMappingDTO {
    private String questionCode;
    private int index;
}