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

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v2/repartidores")
public class RepartidorController {

    @Autowired
    private  RepartidorService repartidorService;

    @GetMapping
    public ResponseEntity<List<RepartidorDTO>> getAll(){
        return ResponseEntity.ok(repartidorService.findall());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RepartidorDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(repartidorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<RepartidorDTO> crear(@Valid @RequestBody RepartidorCrateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repartidorService.crear(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        repartidorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepartidorDTO> actualizar(@PathVariable Long id, @RequestBody RepartidorCrateDTO dto){
        RepartidorDTO reparidorActualizado = repartidorService.actualizar(id, dto);
        return new ResponseEntity<>(reparidorActualizado,HttpStatus.OK);

    }

    
}
