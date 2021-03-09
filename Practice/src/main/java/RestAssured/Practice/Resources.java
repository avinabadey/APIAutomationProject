package RestAssured.Practice;

public class Resources {

	public static String getGetResource() {
		return "/maps/api/place/get/json";
	}
	
	public static String getPostResource() {
		return "/maps/api/place/add/json";
	}
	
	public static String getPutResource() {
		return "/maps/api/place/update/json";
	}
	
	public static String getDeleteResource() {
		return "/maps/api/place/delete/json";
	}
	
	public static String getAddBookResource(){
		return "/Library/Addbook.php";
	}
	
	public static String getDeleteBookResource(){
		return "/Library/DeleteBook.php";
	}
	
	
	public static String getBookByAuthorResource(){
		return "Library/GetBook.php?AuthorName=Rahul";
	}
	
	public static String getBookByIDResource(){
		return "/Library/GetBook.php?ID=bczd227";
	}
	
}
