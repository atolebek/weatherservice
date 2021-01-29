package service;

public class URLService {
    String weatherAPIKey = "0f86a8cf83e467b4851dfa16628267c2";
    String timeZoneAPIKey = "As7H70N6cajlfTFc0UteCpCt8jLrw3jnHQw1zUYDv2aA3Dia2nlP3vKwbGzOGL9g";
    public String weatherByCityCodeURL(String cityCode){
        return "http://api.openweathermap.org/data/2.5/weather?q=" + cityCode + "&units=metric&appid=" + weatherAPIKey;
    }

    public String timeZoneByLatLon(String latlon){
        return "https://dev.virtualearth.net/REST/v1/timezone/" + latlon + "?key=" + timeZoneAPIKey;
    }
}
