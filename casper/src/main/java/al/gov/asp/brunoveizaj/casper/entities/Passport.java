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
import javax.validation.constraints.Size;

@Entity
@Table(name = "\"BIOMETRIC_PASSPORT\"",schema = "repository")
public class Passport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "\"ID\"")
    private int id;
    @Size(max = 20)
    @Column(name = "\"IDP\"")
    private String idp;
    @Size(max = 10)
    @Column(name = "\"NID\"")
    private String nid;
    @Size(max = 5)
    @Column(name = "\"TYPE\"")
    private String type;
    @Size(max = 10)
    @Column(name = "\"ISSUE_DATE\"")
    private String issueDate;
    @Size(max = 5)
    @Column(name = "\"ISSUE_AUTHORITY\"")
    private String issueAuthority;
    @Size(max = 10)
    @Column(name = "\"EXPIRE_DATE\"")
    private String expireDate;
    @Size(max = 5)
    @Column(name = "\"CITIZENSHIP\"")
    private String citizenship;
    @Size(max = 5)
    @Column(name = "\"NATIONALITY\"")
    private String nationality;
    @Size(max = 50)
    @Column(name = "\"NAME\"")
    private String name;
    @Size(max = 50)
    @Column(name = "\"SURNAME\"")
    private String surname;
    @Size(max = 50)
    @Column(name = "\"FATHER_NAME\"")
    private String fatherName;
    @Size(max = 50)
    @Column(name = "\"MOTHER_NAME\"")
    private String motherName;
    @Size(max = 10)
    @Column(name = "\"DOB\"")
    private String dob;
    @Size(max = 1)
    @Column(name = "\"GENDER\"")
    private String gender;
    @Size(max = 50)
    @Column(name = "\"POB\"")
    private String pob;
    @Size(max = 100)
    @Column(name = "\"ADRESS\"")
    private String adress;
    @Size(max = 100)
    @Column(name = "\"FOLDER\"")
    private String folder;
    @Size(max = 20)
    @Column(name = "\"PHOTO_PATH\"")
    private String photoPath;
    @Column(name = "\"INSERT_TIME\"")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date insertDate;
    @Column(name = "\"INSERT_DATE\"")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date insertTime;

    public Passport() {
    }

    public Passport(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdp() {
        return idp;
    }

    public void setIdp(String idp) {
        this.idp = idp;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIssueDate() {
        return issueDate;
    }
    
    public String getIssueDateRow() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueAuthority() {
        return issueAuthority;
    }

    public void setIssueAuthority(String issueAuthority) {
        this.issueAuthority = issueAuthority;
    }

    public String getExpireDate() {
        return expireDate;
    }
    
    public String getExpireDateRow() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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

    public String getDob() {
        return dob;
    }
    
    public String getDobRow() {
        return dob;
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

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    
    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    
}