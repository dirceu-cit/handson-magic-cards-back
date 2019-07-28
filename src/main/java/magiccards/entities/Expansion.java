package magiccards.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "magicexpansion")
public class Expansion {
    @Id
    @Column(name = "expansionid")
    private Integer expansionId;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "ptbrname", columnDefinition = "text")
    private String ptBrName;
    @Column(name = "linkname", columnDefinition = "text")
    private String linkName;
    @Column(name = "code", columnDefinition = "text")
    private String code;
    @Column(name = "launchdate")
    private Date launchDate;
    @Column(name = "expansioncategoryid",columnDefinition = "smallint")
    private Integer expansionCategoryId;
    @Column(name = "ispromo")
    private Boolean promo;
    @Column(name = "islegal")
    private Boolean legal;

    public Expansion(Integer expansionId,String name, String ptBrName,String linkName, String code,Integer category,
    Boolean promo, Boolean legal, Date date){
        this.expansionId = expansionId;
        this.name = name;
        this.ptBrName = ptBrName;
        this.linkName = linkName;
        this.code = code;
        this.launchDate = new Date();
        this.expansionCategoryId = category;
        this.legal = legal;
        this.promo = promo;
        this.expansionCategoryId = category;
        this.launchDate = date;
    }

}
