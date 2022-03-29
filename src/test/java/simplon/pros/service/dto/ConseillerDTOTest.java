package simplon.pros.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import simplon.pros.web.rest.TestUtil;

public class ConseillerDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConseillerDTO.class);
        ConseillerDTO conseillerDTO1 = new ConseillerDTO();
        conseillerDTO1.setId(1L);
        ConseillerDTO conseillerDTO2 = new ConseillerDTO();
        assertThat(conseillerDTO1).isNotEqualTo(conseillerDTO2);
        conseillerDTO2.setId(conseillerDTO1.getId());
        assertThat(conseillerDTO1).isEqualTo(conseillerDTO2);
        conseillerDTO2.setId(2L);
        assertThat(conseillerDTO1).isNotEqualTo(conseillerDTO2);
        conseillerDTO1.setId(null);
        assertThat(conseillerDTO1).isNotEqualTo(conseillerDTO2);
    }
}
