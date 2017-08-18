package translation.getdictionary;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GetDictionary {

	// HTTP GET request
	public String sendGet(String word) throws Exception {
		//word = "paper";
		String url = "https://cdict.net/q/" + word;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		//int responseCode = con.getResponseCode();
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		String content = response.toString();
		//System.out.println(content.substring(content.indexOf("content") + 9, content.indexOf("keywords") - 14));
		return content.substring(content.indexOf("content") + 9, content.indexOf("keywords") - 14);
	}

	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "http://127.0.0.1/test.php";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");

		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String data = "id=UTF8";

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		// wr.writeBytes(data);
		wr.write(data.getBytes("utf-8"));
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("Sending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + data);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		System.out.println(response.toString());

	}

	public static void main(String[] args) throws Exception {

		GetDictionary testhttp = new GetDictionary();
		// testhttp.sendPost();
		String queryword = "Ascension   ";
		queryword = queryword.trim();
		testhttp.sendGet(queryword);
	}
}