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
    public ResponseEntity<FieldDTO> findById(@PathVariable Long id){
        Optional<Field> fieldOptional = this.fieldService.findById(id);

        if(fieldOptional.isPresent()){
            FieldDTO fieldDTO = new FieldDTO().EntityToDTO(fieldOptional.get());
            return ResponseEntity.ok().body(fieldDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<FieldDTO>> findAll(){
        List<Field> fieldList = this.fieldService.findAll();

        if(fieldList.isEmpty()){
           return ResponseEntity.notFound().build();
        }

        List<FieldDTO> fields = new FieldDTO().EntityListToDTOList(fieldList);
        return ResponseEntity.ok().body(fields);
    }

    @PostMapping("/save")
    public ResponseEntity<FieldDTO> save(@RequestBody FieldDTO fieldDTO) throws URISyntaxException {
        FieldDTO dto = new FieldDTO();

        if(fieldDTO.getName().isBlank()){
            ResponseEntity.badRequest().build();
        }
        Field newField = dto.DTOToEntity(fieldDTO);
        this.fieldService.save(newField);
        return ResponseEntity.created(new URI("api/v1/field/save")).body(fieldDTO);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody FieldDTO fieldDTO){

        Optional<Field> optionalField;

        if (id != null){
             optionalField = this.fieldService.findById(id);
             if(optionalField.isPresent()){
                Field field = optionalField.get();

                field.setId(fieldDTO.getId());
                field.setName(fieldDTO.getName());
                field.setDescription(fieldDTO.getDescription());
                field.setStatus(fieldDTO.getStatus());
                field.set
            }
        }
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
