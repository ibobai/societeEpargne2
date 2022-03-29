package simplon.pros.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ComptecouMapperTest {

    private ComptecouMapper comptecouMapper;

    @BeforeEach
    public void setUp() {
        comptecouMapper = new ComptecouMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(comptecouMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(comptecouMapper.fromId(null)).isNull();
    }
}
