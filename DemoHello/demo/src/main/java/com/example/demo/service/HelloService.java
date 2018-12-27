package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Article;

public interface HelloService {

	public String getDemo();
	public List<Article> getAllArticles();
	
	public boolean addArticle(Article article);
}
