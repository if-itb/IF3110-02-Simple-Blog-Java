package posts;

public class Post {
	private int Id;
	private String Title;
	private String Date;
	private String Content;
	private String Status;
	
	public Post(){
		setId(0);
		setTitle("");
		setDate("");
		setContent("");
		setStatus("");
	}
	public Post(int id, String T, String D, String C, String S){
		this.setId(id);
		setTitle(T);
		setDate(D);
		setContent(C);
		setStatus(S);
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public void setPost(int id, String T, String D, String C, String S){
		setId(id);
		setTitle(T);
		setDate(D);
		setContent(C);
		setStatus(S);
	}

}
