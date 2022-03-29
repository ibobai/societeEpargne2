package simplon.pros.service.mapper;


import simplon.pros.domain.*;
import simplon.pros.service.dto.CompteepaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Compteepa} and its DTO {@link CompteepaDTO}.
 */
@Mapper(componentModel = "spring", uses = {CompteMapper.class})
public interface CompteepaMapper extends EntityMapper<CompteepaDTO, Compteepa> {

    @Mapping(source = "compte.id", target = "compteId")
    CompteepaDTO toDto(Compteepa compteepa);

    @Mapping(source = "compteId", target = "compte")
    Compteepa toEntity(CompteepaDTO compteepaDTO);

    default Compteepa fromId(Long id) {
        if (id == null) {
            return null;
        }
        Compteepa compteepa = new Compteepa();
        compteepa.setId(id);
        return compteepa;
    }
}
