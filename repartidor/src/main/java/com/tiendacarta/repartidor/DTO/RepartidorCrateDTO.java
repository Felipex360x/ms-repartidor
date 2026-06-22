package com.tiendacarta.repartidor.DTO;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Datos necesarios para crear o actualizar un Repartidor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepartidorCrateDTO {
    
    @Schema(description = "id de usuario", example = "1")
    @NotNull(message = "ingresa el id del usuario")
    private Long usuarioId;
    /*dejarlo sin validacion */
    @Schema(description = "id de vehiculo", example = "1")
    private Long autoId;
    @Schema(description = "cantidad de envio", example = "1")
    @NotNull(message = "ingresa la cantidad de envio")
    private Integer cantidadEnvio;
    @Schema(description = "Historial de envio", example = "rancagua,dexnimension,delux clasis carta,completado,35.0000")
    @NotNull(message = "ingrese el historial de envio")
    private String historialEnvio;
    @Schema(description = "tienda de origen", example = "nex dimencion")
    @NotNull(message = "ingrese la tienda de origen")
    private String tiendaOrigen;
    
}
