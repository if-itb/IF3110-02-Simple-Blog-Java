//delete_action.jsp

//Halaman JSP untuk meng-handle aksi delete


//Add a jsp:usebean tag.
//As before, enterempsbean as the ID, and hr.DataHandler as the Class.
//Set the Scope to session, and click OK.

//Add a Scriptlet to the page. Enter the following code into the Insert Scriptlet dialog box:

Integer post_id = new Integer(request.getParameter("pstid"));
PostBean.deletePostById(post_id.intValue()); 

//Drag Forward from the Component Palette to add a jsp:forward tag to the page.
//In the Insert Forward dialog box, enter employees.jsp.

