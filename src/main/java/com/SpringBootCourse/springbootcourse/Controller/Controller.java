package com.SpringBootCourse.springbootcourse.Controller;

import com.SpringBootCourse.springbootcourse.Entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class Controller {

    Map<Long, JournalEntry> journalEntryMap = new HashMap<>();

    @GetMapping
    public ArrayList<JournalEntry> getAll(){
        return new ArrayList<>(journalEntryMap.values());
    }

    @PostMapping
    public boolean createEntries(@RequestBody JournalEntry myEntry){
        journalEntryMap.put(myEntry.getId(),myEntry);
        return true;
    }

    @GetMapping("id/{id}")
    public JournalEntry getUsingId(@PathVariable Long id){
        return journalEntryMap.get(id);
    }

    @DeleteMapping("id/{id}")
    public JournalEntry deleteEntry(@PathVariable Long id){
        return journalEntryMap.remove(id);
    }
    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable Long id,@RequestBody JournalEntry myEntry){
        return journalEntryMap.put(id,myEntry);
    }





}
