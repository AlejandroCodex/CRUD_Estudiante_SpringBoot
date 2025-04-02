package com.universidad.repository;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import com.universidad.model.Estudiante;

@Repository // Indica que esta clase es un componente de acceso a datos en Spring
public class EstudianteRepository {

    // Mapa concurrente para almacenar los estudiantes en memoria
    private final Map<Long, Estudiante> estudiantes = new ConcurrentHashMap<>();

    // Contador atómico para generar IDs únicos automáticamente
    private final AtomicLong idContador = new AtomicLong(1);

    /**
     * Guarda un estudiante en el repositorio.
     * Si el estudiante no tiene ID, se le asigna uno automáticamente.
     * 
     * @param estudiante Objeto de tipo Estudiante que se guardará.
     * @return El estudiante guardado con su ID asignado.
     */
    public Estudiante save(Estudiante estudiante) {
        if (estudiante.getId() == null) { // Si el estudiante no tiene un ID, se le asigna uno
            estudiante.setId(idContador.getAndIncrement());
        }
        estudiantes.put(estudiante.getId(), estudiante); // Se almacena en el mapa
        return estudiante; // Retorna el estudiante guardado
    }

    /**
     * Obtiene la lista de todos los estudiantes almacenados.
     * 
     * @return Lista de estudiantes.
     */
    public List<Estudiante> findAll() {
        return new ArrayList<>(estudiantes.values()); // Retorna una lista con los valores del mapa
    }

    /**
     * Busca un estudiante por su ID.
     * 
     * @param id Identificador del estudiante.
     * @return El estudiante encontrado o null si no existe.
     */
    public Estudiante findById(Long id) {
        return estudiantes.get(id); // Retorna el estudiante correspondiente al ID
    }

    /**
     * Elimina un estudiante por su ID.
     * 
     * @param id Identificador del estudiante a eliminar.
     * @return true si se eliminó correctamente, false si el estudiante no existía.
     */
    public boolean deleteById(Long id) {
        return estudiantes.remove(id) != null; // Elimina el estudiante y verifica si existía
    }

    /**
     * Inicializa algunos estudiantes de ejemplo en la base de datos en memoria.
     * Se usa el patrón Builder para facilitar la creación de objetos.
     */
    public void init() {
        Estudiante estudiante1 = Estudiante.builder()
            .nombre("Juan")
            .apellido("Perez")
            .email("juan.perez@gmail.com")
            .fechaNacimiento(LocalDate.of(2000, 5, 15))
            .numeroInscripcion("5001")
            .build();

        Estudiante estudiante2 = Estudiante.builder()
            .nombre("Maria")
            .apellido("Gonzales")
            .email("maria.gonzales@gmail.com")
            .fechaNacimiento(LocalDate.of(2001, 11, 20))
            .numeroInscripcion("5002")
            .build();
        
        Estudiante estudiante3 = Estudiante.builder()
            .nombre("Jose")
            .apellido("Casas")
            .email("jose.casas@gmail.com")
            .fechaNacimiento(LocalDate.of(1999, 5, 17))
            .numeroInscripcion("5003")
            .build();

        Estudiante estudiante4 = Estudiante.builder()
            .nombre("Ana")
            .apellido("Ramirez")
            .email("ana.ramirez@gmail.com")
            .fechaNacimiento(LocalDate.of(2003, 1, 25))
            .numeroInscripcion("5004")
            .build();

        // Se guardan los estudiantes en el mapa
        save(estudiante1);
        save(estudiante2);
        save(estudiante3);
        save(estudiante4);
    }
}
