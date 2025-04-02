package com.universidad.service.impl;

import com.universidad.dto.EstudianteDTO;
import com.universidad.model.Estudiante;
import com.universidad.repository.EstudianteRepository;
import com.universidad.service.iEstudianteService;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteServiceImpl implements iEstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteServiceImpl(EstudianteRepository estudianteRepository){
        this.estudianteRepository = estudianteRepository;
    }

    // Método que se ejecuta después de la construcción del bean para inicializar datos de prueba
    @PostConstruct
    public void init(){
        estudianteRepository.init();
    }

    // Obtener la lista de todos los estudiantes en formato DTO
    @Override
    public List<EstudianteDTO> obtetenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteRepository.findAll(); // Recupera los estudiantes de la base de datos
        List<EstudianteDTO> estudiantesDTO = new ArrayList<>();

        for (Estudiante estudiante : estudiantes){
            estudiantesDTO.add(convertToDTO(estudiante)); // Convierte cada entidad en DTO
        }
        
        return estudiantesDTO;
    }

    // Buscar un estudiante por su ID y devolverlo como DTO
    @Override
    public EstudianteDTO obtenerEstudiantePorId(Long id){
        Estudiante estudiante = estudianteRepository.findById(id);
        if(estudiante == null){
            return null; // Retorna null si no se encuentra el estudiante
        }
        return convertToDTO(estudiante);
    }

    // Guardar un nuevo estudiante en la base de datos
    @Override
    public EstudianteDTO guardarEstudiante(EstudianteDTO estudianteDTO) {
        Estudiante estudiante = convertToEntity(estudianteDTO); // Convierte el DTO a entidad
        estudiante = estudianteRepository.save(estudiante); // Guarda el estudiante en la base de datos
        return convertToDTO(estudiante); // Devuelve el estudiante guardado como DTO
    }

    // Actualizar la información de un estudiante existente
    @Override
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteDTO estudianteDTO) {
        Estudiante estudiante = estudianteRepository.findById(id);
        if(estudiante == null){
            return null; // Retorna null si el estudiante no existe
        }
        
        // Actualiza los datos del estudiante
        estudiante.setNombre(estudianteDTO.getNombre());
        estudiante.setApellido(estudianteDTO.getApellido());
        estudiante.setEmail(estudianteDTO.getEmail());
        estudiante.setFechaNacimiento(estudianteDTO.getFechaNacimiento());
        estudiante.setNumeroInscripcion(estudianteDTO.getNumeroInscripcion());

        estudianteRepository.save(estudiante); // Guarda los cambios en la base de datos

        return convertToDTO(estudiante);
    }

    // Eliminar un estudiante de la base de datos por su ID
    @Override
    public boolean eliminarEstudiante(Long id) {
        return estudianteRepository.deleteById(id); // Retorna true si se eliminó correctamente
    }

    // Método auxiliar para convertir una entidad Estudiante a un DTO
    private EstudianteDTO convertToDTO(Estudiante estudiante){
        return EstudianteDTO.builder()
        .id(estudiante.getId())
        .nombre(estudiante.getNombre())
        .apellido(estudiante.getApellido())
        .email(estudiante.getEmail())
        .fechaNacimiento(estudiante.getFechaNacimiento())
        .numeroInscripcion(estudiante.getNumeroInscripcion())
        .build();
    }

    // Método auxiliar para convertir un DTO a una entidad Estudiante
    private Estudiante convertToEntity(EstudianteDTO estudianteDTO){
        return Estudiante.builder()
        .id(estudianteDTO.getId())
        .nombre(estudianteDTO.getNombre())
        .apellido(estudianteDTO.getApellido())
        .email(estudianteDTO.getEmail())
        .fechaNacimiento(estudianteDTO.getFechaNacimiento())
        .numeroInscripcion(estudianteDTO.getNumeroInscripcion())
        .build();
    }
}