package com.marfeel.webcrawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import org.springframework.util.ResourceUtils;

public class Spider {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";

    //TODO: check all possible errors when making the connection: errors in urls, 404, set timeouts, etc.
    public Document slingWebsite(String url) throws IOException {

        Connection conn = Jsoup.connect("http://" + url).userAgent(USER_AGENT);
        Document htmlDocument = conn.get();
        return htmlDocument;
    }


    //TODO: see how we can extend this part of the code
    public boolean checkIfMarfeelizable(Document document) {
        return (document.title().toLowerCase().contains("news") || document.title().toLowerCase().contains("noticias"));
    }
}
