package com.airbnb.airbnb.entities;

import com.airbnb.airbnb.enums.PropertyTypes;
import com.airbnb.airbnb.enums.ReservaType;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reserve {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String detail;
    private Integer total_quatity;
    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "id")
    private User client;
    @ManyToOne
    @JoinColumn(name = "property", referencedColumnName = "id")
    private Property property;
    @Temporal(TemporalType.DATE)
    Date startDate;
    @Temporal(TemporalType.DATE)
    Date endDate;
    ReservaType state;

}
