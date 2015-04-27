package kr.co.jspstudy.VO;

public class AddRequest {
	private int article_id;
	private String filename;
	private long filesize;
	private String realpath;
	private String description;
	
	
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getRealpath() {
		return realpath;
	}
	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public PdsItem toPdsItem(){
		PdsItem item = new PdsItem();		
		item.setFilename(filename);
		item.setFilesize(filesize);
		item.setRealpath(realpath);
		item.setDescription(description);
		return item;
	}
}
