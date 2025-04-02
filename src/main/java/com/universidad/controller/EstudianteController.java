package com.universidad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.universidad.dto.EstudianteDTO;
import com.universidad.service.iEstudianteService;

import java.util.List;  

/**
 * Controlador REST para gestionar estudiantes.
 * Permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar).
 */
@RestController // Indica que esta clase es un controlador REST en Spring
@RequestMapping("/api/estudiantes") // Define la ruta base para las solicitudes HTTP
public class EstudianteController {

    private final iEstudianteService estudianteService;

    /**
     * Inyección de dependencias del servicio de estudiante.
     */
    @Autowired
    public EstudianteController(iEstudianteService estudianteService){
        this.estudianteService = estudianteService;
    }
    
    /**
     * Obtener la lista de todos los estudiantes.
     * 
     * @return Lista de estudiantes en formato JSON con estado HTTP 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes(){
        List<EstudianteDTO> estudiantes = estudianteService.obtetenerTodosLosEstudiantes();
        return ResponseEntity.ok(estudiantes);
    } 

    /**
     * Obtener un estudiante específico por su ID.
     * 
     * @param id ID del estudiante.
     * @return El estudiante si existe, o HTTP 404 (Not Found) si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtenerEstudiantePorId(@PathVariable Long id){
        EstudianteDTO estudiante = estudianteService.obtenerEstudiantePorId(id);
        if (estudiante == null) {
            return ResponseEntity.notFound().build(); // Retorna HTTP 404 si el estudiante no existe
        }
        return ResponseEntity.ok(estudiante); // Retorna el estudiante con HTTP 200
    }

    /**
     * Guardar un nuevo estudiante.
     * 
     * @param estudianteDTO Datos del nuevo estudiante en el cuerpo de la petición.
     * @return Estudiante creado con estado HTTP 201 (Created).
     */
    @PostMapping
    public ResponseEntity<EstudianteDTO> crearEstudiante(@RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO nuevoEstudiante = estudianteService.guardarEstudiante(estudianteDTO);
        return ResponseEntity.status(201).body(nuevoEstudiante); // Retorna el estudiante creado con HTTP 201
    }

    /**
     * Actualizar un estudiante existente.
     * 
     * @param id ID del estudiante a actualizar.
     * @param estudianteDTO Datos nuevos del estudiante en el cuerpo de la petición.
     * @return Estudiante actualizado si existe, o HTTP 404 si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO){
        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiante(id, estudianteDTO);
        if (estudianteActualizado == null) {
            return ResponseEntity.notFound().build(); // Retorna HTTP 404 si el estudiante no existe
        }
        return ResponseEntity.ok(estudianteActualizado); // Retorna el estudiante actualizado con HTTP 200
    }

    /**
     * Eliminar un estudiante por su ID.
     * 
     * @param id ID del estudiante a eliminar.
     * @return HTTP 204 (No Content) si se eliminó, o HTTP 404 si no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Long id) {
        if (estudianteService.eliminarEstudiante(id)) {
            return ResponseEntity.noContent().build(); // Retorna HTTP 204 si se eliminó correctamente
        }
        return ResponseEntity.notFound().build(); // Retorna HTTP 404 si el estudiante no existe
    }
}
