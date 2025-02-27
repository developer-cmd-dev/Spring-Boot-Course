package com.SpringBootCourse.springbootcourse.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("config_journal_app")
@Data
public class ConfigJournalAppEntity {
    private String key;
    private String value;
}
