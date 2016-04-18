package cn.koha.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.koha.model.FollowPost;
import cn.koha.model.MainPost;
import cn.koha.model.UserModel;

/**
 * 网络工具包
 */
public class NetUtils {
    private static String cookie = "sessionfid=527298747; qasteam18531034=0; sessionip=61.50.123.14; area=110199; sessionid=FCF94165-4952-8493-FA03-B7842F8061CC%7C%7C2016-04-13+09%3A18%3A30.614%7C%7Cwww.baidu.com; sessionuid=FCF94165-4952-8493-FA03-B7842F8061CC||2016-04-13+09%3A18%3A30.614||www.baidu.com; pcpopclub=0D87578D458AF3395C02052D1C4CC0A651EE6A2D56ECCE56C6D838E5530D9A0B7CC40A1F9434E365F1B9B03C645282D326FFF03CC12C61D759C1DC96CC74B2931C17C7FB2FF07C3C38D8C30806A9D84575B87F74C1BC87DD35DB4D5BBEBF839800E560472842848775419DB70731C9EBDC55B749388BB39021175D72CAFC8FB62A4ACC325B22CB455902032C9C972E39FB426D9ECD5F3E13FE40993F9E135C066A952A97739DD72ADB9AD42033877A15AB789ADCA258DC130DB9CEF708C09D043F8CEB50864D4F38FF4761826AA52700BCE6B090BE450401A4A3C5A66E19A1AA9E90118B0D9C59AE5002C638952E4363286DDB50E483E7D003BDF1986A0AA90AE12109F82C92452FDDB053F21EB3E999213B0E4F0BD3783EC669C144996372011D8243D1393A92580010B967A1BAD3DB21B460FC; clubUserShow=18531034|3615|2|%e5%88%b0%e5%ba%95%e5%a4%9a%e5%b0%91%e7%94%a8%e6%88%b7%e5%90%8d%e8%a2%ab%e5%8f%96%e4%ba%86|0|0|1|userheaders/g10/M04/4E/D9/120X120_0_q87_autohomecar__wKjBzVcIoV-ARmHuAAGJ4rBCeO8001.jpg|2016-04-13 12:32:26|0; autouserid=18531034; sessionfid=527298747; CardPostGuide=1; sessionuserid=18531034; sessionlogin=3bc1c70c20674739a3c89415bcbc0b0a011ac2da; __utma=1.140015656.1460510321.1460531126.1460608616.4; __utmb=1.0.10.1460608616; __utmc=1; __utmz=1.1460608616.4.3.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; sessionvid=A06F7138-DDC7-E86C-CF5F-F64BCFEDA708; ref=www.baidu.com%7C%7C0%7C8-1%7C2016-04-14+12%3A36%3A59.163%7C2016-04-13+09%3A18%3A30.614";
    private static String useragent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36";
    private static String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
    private static String connection = "keep-alive";
    private static String acceptencoding = "gzip";
    private static String acceptlanguage = "zh-CN,zh;q=0.8";
    
    private static String sendGet(String urlstr){
    	StringBuffer sb = new StringBuffer();
    	try {
	        // 获取 cookie_sid 和 login_formhash --------------------  
			URL url = new URL(urlstr);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();  
	        con.setRequestProperty("Cookie", cookie);
	        con.setConnectTimeout(10000);  
            con.setDoOutput(true);   
            con.setRequestMethod("GET");  
            con.setUseCaches(false);  
            con.setRequestProperty("User-Agent", useragent);
            con.setRequestProperty("Accept", accept);
            con.setRequestProperty("Accept-Language", acceptlanguage);
            con.setRequestProperty("Connection", connection);
	        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {  
				InputStream is = con.getInputStream();
				GZIPInputStream gis = new GZIPInputStream(is);  
				InputStream in = new BufferedInputStream(gis);  
	            Reader rd = new InputStreamReader(gis, "gb2312");  
	            int c = 0;  
	            while ((c = rd.read()) != -1) {  
	                sb.append((char) c);  
	            }  
	            System.out.println(sb.toString());  
	            in.close();  
	        }  
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	return sb.toString();
    }
    public static MainPost getMainPost(Document document){
    	int clickedTimes = Integer.parseInt(document.select("div#maxwrap-maintopic div.consnav span #x-views").text());
    	int replyTimes = Integer.parseInt(document.select("div#maxwrap-maintopic div.consnav span #x-replys").text());
    	String postContent = document.select("div#F0 div.conright div.rconten div.maxtitle").text();
    	String postId = "";
    	String postTime = document.select("div#F0 div.conright .rtopcon span").get(5).text();
    	String postTitle = document.select("div#F0 div.conright div.rconten div.qa-maxtitle").text();
    	UserModel postUser = new UserModel(document.select("div#F0 div ul.maxw li a").text(), document.select("div#F1").attr("uid"));
    	
    	MainPost mainPost = new MainPost();
    	mainPost.setClickedTimes(clickedTimes);
    	mainPost.setReplyTimes(replyTimes);
    	mainPost.setPostContent(postContent);
    	mainPost.setPostId(postId);
    	mainPost.setPostTime(postTime);
    	mainPost.setPostTitle(postTitle);
    	mainPost.setPostUser(postUser);
    	return mainPost;
    }
    public static List<FollowPost> getAllFollowPost(String url) throws MalformedURLException, IOException{
    	Document document = null;
    	List<FollowPost> followPosts = new ArrayList<FollowPost>();
    	document = Jsoup.parse(new URL(url), 2000);
		MainPost mainpost = getMainPost(document);
		int currentPage = 1;
		for(int i=1; i< mainpost.getReplyTimes(); i++){
			if(document.select("div#F" + i).isEmpty()){
				document = Jsoup.parse(new URL(getPage(url, ++currentPage)), 2000);
			}
			
			UserModel user = new UserModel(document.select("div#F" + i + " div ul.maxw li a").text(), document.select("div#F" + i).attr("uid"));
			String content = document.select("div#F" + i + " div.conright div.x-reply").text();
			int floorNumber = i;
			String postTime = document.select("div#F" + i + " div.conright .rtopconnext span").get(1).text();;
			FollowPost followPost = new FollowPost();
			followPost.setContent(content);
			followPost.setUser(user);
			followPost.setFloorNumber(floorNumber);
			followPost.setPostTime(postTime);
			followPosts.add(followPost);
		}
    	return followPosts;
    }
    private static String getPage(String url, int pageNo){
    	String nextUrl = "";
    	nextUrl += url.substring(0, url.lastIndexOf("-") +1 );
    	nextUrl += pageNo;
    	nextUrl += url.substring(url.lastIndexOf("."));
    	return nextUrl;
    }
}
