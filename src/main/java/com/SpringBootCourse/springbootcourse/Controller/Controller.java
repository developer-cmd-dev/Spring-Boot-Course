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





}
