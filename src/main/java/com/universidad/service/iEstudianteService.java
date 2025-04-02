package com.universidad.service;

import com.universidad.dto.EstudianteDTO;
import java.util.List;

// Interfaz para la gestión de estudiantes
public interface iEstudianteService {

    // Obtener todos los estudiantes
    List<EstudianteDTO> obtetenerTodosLosEstudiantes();

    // Obtener estudiante por ID
    EstudianteDTO obtenerEstudiantePorId(Long id);

    // Guardar un nuevo estudiante
    EstudianteDTO guardarEstudiante(EstudianteDTO estudianteDTO);

    // Actualizar un estudiante existente
    EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO);

    // Eliminar un estudiante por ID
    boolean eliminarEstudiante(Long id);
}