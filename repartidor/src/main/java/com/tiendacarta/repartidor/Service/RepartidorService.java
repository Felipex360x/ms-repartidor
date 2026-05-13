package com.tiendacarta.repartidor.Service;


import feign.FeignException;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.tiendacarta.repartidor.Client.AutoClient;
import com.tiendacarta.repartidor.Client.UsuarioClient;
import com.tiendacarta.repartidor.DTO.AutoDTO;
import com.tiendacarta.repartidor.DTO.RepartidorCrateDTO;
import com.tiendacarta.repartidor.DTO.RepartidorDTO;
import com.tiendacarta.repartidor.DTO.UsuarioDTO;
import com.tiendacarta.repartidor.Excepction.RecursoNoEncontradoException;
import com.tiendacarta.repartidor.Excepction.ServicioNoDisponibleException;
import com.tiendacarta.repartidor.Model.Repartidor;
import com.tiendacarta.repartidor.Repository.RepartidorRepository;

@Service
public class RepartidorService {
    

    private static final Logger Log = LoggerFactory.getLogger(RepartidorService.class);

    @Autowired
    private RepartidorRepository repartidorRepository;
    @Autowired
    private UsuarioClient usuarioClient;
    @Autowired
    private AutoClient autoClient;

    public List<RepartidorDTO> findall(){
        Log.info("consultando todos los repartidores");
        return repartidorRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public RepartidorDTO findById(Long id){
        Repartidor repartidor = repartidorRepository.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Repartidor no Encotrado"+ id));
        return toDTO(repartidor);
    }

    public RepartidorDTO crear(RepartidorCrateDTO dto){
        Log.info("creando Repartidor: usuarioId={}",dto.getUsuarioId());
        UsuarioDTO usuario;
        AutoDTO auto;
        try{
            Log.info("Consultando a Servicio de usuario para el usuarioId={}",dto.getUsuarioId());
            usuario = usuarioClient.obtenerUsuarios(dto.getUsuarioId());
            Log.info("Usuario encotrado: nombre={},correo={}",usuario.getNombre(),usuario.getCorreo());
        } catch (FeignException.NotFound e){
            throw new RecursoNoEncontradoException("Usuario no encotrado"+ dto.getUsuarioId());
        } catch(FeignException e){
            Log.error("Error al cactactar con servico de usuari: {}",e.getMessage());
            throw new ServicioNoDisponibleException("servicio de usuari no dispobibl,intnte nuevamente ");
        }
        /*agregamos el vehiculoe ne el crear */
        try{
            Log.info("consulatndo a servicio de vehiculo para el vehiculoId={}",dto.getAutoId());
            auto = autoClient.obtenerAutos((dto.getAutoId()));
            Log.info("Vehiculo encontrado: matricula={},marca={}",auto.getMatricula(),auto.getMarca());
        } catch(FeignException.NotFound e){
            throw new RecursoNoEncontradoException("vehiculo no enontrado" + dto.getAutoId());
        } catch(FeignException e){
            Log.error("error al contactar con el servicio de vehiculo: {}",e.getMessage());
            throw new ServicioNoDisponibleException("el servicio de vehiculo no disponible, intete nuevamente");
        }

        Repartidor repartidor = new Repartidor();
        repartidor.setUsuarioId(dto.getUsuarioId());
        repartidor.setAutoID(dto.getAutoId());
        repartidor.setCantidadEnvio(dto.getCantidadEnvio());
        repartidor.setHistorialEnvio(dto.getHistorialEnvio());
        repartidor.setTiendaOrigen(dto.getTiendaOrigen());
        
        Repartidor guardar = repartidorRepository.save(repartidor);
        Log.info("Repartidor Creado id={} - usuario={} - correo={} - vehiculo={} - modelo={}",guardar.getId(),usuario.getNombre(),usuario.getCorreo(),auto.getMarca(),auto.getModelo());
        return toDTOConNombre(guardar,usuario.getNombre(),usuario.getCorreo(),auto.getMatricula(),auto.getMarca(),auto.getModelo());
    }

    public RepartidorDTO eliminar(Long id){
        Log.info("eliminando repartidor por Id={}",id);
        Repartidor repartidor = repartidorRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Repartidor no ecntrado"+ id));
        return toDTO(repartidorRepository.save(repartidor));
    }


    private RepartidorDTO toDTO(Repartidor r){
        return new RepartidorDTO(
        r.getId(),
        r.getUsuarioId(),
        null,
        null,
        r.getAutoID(),
        null,
        null,
        null,
        r.getCantidadEnvio(),
        r.getHistorialEnvio(),
        r.getTiendaOrigen()
        );
    }

    public RepartidorDTO actualizar(Long id, RepartidorCrateDTO dto){
        Log.info("se actualizo el Repartidor id={}",id);
        Repartidor repartidor = repartidorRepository.findById(id).orElseThrow(()-> new RecursoNoEncontradoException("Repartidor no Encontrado" + id));
        UsuarioDTO usuario;
        AutoDTO auto;
        try{
            usuario = usuarioClient.obtenerUsuarios(dto.getUsuarioId());
        } catch(FeignException.NotFound e){
            throw new RecursoNoEncontradoException("el usuario no existe:" +dto.getUsuarioId());
        } catch(FeignException e){
            throw new ServicioNoDisponibleException("servicio de usuario no se esncuetra disponible");
        }
        try{
            auto = autoClient.obtenerAutos(dto.getAutoId());
        } catch(FeignException.NotFound e){
            throw new RecursoNoEncontradoException("el vehiculo no existe:" + dto.getAutoId());
        } catch(FeignException e){
            throw new ServicioNoDisponibleException("servicio de vehiculo no disponible");
        }
        repartidor.setUsuarioId(dto.getUsuarioId());
        repartidor.setAutoID(dto.getAutoId());
        repartidor.setCantidadEnvio(dto.getCantidadEnvio());
        repartidor.setHistorialEnvio(dto.getHistorialEnvio());
        repartidor.setTiendaOrigen(dto.getTiendaOrigen());

        Repartidor actualizado = repartidorRepository.save(repartidor);
        Log.info("Repartidor Actualizado id={} - usuario={} - vehiculo={}",actualizado.getId());
        return toDTOConNombre(actualizado,usuario.getNombre(),usuario.getCorreo(),auto.getMatricula(),auto.getMarca(),auto.getModelo());
    }



    public RepartidorDTO toDTOConNombre(Repartidor r, String nombreUsuario, String correoUsuario,String matricula,String marca,String modelo){
        return new RepartidorDTO(
            r.getId(),
            r.getUsuarioId(),
            nombreUsuario,
            correoUsuario,
            r.getAutoID(),
            matricula,
            marca,
            modelo,
            r.getCantidadEnvio(),
            r.getHistorialEnvio(),
            r.getTiendaOrigen()
        );
    }






}
