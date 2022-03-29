package simplon.pros.service.mapper;


import simplon.pros.domain.*;
import simplon.pros.service.dto.CompteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Compte} and its DTO {@link CompteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompteMapper extends EntityMapper<CompteDTO, Compte> {



    default Compte fromId(Long id) {
        if (id == null) {
            return null;
        }
        Compte compte = new Compte();
        compte.setId(id);
        return compte;
    }
}
