package BDD.APITestFramework.utilities;

public enum APIResources {
	
	GET_PLACE_RESOURCE("/maps/api/place/get/json"),
	ADD_PLACE_RESOURCE("/maps/api/place/add/json"),
	UPDATE_PLACE_RESOURCE("/maps/api/place/update/json"),
	DELETE_PLACE_RESOURCE("/maps/api/place/delete/json"),
	ADD_BOOK_LIBRARY_RESOURCE("/Library/Addbook.php"),
	DELETE_BOOK_LIBRARY_RESOURCE("/Library/DeleteBook.php"),
	GET_BOOK_BY_AUTHOR_LIBRARY_RESOURCE("Library/GetBook.php?AuthorName=Rahul"),
	GET_BOOK_BY_ID_LIBRARY_RESOURCE( "/Library/GetBook.php?ID=bczd227");
	private String resource;
	
	APIResources(String resource){
		this.resource = resource;
	};
	
	public String getResource(){
		return resource;
	}
	
}
