package com.marfeel.spring.data.controller;

import com.marfeel.spring.data.model.Website;
import com.marfeel.spring.data.service.WebsiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;


@RestController
@RequestMapping("/service/website")
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    private final LinkedBlockingQueue<DeferredResult<String>> suspendedRequests = new LinkedBlockingQueue<>();
    private final static Logger LOG = LoggerFactory.getLogger(WebsiteController.class);

    //TODO: do all of this asyncronously
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<List<Website>> receiveWebsites(@RequestBody List<Website> websites) {
        if(websites != null)
        {
            websites.stream().forEach(website -> website.setMarfeelizable(websiteService.inspect(website)));
        }
        return new ResponseEntity<List<Website>>(websites, HttpStatus.OK);
    }

    @RequestMapping(value = "/async", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<?>> handleReqDefResult(@RequestBody List<Website> websites) {
        LOG.info("Received async-deferredresult request");
        DeferredResult<ResponseEntity<?>> output = new DeferredResult<>();

        for(Website website:websites)
        {
            ForkJoinPool.commonPool().submit(() -> {
                LOG.info("Processing in separate thread");
                website.setMarfeelizable(websiteService.inspect(website));
                websiteService.add(website);
            });

        }
        output.setResult(ResponseEntity.ok("websites received!"));
        LOG.info("servlet thread freed");
        return output;
    }

}