package com.charlie.internetcrawl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InternetCrawlApplication {

	public static void main(String[] args) {

		SpringApplication.run(InternetCrawlApplication.class, args);

		for(String fileName: args) {
			Crawler crawler = new Crawler(fileName);
			crawler.crawl();
		}
	}
}
