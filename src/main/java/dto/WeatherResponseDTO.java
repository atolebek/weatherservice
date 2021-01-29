package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherResponseDTO {
    private String minTemp;
    private String maxTemp;
    private String curTemp;
    private String cityName;
    private String precipitation;
    private String main;
    private String description;
}
