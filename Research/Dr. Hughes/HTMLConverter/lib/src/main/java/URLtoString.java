import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class URLtoString {

    private static final String FILENAME = "/Users/Seong-EunCho/Desktop/mathfeed_urls2.txt";

    public static void main(String[] args) {

        try {

            BufferedReader br = new BufferedReader(new FileReader(FILENAME));
            String webPage;
            int count = 1;

            while ((webPage = br.readLine()) != null) {

                if (webPage.matches("(.*)(twitter)(.*)")) {
                    continue;
                }

                try {

                    System.out.println(webPage);
                    String url = "https://www.w3.org/services/html2txt?url=" + webPage;

                    URL obj = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    // optional default is GET
                    con.setRequestMethod("GET");

                    //add request header
                    con.setRequestProperty("User-Agent", "Mozilla/5.0");

                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));

                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    ArrayList<StringBuilder> body = new ArrayList<>();
                    StringBuilder block = new StringBuilder();
                    StringBuilder bodyBlock = null;
                    int countInc = 0;
                    int countSent = 0;
                    int maxSent = 0;

                    while ((inputLine = in.readLine()) != null) {

                        //ignore links
                        if (inputLine.matches("(.*)://(.*)")) {
                            continue;
                        }
                        //ignores lines that starts with '+'
                        if (inputLine.matches("\\s{1,}[+](.*)")) {
                            continue;
                        }
                        //ignores lines that starts with '*'
                        if (inputLine.matches("\\s{1,}[*](.*)")) {
                            continue;
                        }
                        //ignores lines that starts with '#'
                        if (inputLine.matches("\\s{1,}[#](.*)")) {
                            continue;
                        }
                        //count the sentences in each block
                        if (inputLine.matches("(.*)(\\s[A-Z]?[a-z]{2,}[\\.\\!\\?])(.*)")) {
                            countSent++;
                        }

                        if (inputLine.length() > 3) {
                            if (inputLine.charAt(0) == ' ' && inputLine.charAt(1) == ' ' && inputLine.charAt(2) == ' ') {
                                block.append(inputLine);
                                block.append("\n");
                                countInc = 0;
                            } else {
                                if (countInc == 0) {
                                    body.add(block);
                                    if (countSent > maxSent) {
                                        maxSent = countSent;
                                        bodyBlock = block;
                                    }
                                    block = new StringBuilder();
                                    countSent = 0;
                                    block.append(inputLine);
                                    block.append("\n");
                                } else {
                                    block.append(inputLine);
                                    block.append("\n");
                                }
                                countInc++;

                            }
                        } else {
                            block.append(inputLine);
                            block.append("\n");
                        }


                        response.append(inputLine);
                        response.append("\n");
                    }
                    if (countSent > maxSent) {
                        countSent = maxSent;
                        bodyBlock = block;
                    }

                    in.close();

                    //print result
                    for (int i = 0; i < body.size(); i++) {
                        System.out.println("************ BLOCK " + i + " ************\n");
                        System.out.println(body.get(i).toString());
                        System.out.println("\n\n");
                    }

                    if (bodyBlock != null) {
                        System.out.println("************ ARTICLE BODY ************");
                        System.out.println(bodyBlock.toString());
                        PrintWriter out = new PrintWriter("article" + count + ".txt");
                        out.println(bodyBlock.toString());
                        out.close();
                        count++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}