package cancha.directa.controller;

import cancha.directa.dto.impl.SportTypeDTO;
import cancha.directa.service.ISportTypeService;
import cancha.directa.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("api/v1/sporttype")
public class SportTypeController {

    private final ISportTypeService sportTypeService;

    @Autowired
    public SportTypeController(ISportTypeService sportTypeService){
        this.sportTypeService = sportTypeService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("api/v1/sporttype");
    }

    @JsonView(View.detailed.class)
    @GetMapping("/{id}")
    public ResponseEntity<SportTypeDTO> findById(@PathVariable("id") Long idUser){
        return ResponseEntity.ok(sportTypeService.findById(idUser));
    }

    @JsonView(View.general.class)
    @GetMapping("/all")
    public ResponseEntity<List<SportTypeDTO>> findAll (){
        return ResponseEntity.ok(sportTypeService.findAll());
    }

    @JsonView(View.general.class)
    @PostMapping("/save")
    public ResponseEntity<SportTypeDTO> save(@Valid @RequestBody SportTypeDTO sportTypeDTO) throws URISyntaxException {
        SportTypeDTO newSportTypeDTO = this.sportTypeService.save(sportTypeDTO);
        return ResponseEntity.created(new URI("api/v1/sporttype/save")).body(newSportTypeDTO);
    }

    @JsonView(View.general.class)
    @PutMapping("/update/{id}")
    public ResponseEntity<SportTypeDTO> update(@PathVariable Long id, @Valid @RequestBody SportTypeDTO sportTypeDTO){
        SportTypeDTO sportTypeUpdated = this.sportTypeService.updateById(id, sportTypeDTO);
        return ResponseEntity.ok().body(sportTypeUpdated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        this.sportTypeService.deleteById(id);
        return ResponseEntity.ok("SportType deleted");
    }

}
