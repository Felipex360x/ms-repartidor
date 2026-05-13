package com.tiendacarta.repartidor.DTO;



import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepartidorCrateDTO {

    @NotNull(message = "ingresa el id del usuario")
    private Long usuarioId;
    @NotNull(message = "ingrese el id del auto")
    private Long autoId;
    @NotNull(message = "ingresa la cantidad de envio")
    private Integer cantidadEnvio;
    @NotNull(message = "ingrese el historial de envio")
    private String historialEnvio;
    @NotNull(message = "ingrese la tienda de origen")
    private String tiendaOrigen;
    
}
