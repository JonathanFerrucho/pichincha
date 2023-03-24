package pichincha.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
public class TokenInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String jwttoken;

}
