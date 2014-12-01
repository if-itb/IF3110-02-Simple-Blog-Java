package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class EditPostStatus {
	private int id;

	public void delete(int temp_id) {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE post SET is_deleted = 1 WHERE id = "
				+ temp_id;

		System.out.println(query);

		dbUtil.execute(query);
	}
	
	public void undelete(int temp_id){
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE post SET is_deleted = 0 WHERE id = "
				+ temp_id;

		System.out.println(query);

		dbUtil.execute(query);	
	}

	public void publish(int temp_id) {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE post SET is_published = 1 WHERE id = "
				+ temp_id;

		System.out.println(query);

		dbUtil.execute(query);
	}
	
	public void unpublish(int temp_id) {
		DatabaseUtility dbUtil = DatabaseUtility.getInstance();

		String query = "UPDATE post SET is_published = 0 WHERE id = "
				+ temp_id;

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