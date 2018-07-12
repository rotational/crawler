package com.marfeel.spring.data.service;

import com.marfeel.webcrawler.Spider;
import com.marfeel.spring.data.model.Website;
import com.marfeel.spring.data.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
public class WebsiteService {

    @Autowired
    private WebsiteRepository websiteRepository;

    @Transactional
    public Website add(Website website) {
       return websiteRepository.save(website);
    }

    @Transactional(readOnly=true)
    public List<Website> findAll() {
        return websiteRepository.findAll();
    }

    @Transactional
    public void deleteAllInBatch() {
        websiteRepository.deleteAllInBatch();
    }

    public Boolean inspect(Website website)
    {
        Spider spider = new Spider();
        try {
            return spider.checkIfMarfeelizable(spider.slingWebsite(website.getURL()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

}
