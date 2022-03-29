package simplon.pros.service.mapper;


import simplon.pros.domain.*;
import simplon.pros.service.dto.TransfertDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Transfert} and its DTO {@link TransfertDTO}.
 */
@Mapper(componentModel = "spring", uses = {CompteMapper.class})
public interface TransfertMapper extends EntityMapper<TransfertDTO, Transfert> {

    @Mapping(source = "compte.id", target = "compteId")
    TransfertDTO toDto(Transfert transfert);

    @Mapping(source = "compteId", target = "compte")
    Transfert toEntity(TransfertDTO transfertDTO);

    default Transfert fromId(Long id) {
        if (id == null) {
            return null;
        }
        Transfert transfert = new Transfert();
        transfert.setId(id);
        return transfert;
    }
}
