package simplon.pros.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import simplon.pros.web.rest.TestUtil;

public class CompteepaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Compteepa.class);
        Compteepa compteepa1 = new Compteepa();
        compteepa1.setId(1L);
        Compteepa compteepa2 = new Compteepa();
        compteepa2.setId(compteepa1.getId());
        assertThat(compteepa1).isEqualTo(compteepa2);
        compteepa2.setId(2L);
        assertThat(compteepa1).isNotEqualTo(compteepa2);
        compteepa1.setId(null);
        assertThat(compteepa1).isNotEqualTo(compteepa2);
    }
}
