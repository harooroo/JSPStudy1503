package kr.co.jspstudy.VO;

public class PdsItem {
	private int id;
	private int article_id;
	private String filename;
	private String realpath;
	private long filesize;
	private int downloadCount;
	private String description;
	
	public PdsItem() {		
	}
	public PdsItem(int id, int article_id, String filename, String realpath,
			long filesize, int downloadCount, String description) {
		super();
		this.id = id;
		this.article_id = article_id;
		this.filename = filename;
		this.realpath = realpath;
		this.filesize = filesize;
		this.downloadCount = downloadCount;
		this.description = description;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;	}
	
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
	public String getRealpath() {
		return realpath;
	}
	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
