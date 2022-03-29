package simplon.pros.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CompteepaMapperTest {

    private CompteepaMapper compteepaMapper;

    @BeforeEach
    public void setUp() {
        compteepaMapper = new CompteepaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(compteepaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(compteepaMapper.fromId(null)).isNull();
    }
}
