package dao;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "potok", schema = "public", catalog = "postgres")
public class PotokEntity {
    private int idPotok;
    private String year;
    private String fakultet;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_potok", nullable = false)
    public int getIdPotok() {
        return idPotok;
    }

    public void setIdPotok(int idPotok) {
        this.idPotok = idPotok;
    }

    @Basic
    @Column(name = "year", nullable = false, length = -1)
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    @Basic
    @Column(name = "fakultet", nullable = false, length = -1)
    public String getFakultet() {
        return fakultet;
    }

    public void setFakultet(String fakultet) {
        this.fakultet = fakultet;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PotokEntity that = (PotokEntity) o;

        if (idPotok != that.idPotok) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPotok;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
