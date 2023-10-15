package com.azure.service;

import com.azure.entity.*;
import com.azure.entity.qBank.Question;
import com.azure.exception.ResourceNotFoundException;
import com.azure.payload.*;
import com.azure.repository.QuestionSectionUserMappingRepository;
import com.azure.repository.QuestionnaireRepository;
import com.azure.repository.qBank.QuestionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionnaireService {
    private static final Logger logger = Logger.getLogger(QuestionnaireService.class);

    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionSectionUserMappingRepository questionSectionUserMappingRepository;

    @Transactional
    public void saveQuestionnaire(MultipartFile file) throws IOException {
        // Read JSON data from the uploaded file
        ObjectMapper objectMapper = new ObjectMapper();
        QuestionnaireDTO questionnaireDTO = objectMapper.readValue(file.getInputStream(), QuestionnaireDTO.class);

        Questionnaire questionnaire = convertToEntity(questionnaireDTO);
        logger.info("Entity conversion started");
        questionnaireRepository.save(questionnaire);
        logger.info("Saved questionnaire with ID: " + questionnaire.getId());
    }


    private Section convertToSection(SectionDTO sectionDTO) {
        logger.info("Section Entity conversion started");
        Section section = new Section();
        section.setType(sectionDTO.getType());
        section.setText(sectionDTO.getText());
        section.setName(sectionDTO.getName());

        // Convert SubSectionDTO to SubSection and set it in Section entity
        List<SubSection> subSections = sectionDTO.getSubSectionList() != null ?
                sectionDTO.getSubSectionList().stream()
                .map(this::convertToSubSection)
                .collect(Collectors.toList()) :
                Collections.emptyList();

        section.setSubSectionList(subSections);

        return section;
    }

    private SubSection convertToSubSection(SubSectionDTO subSectionDTO) {
        logger.info("Sub Section Entity conversion started");

        SubSection subSection = new SubSection();
        subSection.setSection(subSectionDTO.getSection());
        subSection.setType(subSectionDTO.getType());
        subSection.setText(subSectionDTO.getText());
        subSection.setName(subSectionDTO.getName());



        // Convert QuestionSectionUserMappingDTO to QuestionSectionUserMapping and set it in SubSection entity
        List<QuestionSectionUserMapping> mappings = subSectionDTO.getQuestionSectionUserMapping() != null ?
                convertToQuestionSectionUserMappings(subSectionDTO.getQuestionSectionUserMapping()) :
                Collections.emptyList();

        subSection.setQuestionSectionUserMapping(mappings);
        List<Conditions> mappings2 = subSectionDTO.getConditions()!= null ?
                subSectionDTO.getConditions()
                .stream()
                .map(this::convertToConditions)
                .collect(Collectors.toList()) :
                Collections.emptyList();

        subSection.setConditions(mappings2);

        return subSection;
    }

    private List<QuestionSectionUserMapping> convertToQuestionSectionUserMappings(List<QuestionSectionUserMappingDTO> mappingDTOs)  {
        logger.info("QuestionSectionUserMapping Entity conversion started");
        //mappingDTOs.forEach(mappingDTO -> logger.info("Question Code: " + mappingDTO.getQuestionCode()));

        return mappingDTOs.stream()
                .map(mappingDTO -> {
                    Question question = questionRepository.findByCode(mappingDTO.getQuestionCode());

                    if (question == null) {
                        logger.error("Question not found for code: " + mappingDTO.getQuestionCode());
                        throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "question code not found");
                    }

                    QuestionSectionUserMapping questionSectionUserMapping = new QuestionSectionUserMapping();
                    questionSectionUserMapping.setQuestion(question);
                    questionSectionUserMapping.setIndex(mappingDTO.getIndex());
                    return questionSectionUserMapping;
                })
                .collect(Collectors.toList());

    }
    private Conditions convertToConditions(ConditionsDTO conditionsDTO){
        logger.info("Conditions Entity conversion started");
        Conditions conditions = new Conditions();
        conditions.setConditions(conditionsDTO.getConditions());
        conditions.setConditionsType(conditionsDTO.getConditionsType());
        conditions.setConditionsAnswer(conditionsDTO.getConditionsAnswer());
        return conditions;
    }
    private Questionnaire convertToEntity(QuestionnaireDTO questionnaireDTO) {
        logger.info("Questionnaire Entity conversion started");
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName(questionnaireDTO.getName());
        questionnaire.setType(questionnaireDTO.getType());
        questionnaire.setText(questionnaireDTO.getText());
        // Convert SectionDTO to Section and set it in Questionnaire entity
        List<Section> sections = questionnaireDTO.getSectionQList().stream()
                .map(this::convertToSection)
                .collect(Collectors.toList());

        questionnaire.setSectionQList(sections);

        return questionnaire;
    }

//    public List<Questionnaire> getAll() {
//        return questionnaireRepository.findAll();
//    }
}
