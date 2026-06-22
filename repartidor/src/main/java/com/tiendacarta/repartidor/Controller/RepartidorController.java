package com.tiendacarta.repartidor.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendacarta.repartidor.DTO.RepartidorCrateDTO;
import com.tiendacarta.repartidor.DTO.RepartidorDTO;
import com.tiendacarta.repartidor.Service.RepartidorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(name="repartidores",description = "Operaciones de gestion de repartidores")
@RestController
@RequestMapping("api/v2/repartidores")
public class RepartidorController {

    @Autowired
    private  RepartidorService repartidorService;

    @Operation(
        summary = "Listar todas los repartidores",
        description = "Retorna la lista completa de repartidores registradas en el sistema."
    )
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<RepartidorDTO>> getAll(){
        return ResponseEntity.ok(repartidorService.findall());
    }

    @Operation(summary = "Buscar repartidores por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "repartidores encontrada"),
        @ApiResponse(responseCode = "404", description = "repartidores no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RepartidorDTO> getById(@Parameter(description = "Id unico de la Usuario",required = true)@PathVariable Long id){
        return ResponseEntity.ok(repartidorService.findById(id));
    }


    @Operation(summary = "Registrar nueva repartidores")
    @ApiResponse(responseCode = "201", description = "repartidores creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @PostMapping
    public ResponseEntity<RepartidorDTO> crear(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Datos del nuevo repartidores"
            )
            @Valid @RequestBody RepartidorCrateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repartidorService.crear(dto));
    }
    @Operation(summary = "Eliminar repartidores")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Eliminación exitosa"),
        @ApiResponse(responseCode = "404", description = "repartidores no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del repartidores a eliminar", required = true)
            @PathVariable Long id) {
        repartidorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Actualizar repartidores existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
        @ApiResponse(responseCode = "404", description = "repartidores no encontrada"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RepartidorDTO> actualizar(
            @Parameter(description = "ID del repartidores a actualizar", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Nuevos datos del repartidores"
            )
            @Valid @RequestBody RepartidorCrateDTO dto) {
        return ResponseEntity.ok(repartidorService.actualizar(id, dto));
    }


    
}
