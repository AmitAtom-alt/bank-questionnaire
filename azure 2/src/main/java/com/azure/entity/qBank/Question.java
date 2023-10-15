package com.azure.entity.qBank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String name;
    private String lens;
    private String category;
    private String subCategory;
    private String question;
    private String statement;
    @Lob
    private String context;
    @OneToOne(cascade = CascadeType.ALL)
    private Recommendation recommendation;
    private String questionDescription;
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<QuestionCondition> questionConditions;


    private String color;
    private String textFont;
    private String type;
    private boolean disabled = false;



    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<Choice> choices;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_code")
    private List<QuestionValidation> questionValidation;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<SubQuestion> subQuestionsList;
}