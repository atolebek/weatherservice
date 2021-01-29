package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.WeatherInfoDTO;
import service.URLService;
import util.ResponseReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeoServiceServlet extends HttpServlet {
    URLService urlService = new URLService();
    ResponseReader reader = new ResponseReader();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        JsonObject jo = new JsonParser().parse(req.getParameter("json")).getAsJsonObject();
        String json = cityToTimezone(jo.get("zipCode").toString().replace("\"", ""));
        PrintWriter pw = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        pw.write(json);
        pw.close();
        System.out.println(json);
    }

    protected String cityToTimezone(String city){
        try {
            URL url = new URL(urlService.weatherByCityCodeURL(city));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection != null) {
                String responseBody = reader.getResponseBodyGET(connection);
                ObjectMapper mapper = new ObjectMapper();
                WeatherInfoDTO weatherInfoDTO = mapper.readValue(responseBody, WeatherInfoDTO.class);
                StringBuilder sb = new StringBuilder();
                sb.append(weatherInfoDTO.getCoordinate().getLat());
                sb.append(",");
                sb.append(weatherInfoDTO.getCoordinate().getLon());
                String timeZoneURL = urlService.timeZoneByLatLon(sb.toString());
                URL tmzURL = new URL(timeZoneURL);
                HttpURLConnection tmzCon = (HttpURLConnection) tmzURL.openConnection();
                if(tmzCon!=null){
                    String tmzBody = reader.getResponseBodyGET(tmzCon);
                    JsonObject jo = new JsonParser().parse(tmzBody).getAsJsonObject()
                            .get("resourceSets").getAsJsonArray().get(0).getAsJsonObject()
                            .get("resources").getAsJsonArray().get(0).getAsJsonObject()
                            .get("timeZone").getAsJsonObject()
                            .get("convertedTime").getAsJsonObject();
                    System.out.println(jo.get("resourceSets"));
                    return jo.toString();
                }
                return "";
            }
            return "";
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }
}
