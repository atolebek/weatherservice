package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {

    @JsonProperty("lon")
    private String lon;

    @JsonProperty("lat")
    private String lat;

}
