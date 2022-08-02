package models;

public class Restaurant {
private int Id;
private String restaurantName;
private String filename;
private String path;
private String ownerName;
private String city;
private String adress;
private String type;
private String description;
private float grade;
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public String getPath() {
	return path;
}
public void setPath(String path) {
	this.path = path;
}

public String getRestaurantName() {
	return restaurantName;
}
public void setRestaurantName(String restaurantName) {
	this.restaurantName = restaurantName;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getOwnerName() {
	return ownerName;
}
public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
}

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getAdress() {
	return adress;
}
public void setAdress(String adress) {
	this.adress = adress;
}
public float getGrade() {
	return grade;
}
public void setGrade(float grade) {
	this.grade = grade;
}


}
