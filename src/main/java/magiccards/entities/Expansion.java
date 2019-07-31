package magiccards.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "magicexpansion")
@Builder
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "launchdate", columnDefinition = "date")
    private Date launchDate;
    @Column(name = "expansioncategoryid", columnDefinition = "smallint")
    private Integer expansionCategoryId;
    @Column(name = "ispromo", columnDefinition = "bit(1) default 0")
    private Boolean isPromo;
    @Column(name = "islegal", columnDefinition = "bit(1) default 0")
    private Boolean isLegal;

}
