package al.gov.asp.brunoveizaj.casper.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Bruno
 */
@Entity
@Table(name = "\"PHONE\"",schema = "repository")
public class Phone implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "\"ID\"")
    private int id; 
    
    @Column(name = "\"NID\"")
    private String nid; 
    @Column(name = "\"NAME\"")
    private String name;
    @Column(name = "\"SURNAME\"")
    private String surname;
    @Column(name = "\"FATHER_NAME\"")
    private String fatherName;
    @Column(name = "\"MOTHER_NAME\"")
    private String motherName;
    @Column(name = "\"DOB\"")
    private String dob;
    @Column(name = "\"PHONE\"")
    private String phone;    
    @Column(name = "\"DATA_SOURCE\"")
    private String source;
    @Column(name="\"INSERT_DATE\"")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date insertDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        
        this.nid = nid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }
    
    
    
    
}