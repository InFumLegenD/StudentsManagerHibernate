package dao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fakultet", schema = "public", catalog = "postgres")
public class FakultetEntity {
    private int idFakultet;
    private String nameFakultet;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fakultet", nullable = false)
    public int getIdFakultet() {
        return idFakultet;
    }

    public void setIdFakultet(int idFakultet) {
        this.idFakultet = idFakultet;
    }

    @Basic
    @Column(name = "name_fakultet", nullable = false, length = -1)
    public String getNameFakultet() {
        return nameFakultet;
    }

    public void setNameFakultet(String nameFakultet) {
        this.nameFakultet = nameFakultet;
    }






    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FakultetEntity that = (FakultetEntity) o;

        if (idFakultet != that.idFakultet) return false;
        if (nameFakultet != null ? !nameFakultet.equals(that.nameFakultet) : that.nameFakultet != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFakultet;
        result = 31 * result + (nameFakultet != null ? nameFakultet.hashCode() : 0);
        return result;
    }
}
