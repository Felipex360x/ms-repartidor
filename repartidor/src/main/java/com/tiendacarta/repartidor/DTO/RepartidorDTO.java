package com.tiendacarta.repartidor.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepartidorDTO {

    private Long id;
    /*parte de usuario */
    private Long UsuarioId;
    private String nombreUsuario;
    private String correoUsuario;
    /*parte de vehiculo */
    private Long autoId;
    private String marcaAuto;
    private String modeloAuto;
    private String matricula;
    /*parte de repartidor */
    private Integer cantidadEnvio;
    private String historialEnvio;
    private String tiendaOrigen;
    
}
