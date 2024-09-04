package cancha.directa.dto;

import java.util.List;

/*
D = objeto DTO
E = Objeto Entidad
 */
public interface IDTO<D, E> {

   E DTOToEntity(D dto);
   D EntityToDTO(E entity);
   List<D> EntityListToDTOList(List<E> entityList);
}
