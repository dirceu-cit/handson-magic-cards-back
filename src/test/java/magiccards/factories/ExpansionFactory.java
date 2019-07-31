package magiccards.factories;

import java.sql.Timestamp;
import java.time.Instant;
import magiccards.entities.Expansion;

public class ExpansionFactory {

    public static Expansion createExpansion() {
        return Expansion.builder()
                .name("Expansion 1")
                .ptBrName("Expansão 1")
                .linkName("Expansion Link Name 1")
                .launchDate(Timestamp.from(Instant.now()))
                .expansionCategoryId(1)
                .isLegal(true)
                .isPromo(true)
                .build();
    }

}
