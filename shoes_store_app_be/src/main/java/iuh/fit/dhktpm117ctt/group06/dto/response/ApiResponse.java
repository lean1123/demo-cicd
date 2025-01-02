package iuh.fit.dhktpm117ctt.group06.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T>{
    private int code = 200;
    private String message;
    private T result;
}
