package com.example.demo.dao;
import com.example.demo.entity.Article;
import java.util.List;

public interface HelloData {

	public List<Article> getAllArticles();
	public void addArticle(Article article);
	boolean articleExists(String title, String category);
	
}
