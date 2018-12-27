package com.example.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.demo.entity.Article;
import com.example.demo.entity.HelloRowMapper;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class HelloDataImpl implements HelloData{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	public List<Article> getAllArticles() {
		String sql = "SELECT article_id, title, category FROM articles";
		RowMapper<Article> rowMapper = new HelloRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public void addArticle(Article article) {
		System.out.println("inside dao--add article");
		//Add article
		String sql = "INSERT INTO articles (article_id, title, category) values (?, ?, ?)";
		jdbcTemplate.update(sql, article.getArticleId(), article.getTitle(), article.getCategory());
		
		//Fetch article id
		sql = "SELECT article_id FROM articles WHERE title = ? and category=?";
		int articleId = jdbcTemplate.queryForObject(sql, Integer.class, article.getTitle(), article.getCategory());
		
		//Set article id 
		article.setArticleId(articleId);
	}
	
	@Override
	public boolean articleExists(String title, String category) {
		String sql = "SELECT count(*) FROM articles WHERE title = ? and category=?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, title, category);
		if(count == 0) {
    		        return false;
		} else {
			return true;
		}
	}
}
