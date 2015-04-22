package kr.co.jspstudy.VO;

import java.util.Date;

public class Article {
	private int article_id;
	private int group_id;
	private int parent;
	private int depth;
	private int indent;
	private Date posting_date;
	private int read_count;
	private String writer_name;
	private String title;
	private String content;
	
	
	public Article() { }
	public Article(int article_id, int group_id, int parent, int depth,
			int indent, Date posting_date, int read_count, String writer_name,
			String title, String content) {		
		this.article_id = article_id;
		this.group_id = group_id;
		this.parent = parent;
		this.depth = depth;
		this.indent = indent;
		this.posting_date = posting_date;
		this.read_count = read_count;
		this.writer_name = writer_name;
		this.title = title;
		this.content = content;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}	
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getIndent() {
		return indent;
	}
	public void setIndent(int indent) {
		this.indent = indent;
	}
	public Date getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(Date posting_date) {
		this.posting_date = posting_date;
	}
	public int getRead_count() {
		return read_count;
	}
	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}
	public String getWriter_name() {
		return writer_name;
	}
	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
