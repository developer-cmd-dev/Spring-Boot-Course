package com.SpringBootCourse.springbootcourse.cache;

import com.SpringBootCourse.springbootcourse.Entity.ConfigJournalAppEntity;
import com.SpringBootCourse.springbootcourse.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AppCache {

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;
    public Map<String,String> appCache = new HashMap<>();

    @PostConstruct
    public void init(){
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        log.info(all.toString());

        for (ConfigJournalAppEntity configJournalAppEntity:all){
            appCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }

    }


}
