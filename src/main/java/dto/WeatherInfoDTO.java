package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"base", "visibility", "wind", "clouds", "dt", "sys", "timezone", "id", "cod"})
public class WeatherInfoDTO implements Serializable {

    @JsonProperty("coord")
    private Coordinate coordinate;

    @JsonProperty("weather")
    private WeatherGeneralInfo[] weatherGeneral;

    @JsonProperty("main")
    private WeatherMainInfo weatherMain;

    @JsonProperty("name")
    private String name;
}
