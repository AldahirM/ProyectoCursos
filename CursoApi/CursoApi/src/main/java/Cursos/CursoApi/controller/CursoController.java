package Cursos.CursoApi.controller;

import Cursos.CursoApi.model.Curso;
import Cursos.CursoApi.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {

    
    public String holaCande(){
        return "Hola Cande";
    }
    


}
