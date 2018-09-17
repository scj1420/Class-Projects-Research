package handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

import java.io.IOException;

/**
 * Created by Seong-EunCho on 3/6/17.
 */

public class FileHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        String uri = "index.html";
        String filePathStr = "HTML" + File.separator+ uri;
        Path filePath = FileSystems.getDefault().getPath(filePathStr);
        OutputStreamWriter wr = new OutputStreamWriter(httpExchange.getResponseBody());
        String text = new String(Files.readAllBytes(Paths.get(filePathStr)), StandardCharsets.UTF_8);
        wr.write(text);
        wr.close();
        //Files.copy(filePath, httpExchange.getResponseBody());
    }
}
