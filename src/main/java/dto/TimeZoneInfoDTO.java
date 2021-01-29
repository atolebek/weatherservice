package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"authenticationResultCode", "brandLogoUri", "copyright", "statusCode", "statusDescription", "traceId"})
public class TimeZoneInfoDTO {

}
