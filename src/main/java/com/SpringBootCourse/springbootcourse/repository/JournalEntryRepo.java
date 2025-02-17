package com.SpringBootCourse.springbootcourse.repository;

import com.SpringBootCourse.springbootcourse.Entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournalEntry,String> {
}
