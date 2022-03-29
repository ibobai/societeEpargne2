package simplon.pros.service.mapper;


import simplon.pros.domain.*;
import simplon.pros.service.dto.ConseillerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Conseiller} and its DTO {@link ConseillerDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ConseillerMapper extends EntityMapper<ConseillerDTO, Conseiller> {

    @Mapping(source = "user.id", target = "userId")
    ConseillerDTO toDto(Conseiller conseiller);

    @Mapping(source = "userId", target = "user")
    Conseiller toEntity(ConseillerDTO conseillerDTO);

    default Conseiller fromId(Long id) {
        if (id == null) {
            return null;
        }
        Conseiller conseiller = new Conseiller();
        conseiller.setId(id);
        return conseiller;
    }
}
