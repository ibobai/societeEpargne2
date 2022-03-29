package simplon.pros.service.mapper;


import simplon.pros.domain.*;
import simplon.pros.service.dto.ComptecouDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Comptecou} and its DTO {@link ComptecouDTO}.
 */
@Mapper(componentModel = "spring", uses = {CompteMapper.class})
public interface ComptecouMapper extends EntityMapper<ComptecouDTO, Comptecou> {

    @Mapping(source = "compte.id", target = "compteId")
    ComptecouDTO toDto(Comptecou comptecou);

    @Mapping(source = "compteId", target = "compte")
    Comptecou toEntity(ComptecouDTO comptecouDTO);

    default Comptecou fromId(Long id) {
        if (id == null) {
            return null;
        }
        Comptecou comptecou = new Comptecou();
        comptecou.setId(id);
        return comptecou;
    }
}
