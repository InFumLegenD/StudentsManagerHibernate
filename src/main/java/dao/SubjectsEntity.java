package dao;

import javax.persistence.*;

@Entity
@Table(name = "subjects", schema = "public", catalog = "postgres")
public class SubjectsEntity {
    private int idSubject;
    private String nameSubject;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_subject", nullable = false)
    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    @Basic
    @Column(name = "name_subject", nullable = false, length = -1)
    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectsEntity that = (SubjectsEntity) o;

        if (idSubject != that.idSubject) return false;
        if (nameSubject != null ? !nameSubject.equals(that.nameSubject) : that.nameSubject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSubject;
        result = 31 * result + (nameSubject != null ? nameSubject.hashCode() : 0);
        return result;
    }
}
