package com.azure.repository.qBank;

import com.azure.entity.qBank.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}

