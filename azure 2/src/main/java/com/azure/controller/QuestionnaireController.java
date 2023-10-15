package com.azure.controller;

import com.azure.entity.Questionnaire;
import com.azure.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/questionnaire")
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;

    @PostMapping("/import")
    public ResponseEntity<String> importQuestionnaire(@RequestParam("file") MultipartFile file) {
        try {
            questionnaireService.saveQuestionnaire(file);
            return ResponseEntity.ok("Questionnaire data imported successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while importing questionnaire data.");
        }
    }
//    @GetMapping("/getAll")
//    public ResponseEntity<List<Questionnaire>> getAllQuestionnaire(){
//        List<Questionnaire> all = questionnaireService.getAll();
//        return ResponseEntity.ok(all);
//    }

}
