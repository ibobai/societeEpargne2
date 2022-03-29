package simplon.pros.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import simplon.pros.web.rest.TestUtil;

public class ComptecouDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ComptecouDTO.class);
        ComptecouDTO comptecouDTO1 = new ComptecouDTO();
        comptecouDTO1.setId(1L);
        ComptecouDTO comptecouDTO2 = new ComptecouDTO();
        assertThat(comptecouDTO1).isNotEqualTo(comptecouDTO2);
        comptecouDTO2.setId(comptecouDTO1.getId());
        assertThat(comptecouDTO1).isEqualTo(comptecouDTO2);
        comptecouDTO2.setId(2L);
        assertThat(comptecouDTO1).isNotEqualTo(comptecouDTO2);
        comptecouDTO1.setId(null);
        assertThat(comptecouDTO1).isNotEqualTo(comptecouDTO2);
    }
}
