package simplon.pros.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TransfertMapperTest {

    private TransfertMapper transfertMapper;

    @BeforeEach
    public void setUp() {
        transfertMapper = new TransfertMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(transfertMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(transfertMapper.fromId(null)).isNull();
    }
}
