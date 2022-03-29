package simplon.pros.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CompteMapperTest {

    private CompteMapper compteMapper;

    @BeforeEach
    public void setUp() {
        compteMapper = new CompteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(compteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(compteMapper.fromId(null)).isNull();
    }
}
