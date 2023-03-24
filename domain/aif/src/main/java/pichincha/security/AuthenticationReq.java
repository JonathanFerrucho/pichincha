package pichincha.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private String usuario;

    private String clave;

}
