package cancha.directa.dto;

import java.util.List;

/*
D = objeto DTO
E = Objeto Entidad
 */
public interface IDTO<D, E> {

   E toEntity(D dto);
   D toDTO(E entity);
   D toDTOAllAtributes(E entity);
   List<D> toDTOList(List<E> entityList);
   List<E> toEntityList(List<D> DTOList);
}
