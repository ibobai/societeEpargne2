package simplon.pros.service.mapper;


import simplon.pros.domain.*;
import simplon.pros.service.dto.ClientDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CompteMapper.class})
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "compte.id", target = "compteId")
    ClientDTO toDto(Client client);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "compteId", target = "compte")
    Client toEntity(ClientDTO clientDTO);

    default Client fromId(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
