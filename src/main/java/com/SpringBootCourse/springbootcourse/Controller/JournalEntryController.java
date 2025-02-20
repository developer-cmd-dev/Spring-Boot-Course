package com.SpringBootCourse.springbootcourse.Controller;

import com.SpringBootCourse.springbootcourse.Entity.JournalEntry;
import com.SpringBootCourse.springbootcourse.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    public JournalEntryService journalEntryService;

// Get all journal entries.
    @GetMapping("/get-journal")
    public ResponseEntity<List<JournalEntry>> getJournalData(){
        List<JournalEntry> journalEntries = journalEntryService.getJournal();
        if(journalEntries!=null){
            return new ResponseEntity<>(journalEntries,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    Add journal.
    @PostMapping("/add-journal")
    public ResponseEntity<?> createEntries(@RequestBody JournalEntry myEntry){
        if(myEntry!=null){
            myEntry.setDate(LocalDateTime.now());
            boolean isSaved =   journalEntryService.saveEntry(myEntry);
          if (isSaved){
              return new ResponseEntity<JournalEntry>(myEntry, HttpStatus.OK);
          }

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


//    Get a journal.
    @GetMapping("get-journal/{id}")
    public ResponseEntity<?> getUsingId(@PathVariable ObjectId id){
        Optional<JournalEntry> entry= journalEntryService.getById(id);
        if(entry.isPresent()){
            return new ResponseEntity<>(entry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-journal/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id){
        return journalEntryService.deleteById(id);
    }


    @PutMapping("/update-journal/{id}")
    public ResponseEntity<?> updateEntry(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry){
            Optional<JournalEntry> updatedEntry = journalEntryService.updateById(id,newEntry);
            if(updatedEntry.isPresent()){
                return new ResponseEntity<>(updatedEntry,HttpStatus.OK);
            }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }





}
