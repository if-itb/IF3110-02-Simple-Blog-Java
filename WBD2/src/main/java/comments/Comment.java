package comments;

public class Comment {
	private int Id;
	private String Name;
	private String Email;
	private String Date;
	private String Content;
	private String postId;
	
	public Comment(){
		setId(0);
		setName(null);
		setEmail(null);
		setDate(null);
		setContent(null);
		setPostId(null);
	}
	public Comment(int I, String N, String E, String D, String C, String P){
		setId(I);
		setName(N);
		setEmail(E);
		setDate(D);
		setContent(C);
		setPostId(P);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
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

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	public void setComment(int I, String N, String E, String D, String C, String P){
		setId(I);
		setName(N);
		setEmail(E);
		setDate(D);
		setContent(C);
		setPostId(P);
	}
}
