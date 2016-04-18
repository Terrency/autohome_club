package cn.koha.model;

/**
 * ¸úÌû
 */
public class FollowPost {

	private MainPost mainPost;
	private UserModel user;
	private String content;
	private int floorNumber;
	private String postTime;
	
	public MainPost getMainPost() {
		return mainPost;
	}
	public void setMainPost(MainPost mainPost) {
		this.mainPost = mainPost;
	}
	public UserModel getUser() {
		return user;
	}
	public void setUser(UserModel user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	
}
