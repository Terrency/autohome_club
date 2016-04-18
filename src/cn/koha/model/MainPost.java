package cn.koha.model;

/**
 * Ö÷Ìû
 */
public class MainPost {

	private String postTitle;
	private String postTime;
	private String postContent;
	private UserModel postUser;
	private String postType;
	private int clickedTimes;
	private int replyTimes;
	private String postId;
	
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public UserModel getPostUser() {
		return postUser;
	}
	public void setPostUser(UserModel postUser) {
		this.postUser = postUser;
	}
	public String getPostType() {
		return postType;
	}
	public void setPostType(String postType) {
		this.postType = postType;
	}
	public int getClickedTimes() {
		return clickedTimes;
	}
	public void setClickedTimes(int clickedTimes) {
		this.clickedTimes = clickedTimes;
	}
	public int getReplyTimes() {
		return replyTimes;
	}
	public void setReplyTimes(int replyTimes) {
		this.replyTimes = replyTimes;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	
	
}
