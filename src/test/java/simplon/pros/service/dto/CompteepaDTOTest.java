package simplon.pros.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import simplon.pros.web.rest.TestUtil;

public class CompteepaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompteepaDTO.class);
        CompteepaDTO compteepaDTO1 = new CompteepaDTO();
        compteepaDTO1.setId(1L);
        CompteepaDTO compteepaDTO2 = new CompteepaDTO();
        assertThat(compteepaDTO1).isNotEqualTo(compteepaDTO2);
        compteepaDTO2.setId(compteepaDTO1.getId());
        assertThat(compteepaDTO1).isEqualTo(compteepaDTO2);
        compteepaDTO2.setId(2L);
        assertThat(compteepaDTO1).isNotEqualTo(compteepaDTO2);
        compteepaDTO1.setId(null);
        assertThat(compteepaDTO1).isNotEqualTo(compteepaDTO2);
    }
}
