package cn.koha;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import cn.koha.model.FollowPost;
import cn.koha.utils.NetUtils;

public class TestServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<FollowPost> followposts = new ArrayList<FollowPost>();
		try {
			followposts = NetUtils.getAllFollowPost("http://club.autohome.com.cn/bbs/thread-c-982-51438105-1.html");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i=0; i< followposts.size(); i++){
			System.out.println("User:" + followposts.get(i).getUser().getUserName() +
					"UserName:" + followposts.get(i).getUser().getUserId() + 
					"Content:" + followposts.get(i).getContent()
					);
		}
	}

}
