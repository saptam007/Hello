package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.HelloData;
import com.example.demo.entity.Article;

@Service
public class HelloServiceImpl implements HelloService{
	@Autowired
	private HelloData helloData;
	
	public String getDemo() {
		return "test data from service";
	} 
	
	@Override
	public List<Article> getAllArticles(){
		return helloData.getAllArticles();
	}
	
	public synchronized boolean addArticle(Article article){
        if (helloData.articleExists(article.getTitle(), article.getCategory())) {
          return false;
        } else {
        	helloData.addArticle(article);
          return true;
        }
}
	
}
