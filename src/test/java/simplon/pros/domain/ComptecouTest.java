package simplon.pros.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import simplon.pros.web.rest.TestUtil;

public class ComptecouTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Comptecou.class);
        Comptecou comptecou1 = new Comptecou();
        comptecou1.setId(1L);
        Comptecou comptecou2 = new Comptecou();
        comptecou2.setId(comptecou1.getId());
        assertThat(comptecou1).isEqualTo(comptecou2);
        comptecou2.setId(2L);
        assertThat(comptecou1).isNotEqualTo(comptecou2);
        comptecou1.setId(null);
        assertThat(comptecou1).isNotEqualTo(comptecou2);
    }
}
