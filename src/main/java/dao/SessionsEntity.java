package dao;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "sessions", schema = "public", catalog = "postgres")
public class SessionsEntity {
    private int idSession;
    private String date;
    private int mark;
    private String student;
    private String subject;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session", nullable = false)
    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public String  getDate() {
        return date;
    }

    public void setDate(String  date) {
        this.date = date;
    }



    @Basic
    @Column(name = "student", nullable = false, length = -1)
    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }



    @Basic
    @Column(name = "subject", nullable = false, length = -1)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    @Column(name = "mark", nullable = false)
    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionsEntity that = (SessionsEntity) o;

        if (idSession != that.idSession) return false;
        if (mark != that.mark) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSession;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + mark;
        return result;
    }
}
