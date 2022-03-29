package simplon.pros.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import simplon.pros.web.rest.TestUtil;

public class ConseillerTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Conseiller.class);
        Conseiller conseiller1 = new Conseiller();
        conseiller1.setId(1L);
        Conseiller conseiller2 = new Conseiller();
        conseiller2.setId(conseiller1.getId());
        assertThat(conseiller1).isEqualTo(conseiller2);
        conseiller2.setId(2L);
        assertThat(conseiller1).isNotEqualTo(conseiller2);
        conseiller1.setId(null);
        assertThat(conseiller1).isNotEqualTo(conseiller2);
    }
}
