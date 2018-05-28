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

/**
 *
 * @author Bruno
 */
@Entity
@Table(name = "\"CARD\"",schema = "repository")
public class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "\"ID\"")
    private int id;
    @Size(max = 9)
    @Column(name = "\"IDC\"")
    private String idc;
    @Size(max = 10)
    @Column(name = "\"NID\"")
    private String nid;
    @Size(max = 5)
    @Column(name = "\"CARD_TYPE\"")
    private String cardType;
    @Size(max = 10)
    @Column(name = "\"ISSUE_DATE\"")
    private String issueDate;
    @Size(max = 5)
    @Column(name = "\"ISSUE_AUTHORITY\"")
    private String issueAuthority;
    @Size(max = 5)
    @Column(name = "\"ZGJC_CODE\"")
    private String zgjcCode;
    @Size(max = 10)
    @Column(name = "\"EXPIRE_DATE\"")
    private String expireDate;
    @Size(max = 5)
    @Column(name = "\"NATIONALITY\"")
    private String nationality;
    @Size(max = 5)
    @Column(name = "\"CITIZENSHIP\"")
    private String citizenship;
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
    @Size(max = 10)
    @Column(name = "\"GENDER\"")
    private String gender;
    @Size(max = 80)
    @Column(name = "\"POB\"")
    private String pob;
    @Size(max = 50)
    @Column(name = "\"REGION\"")
    private String region;
    @Size(max = 50)
    @Column(name = "\"CITY\"")
    private String city;
    @Size(max = 50)
    @Column(name = "\"STREET\"")
    private String street;
    @Size(max = 200)
    @Column(name = "\"BUILDING\"")
    private String building;
    @Size(max = 100)
    @Column(name = "\"APPARTMENT\"")
    private String appartment;
    @Size(max = 20)
    @Column(name = "\"TEL_ONE\"")
    private String telOne;
    @Size(max = 20)
    @Column(name = "\"TEL_TWO\"")
    private String telTwo;
    @Size(max = 20)
    @Column(name = "\"PHOTO_PATH\"")
    private String photoPath;
    @Column(name = "\"INSERT_DATE\"")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date insertDate;
    @Column(name = "\"INSERT_TIME\"")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date insertTimestamp;
    @Size(max = 200)
    @Column(name = "\"FOLDER\"")
    private String folder;

    public Card() {
    }

    public Card(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdc() {
        return idc;
    }

    public void setIdc(String idc) {
        this.idc = idc;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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

    public String getZgjcCode() {
        return zgjcCode;
    }

    public void setZgjcCode(String zgjcCode) {
        this.zgjcCode = zgjcCode;
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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    public String getTelOne() {
        return telOne;
    }

    public void setTelOne(String telOne) {
        this.telOne = telOne;
    }

    public String getTelTwo() {
        return telTwo;
    }

    public void setTelTwo(String telTwo) {
        this.telTwo = telTwo;
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

    public void setInsertDate(Date inserTime) {
        this.insertDate = inserTime;
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

    public Date getInsertTimestamp() {
        return insertTimestamp;
    }

    public void setInsertTimestamp(Date insertTimestamp) {
        this.insertTimestamp = insertTimestamp;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
    

    
}
