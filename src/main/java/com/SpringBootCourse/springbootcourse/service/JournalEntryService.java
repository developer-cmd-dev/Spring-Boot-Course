package com.SpringBootCourse.springbootcourse.service;

import com.SpringBootCourse.springbootcourse.Entity.JournalEntry;
import com.SpringBootCourse.springbootcourse.Entity.User;
import com.SpringBootCourse.springbootcourse.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<JournalEntry> getAll(String username){
        User userdata = userService.getUserByUsername(username);
        if(userdata!=null){
            return userdata.getJournalEntries();
        }
        return null;
    }

    @Transactional
    public boolean saveEntry(JournalEntry journalEntry,String username){
        User userData = userService.getUserByUsername(username);
        if(userData!=null){
        JournalEntry savedEntry =journalEntryRepo.save(journalEntry);
        userData.getJournalEntries().add(savedEntry);
        userService.saveUser(userData);
        return true;
        }
        return false;


    }

    public Optional<JournalEntry> getById(ObjectId id){
      return   journalEntryRepo.findById(id);
    }

    public boolean deleteById(ObjectId id, String username){
        User userData = userService.getUserByUsername(username);

        userData.getJournalEntries().removeIf((x)->x.getId().equals(id));
        userService.saveUser(userData);
        journalEntryRepo.deleteById(id);
        return true;
    }



}
