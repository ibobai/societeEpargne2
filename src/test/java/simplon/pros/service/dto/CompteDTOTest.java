package simplon.pros.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import simplon.pros.web.rest.TestUtil;

public class CompteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CompteDTO.class);
        CompteDTO compteDTO1 = new CompteDTO();
        compteDTO1.setId(1L);
        CompteDTO compteDTO2 = new CompteDTO();
        assertThat(compteDTO1).isNotEqualTo(compteDTO2);
        compteDTO2.setId(compteDTO1.getId());
        assertThat(compteDTO1).isEqualTo(compteDTO2);
        compteDTO2.setId(2L);
        assertThat(compteDTO1).isNotEqualTo(compteDTO2);
        compteDTO1.setId(null);
        assertThat(compteDTO1).isNotEqualTo(compteDTO2);
    }
}
