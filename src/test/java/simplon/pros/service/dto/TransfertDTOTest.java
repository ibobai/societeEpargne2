package simplon.pros.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import simplon.pros.web.rest.TestUtil;

public class TransfertDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransfertDTO.class);
        TransfertDTO transfertDTO1 = new TransfertDTO();
        transfertDTO1.setId(1L);
        TransfertDTO transfertDTO2 = new TransfertDTO();
        assertThat(transfertDTO1).isNotEqualTo(transfertDTO2);
        transfertDTO2.setId(transfertDTO1.getId());
        assertThat(transfertDTO1).isEqualTo(transfertDTO2);
        transfertDTO2.setId(2L);
        assertThat(transfertDTO1).isNotEqualTo(transfertDTO2);
        transfertDTO1.setId(null);
        assertThat(transfertDTO1).isNotEqualTo(transfertDTO2);
    }
}
