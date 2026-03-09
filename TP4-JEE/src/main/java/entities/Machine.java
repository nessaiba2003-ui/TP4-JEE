package entities;

import java.util.Date;
import javax.persistence.*;

@Entity
@NamedNativeQuery(name = "findBetweenDateNative", query = "select * from machine where dateAchat between :d1 and :d2", resultClass = Machine.class)
@NamedQuery(name = "findBetweenDate", query = "from Machine where dateAchat between :d1 and :d2")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // other fields
    private Date dateAchat;
    @ManyToOne
    @JoinColumn(name = "salle_code")
    private Salle salle;

    public Salle getSalle() {
        return salle;
    }

    public void setDateAchat(Date ignoredDate) {
    }

    public void setSalle(Salle ignoredSalle) {
    }

    public int getId() {
        return 0;
    }

    public boolean getRef() {
        return false;
    }

    public Object setDateAchat() {
        return null;
    }

    public void setRef() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Constructeurs, getters et setters
}