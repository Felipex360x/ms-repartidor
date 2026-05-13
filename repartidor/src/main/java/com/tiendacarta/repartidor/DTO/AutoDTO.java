package com.tiendacarta.repartidor.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AutoDTO {

    private Long id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private String version;
    private Integer ano;
    private String TipoVehi;
    private LocalDate fechaingreso;
    
}
