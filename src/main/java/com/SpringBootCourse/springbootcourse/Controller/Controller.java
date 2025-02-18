package com.SpringBootCourse.springbootcourse.Controller;

import com.SpringBootCourse.springbootcourse.Entity.JournalEntry;
import com.SpringBootCourse.springbootcourse.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class Controller {

    @Autowired
    public JournalEntryService journalEntryService;


    @GetMapping("/{username}")
    public ResponseEntity<List<JournalEntry>> getAll(@PathVariable String username){
        List<JournalEntry> journalEntries = journalEntryService.getAll(username);
        if(journalEntries!=null){
            return new ResponseEntity<>(journalEntries,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> createEntries(@PathVariable String username, @RequestBody JournalEntry myEntry){

        if(myEntry!=null){
            myEntry.setDate(LocalDateTime.now());
            boolean isSaved =   journalEntryService.saveEntry(myEntry,username);
          if (isSaved){
              return new ResponseEntity<JournalEntry>(myEntry, HttpStatus.OK);
          }

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getUsingId(@PathVariable ObjectId id){
        JournalEntry data = journalEntryService.getById(id).orElse(null);
        if(data!=null){
            return new ResponseEntity<JournalEntry>(data,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{username}/{id}")
    public boolean deleteEntry(@PathVariable ObjectId id,@PathVariable String username){
        return journalEntryService.deleteById(id,username);
    }


    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry){
       JournalEntry oldEntry = journalEntryService.getById(id).orElse(null);
//       if (oldEntry !=null){
//        oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty() ?newEntry.getTitle():
//                oldEntry.getTitle());
//           oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty() ?newEntry.getContent():
//                oldEntry.getContent());
//       }
//       journalEntryService.saveEntry(oldEntry);
       return oldEntry;

    }





}
