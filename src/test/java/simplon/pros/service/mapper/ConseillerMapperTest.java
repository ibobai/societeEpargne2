package simplon.pros.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ConseillerMapperTest {

    private ConseillerMapper conseillerMapper;

    @BeforeEach
    public void setUp() {
        conseillerMapper = new ConseillerMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(conseillerMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(conseillerMapper.fromId(null)).isNull();
    }
}
