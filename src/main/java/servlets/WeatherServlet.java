package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import dto.WeatherInfoDTO;
import dto.WeatherRequestDTO;
import dto.WeatherResponseDTO;
import service.URLService;
import util.WeatherFormatter;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class WeatherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    URLService urlService = new URLService();
    WeatherFormatter weatherFormatter = new WeatherFormatter();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cityCode = req.getParameter("cityCode");
        WeatherResponseDTO responseDTO = weatherFormatter.formatInfo(jsonToWeatherPOJO(cityCode));
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String json = gson.toJson(responseDTO);
        PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        pw.write(json);
        pw.close();
    }

    private WeatherInfoDTO jsonToWeatherPOJO(String cityCode) throws IOException {
        HttpURLConnection connection = weatherAPIConnection(urlService.weatherByCityCodeURL(cityCode));
        if (connection != null) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            ObjectMapper mapper = new ObjectMapper();
            WeatherInfoDTO weatherInfoDTO = mapper.readValue(content.toString(), WeatherInfoDTO.class);
            return weatherInfoDTO;
        }
        return null;
    }

    private HttpURLConnection weatherAPIConnection(String link) throws IOException {
        try {
            URL url = new URL(link);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            return connection;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
