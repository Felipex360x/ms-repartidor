package com.tiendacarta.repartidor.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tiendacarta.repartidor.DTO.AutoDTO;


@FeignClient(
    name = "vehiculos",
    url= "${vehiculo.service.url}"
)


public interface AutoClient {
    @GetMapping("/api/v2/vehiculos/{id}")
    AutoDTO obtenerAutos(@PathVariable("id")Long id);

    
} 
