package com.charlie.internetcrawl;

import com.charlie.internetcrawl.model.Internet;
import com.charlie.internetcrawl.model.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Crawler {

    private String filePath;
    private Internet internet;

    private HashMap<String, Page> validPages = new HashMap<>();

    private HashSet<String> visitedPages = new HashSet<>();
    private HashSet<String> skippedPages = new HashSet<>();
    private HashSet<String> invalidLinks = new HashSet<>();

    public Crawler(String filePath) {
        this.filePath = filePath;
        System.out.println("\n**************************************************************");
        System.out.println("***** Crawling Internet from file " + this.filePath  + "******");
        System.out.println("*************************************************************\n");
        getInternetFromFile();
    }

    private void getInternetFromFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.internet = mapper.readValue(new File(this.filePath), Internet.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void crawl() {
        addMainPages();

        Page root = this.internet.getPages().stream().findFirst().get();

        String link = root.getAddress();

        markPageVisited(link,root);

        visitPage(root);

        outputResults();

    }

    private void addMainPages() {
        for (Page page : this.internet.getPages()) {
            validPages.put(page.getAddress(), page);
        }
    }

    private void visitPage(Page page) {
        for (String link : page.getLinks()) {
            if (!validPages.containsKey(link)) {
                invalidLinks.add(link);
                continue;
            }
            Page nextPage = validPages.get(link);
            if (!nextPage.getVisited()) {
                markPageVisited(link,nextPage);
                visitPage(validPages.get(link));
            } else {
                skippedPages.add(link);
            }
        }
        return;
    }

    private void markPageVisited(String link,Page page) {
        page.setVisited(true);
        validPages.put(link, page);
        visitedPages.add(link);
    }

    private void outputResults () {
        System.out.println("********* Success *************");
        System.out.println(visitedPages);

        System.out.println("\n");
        System.out.println("********* Skipped **********");
        System.out.println(skippedPages);

        System.out.println("\n");
        System.out.println("********* Error **********");
        System.out.println(invalidLinks);
    }
}
