package com.SpringBootCourse.springbootcourse.Controller;

import com.SpringBootCourse.springbootcourse.Entity.JournalEntry;
import com.SpringBootCourse.springbootcourse.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping
    public List<JournalEntry> getAll(){
    return journalEntryService.getAll();
    }

    @PostMapping
    public boolean createEntries(@RequestBody JournalEntry myEntry){
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{id}")
    public JournalEntry getUsingId(@PathVariable ObjectId id){
       return journalEntryService.getById(id).orElse(null);
    }

    @DeleteMapping("id/{id}")
    public boolean deleteEntry(@PathVariable ObjectId id){
        return journalEntryService.deleteById(id);
    }
    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry){
       JournalEntry oldEntry = journalEntryService.getById(id).orElse(null);
       if (oldEntry !=null){
        oldEntry.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().isEmpty() ?newEntry.getTitle():
                oldEntry.getTitle());
           oldEntry.setContent(newEntry.getContent()!=null && !newEntry.getContent().isEmpty() ?newEntry.getContent():
                oldEntry.getContent());
       }
       journalEntryService.saveEntry(oldEntry);
       return oldEntry;

    }





}
