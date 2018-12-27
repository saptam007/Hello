package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.service.HelloService;
import com.example.demo.entity.Article;
import java.util.List;


@RestController
@RequestMapping("hello")
public class HelloController {

	@Autowired
	HelloService helloService;
	
	@RequestMapping("/test")
	public String Demo() {
		String s=null;
		s=helloService.getDemo();
		
		return s;
		//return "This is Demo data 1";
	}
	
/*	@GetMapping("articles")
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> list = helloService.getAllArticles();
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}
	*/  
	
	@RequestMapping(value = "/articleList", method = RequestMethod.GET)
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> list = helloService.getAllArticles();
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}
	
	//@PostMapping("article")
	@RequestMapping(value = "/article", method = RequestMethod.POST)
	public ResponseEntity<Void> addArticle(@RequestBody Article article, UriComponentsBuilder builder) {
		System.out.println("inside add article controller");
                boolean flag = helloService.addArticle(article);
                if (flag == false) {
        	   return new ResponseEntity<Void>(HttpStatus.CONFLICT);
                }
                HttpHeaders headers = new HttpHeaders();
                headers.setLocation(builder.path("/article/{id}").buildAndExpand(article.getArticleId()).toUri());
                return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
