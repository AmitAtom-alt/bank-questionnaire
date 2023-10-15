package com.azure.repository.qBank;

import com.azure.entity.qBank.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, Long>
{

    @Query("SELECT q FROM Question q WHERE q.code = :code")
    Question findByCode(@Param("code") String code);
}