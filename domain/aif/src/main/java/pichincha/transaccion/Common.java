package pichincha.transaccion;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Common<T> {

    private Boolean exito;
    private String mensaje;
    private final T data;
}
