package Cursos.CursoApi.controller;

import Cursos.CursoApi.model.Escuela;
import Cursos.CursoApi.model.Curso;
import Cursos.CursoApi.repository.EscuelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/escuela")
public class EscuelaController {
    @Autowired
    EscuelaRepository escuelaRepository;

    @GetMapping
    public ResponseEntity<Iterable<Escuela>> getEscuelas() {
        return ResponseEntity.ok(escuelaRepository.findAll());
    }

    @GetMapping("/{CCT}")
    public ResponseEntity<Escuela> getEscuela(@PathVariable String CCT) {
        Optional<Escuela> escuelaOptional = escuelaRepository.findById(CCT);
        if (escuelaOptional.isPresent()) {
            return ResponseEntity.ok(escuelaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Escuela newEscuela, UriComponentsBuilder ucb) {
        Escuela savedEscuela = escuelaRepository.save(newEscuela);
        URI uri = ucb.path("/escuela/{CCT}").buildAndExpand(savedEscuela.getIdEscuela()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{CCT}")
    public ResponseEntity<Void> update(@PathVariable String CCT, @RequestBody Escuela escuelaAct) {
        Escuela escuelaAnt = escuelaRepository.findById(CCT).get();
        if (escuelaAnt != null) {
            escuelaAct.setIdEscuela(escuelaAnt.getIdEscuela());
            escuelaRepository.save(escuelaAct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{CCT}")
    public ResponseEntity<Void> delete(@PathVariable String CCT) {
        if (escuelaRepository.findById(CCT).get() != null) {
            escuelaRepository.deleteById(CCT);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{CCT}/cursos")
    public ResponseEntity<Iterable<Curso>> getCursos(@PathVariable String CCT) {
        Optional<Escuela> escuelaOptional = escuelaRepository.findById(CCT);
        if (!(escuelaOptional.isPresent())) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(escuelaOptional.get().getCursos());
    }
}
