package com.SpringBootCourse.springbootcourse.Controller;

import com.SpringBootCourse.springbootcourse.Entity.JournalEntry;
import com.SpringBootCourse.springbootcourse.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class Controller {

    @Autowired
    public JournalEntryService journalEntryService;


    @GetMapping
    public ArrayList<JournalEntry> getAll(){

        return null;
    }

    @PostMapping
    public boolean createEntries(@RequestBody JournalEntry myEntry){
        journalEntryService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("id/{id}")
    public JournalEntry getUsingId(@PathVariable Long id){
        return null;
    }

    @DeleteMapping("id/{id}")
    public JournalEntry deleteEntry(@PathVariable Long id){
        return null;
    }
    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable Long id,@RequestBody JournalEntry myEntry){
        return null;
    }





}
