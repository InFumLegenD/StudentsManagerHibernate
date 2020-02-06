package dao;

import javax.persistence.*;

@Entity
@Table(name = "students", schema = "public", catalog = "postgres")
public class StudentsEntity {
    private int idStudent;
    private String fio;
    private int group;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student", nullable = false)
    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }



    @Basic
    @Column(name = "groups", nullable = false)
    public int getGroup() {
        return group;
    }
    public void setGroup(int group) {
        this.group = group;
    }

    @Basic
    @Column(name = "fio", nullable = false, length = -1)
    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsEntity that = (StudentsEntity) o;

        if (idStudent != that.idStudent) return false;
        if (fio != null ? !fio.equals(that.fio) : that.fio != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStudent;
        result = 31 * result + (fio != null ? fio.hashCode() : 0);
        return result;
    }
}
