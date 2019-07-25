package magiccards.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "magicexpansion")
public class Expansion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "expansionid")
    private int expansionId;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "ptbrname", columnDefinition = "text")
    private String ptBrName;
    @Column(name = "linkname", columnDefinition = "text")
    private String linkName;
    @Column(name = "code")
    private String code;
    @Column(name = "launchdate")
    private Date launchDate;
    @Column(name = "expansioncategoryid", columnDefinition = "smallint")
    private int expansionCategoryId;
    @Column(name = "ispromo")
    private boolean isPromo;
    @Column(name = "islegal")
    private boolean isLegal;
}
