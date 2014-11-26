package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class EditPostStatus {
	private int id;

	public void delete(int temp_id) {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE post SET id_deleted = 1 WHERE id = "
				+ temp_id;

		System.out.println(query);

		dbUtil.execute(query);
	}
	
	public void undelete(){
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE post SET id_deleted = 0 WHERE id = "
				+ this.getId();

		System.out.println(query);

		dbUtil.execute(query);	
	}

	public void publish() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE post SET is_published = 1 WHERE id = "
				+ this.getId();

		System.out.println(query);

		dbUtil.execute(query);
	}
	
	public void unpublish() {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE post SET is_published = 0 WHERE id = "
				+ this.getId();

		System.out.println(query);

		dbUtil.execute(query);
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}
	
}