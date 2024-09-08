package cancha.directa.controller;

import cancha.directa.dto.impl.FieldDTO;
import cancha.directa.model.Field;
import cancha.directa.service.IFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/field")
public class FieldController {
    private final IFieldService fieldService;

    @Autowired
    public FieldController(IFieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<cancha.directa.dto.impl.FieldDTO> findById(@PathVariable Long id){
        Optional<Field> fieldOptional = this.fieldService.findById(id);

        if(fieldOptional.isPresent()){
            cancha.directa.dto.impl.FieldDTO fieldDTO = new cancha.directa.dto.impl.FieldDTO().toDTO(fieldOptional.get());
            return ResponseEntity.ok().body(fieldDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<cancha.directa.dto.impl.FieldDTO>> findAll(){
        List<Field> fieldList = this.fieldService.findAll();

        if(fieldList.isEmpty()){
           return ResponseEntity.notFound().build();
        }

        List<FieldDTO> fields = new FieldDTO().toDTOList(fieldList);
        return ResponseEntity.ok().body(fields);
    }

    @PostMapping("/save")
    public ResponseEntity<cancha.directa.dto.impl.FieldDTO> save(@RequestBody cancha.directa.dto.impl.FieldDTO fieldDTO) throws URISyntaxException {
        cancha.directa.dto.impl.FieldDTO dto = new cancha.directa.dto.impl.FieldDTO();

        if(fieldDTO.getName().isBlank()){
            ResponseEntity.badRequest().build();
        }
        Field newField = dto.toEntity(fieldDTO);
        this.fieldService.save(newField);
        return ResponseEntity.created(new URI("api/v1/field/save")).body(fieldDTO);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody cancha.directa.dto.impl.FieldDTO fieldDTO){

        return null;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById (@PathVariable Long id){

        if(id != null){
            this.fieldService.deleteById(id);
            return ResponseEntity.ok("Field deleted successfully");
        }

        return ResponseEntity.badRequest().body("Field not found");
    }
}
