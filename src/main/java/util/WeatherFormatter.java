package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.WeatherInfoDTO;
import dto.WeatherResponseDTO;

public class WeatherFormatter {
    public WeatherFormatter(){}
    public WeatherResponseDTO formatInfo(WeatherInfoDTO weatherInfoDTO){
        if(weatherInfoDTO != null) {
            WeatherResponseDTO responseDTO = new WeatherResponseDTO();
            responseDTO.setCurTemp(Double.toString(weatherInfoDTO.getWeatherMain().getTemp()));
            responseDTO.setMaxTemp(Double.toString(weatherInfoDTO.getWeatherMain().getTemp_max()));
            responseDTO.setMinTemp(Double.toString(weatherInfoDTO.getWeatherMain().getTemp_min()));
            responseDTO.setDescription(weatherInfoDTO.getWeatherGeneral()[0].getDescription());
            responseDTO.setPrecipitation(Double.toString(weatherInfoDTO.getWeatherMain().getHumidity()));
            responseDTO.setMain(weatherInfoDTO.getWeatherGeneral()[0].getMain());
            responseDTO.setCityName(weatherInfoDTO.getName());
            return responseDTO;
        }
        return null;
    }

    public String objectToJSON(WeatherResponseDTO responseDTO){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(responseDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
