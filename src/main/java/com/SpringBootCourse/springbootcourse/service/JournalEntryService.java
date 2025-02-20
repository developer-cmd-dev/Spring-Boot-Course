package com.SpringBootCourse.springbootcourse.service;

import com.SpringBootCourse.springbootcourse.Entity.JournalEntry;
import com.SpringBootCourse.springbootcourse.Entity.User;
import com.SpringBootCourse.springbootcourse.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;


    private String getAuthenticatedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public List<JournalEntry> getJournal() {
        String username = getAuthenticatedUsername();
        if (username != null) {
            User user = userService.getUserByUsername(username);
            return user.getJournalEntries();
        }
        return null;

    }

    @Transactional
    public boolean saveEntry(JournalEntry journalEntry) {
        String username = getAuthenticatedUsername();
        User userData = userService.getUserByUsername(username);
        if (userData != null) {
            JournalEntry savedEntry = journalEntryRepo.save(journalEntry);
            userData.getJournalEntries().add(savedEntry);
            userService.saveUser(userData);
            return true;
        }
        return false;


    }

    public Optional<JournalEntry> getById(ObjectId id) {
        String username = getAuthenticatedUsername();
        User user = userService.getUserByUsername(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).toList();
        if (!collect.isEmpty()) {
            Optional<JournalEntry> entry = journalEntryRepo.findById(id);
            if (entry != null) {
                return entry;
            }
        }
        return Optional.empty();
    }

    @Transactional
    public ResponseEntity<?> deleteById(ObjectId id) {
        try {
            String username = getAuthenticatedUsername();
            User userData = userService.getUserByUsername(username);
            userData.getJournalEntries().removeIf((x) -> x.getId().equals(id));
            userService.saveUser(userData);
            journalEntryRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    public Optional<JournalEntry> updateById(ObjectId id, JournalEntry newEntry) {
        Optional<JournalEntry> oldEntry = getById(id);

        if (oldEntry.isPresent()) {
            oldEntry.get().setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ?
                    newEntry.getTitle() :
                    oldEntry.get().getTitle());

            oldEntry.get().setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ?
                    newEntry.getContent() :
                    oldEntry.get().getContent());

            saveEntry(oldEntry.get());
        }
        return Optional.empty();

    }


}
