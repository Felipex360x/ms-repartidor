package com.tiendacarta.repartidor.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendacarta.repartidor.Model.Repartidor;

@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor,Long> {
    
}
