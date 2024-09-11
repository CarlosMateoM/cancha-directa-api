package cancha.directa.mapper;
/*
R -> Record
E -> Entidad
 */
public interface IMapper <R, E>{
    R toRecord(E entity);
    E toEntity(R record);
}
