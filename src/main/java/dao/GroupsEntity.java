package dao;

import org.hibernate.annotations.Generated;

import javax.persistence.*;

@Entity
@Table(name = "groups", schema = "public", catalog = "postgres")
public class GroupsEntity {
    private int idGroup;
    private int potok_id;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_group", nullable = false)
    public int getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(int idGroup) {
        this.idGroup = idGroup;
    }

    @Basic
    @Column(name = "potok_id")
    public int getPotok_id() {
        return potok_id;
    }

    public void setPotok_id(int potok_id) {
        this.potok_id = potok_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntity that = (GroupsEntity) o;

        if (idGroup != that.idGroup) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idGroup;
    }
}


