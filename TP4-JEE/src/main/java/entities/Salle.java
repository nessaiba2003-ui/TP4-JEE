package entities;

import javax.persistence.*;

@Entity
@Table(name = "salles")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code;

    public Salle() {

        this.code = null;
    }

    public void setCode() {
    }

    public String getCode() {
        return code;
    }

    public int getId() {
        return 0;
    }

    public Machine[] getMachines() {
        return new Machine[0];
    }

    // Constructeurs, getters et setters


}
