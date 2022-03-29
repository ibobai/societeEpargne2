package simplon.pros.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import simplon.pros.web.rest.TestUtil;

public class TransfertTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Transfert.class);
        Transfert transfert1 = new Transfert();
        transfert1.setId(1L);
        Transfert transfert2 = new Transfert();
        transfert2.setId(transfert1.getId());
        assertThat(transfert1).isEqualTo(transfert2);
        transfert2.setId(2L);
        assertThat(transfert1).isNotEqualTo(transfert2);
        transfert1.setId(null);
        assertThat(transfert1).isNotEqualTo(transfert2);
    }
}
