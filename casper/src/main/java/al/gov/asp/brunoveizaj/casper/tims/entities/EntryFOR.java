package al.gov.asp.brunoveizaj.casper.tims.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="FOR_ENTRANCE")
public class EntryFOR implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private BorderPK id;
	
	@Column(name="GENDER")
	String gender;
	@Column(name="DOC_TYPE")
	String docType;
	@Column(name="DOB")
	String dob;
	@Column(name="DOE")
	@Temporal(TemporalType.TIMESTAMP)
	Date doe;
	@Column(name="CITIZEN_TYPE")
	String citizenType;
	@Column(name="TRAVEL")
	String travel;
	@Column(name="BCG")
	String bcg;
	@Column(name="DRIVER")
	Integer driver;
	@Column(name="PLATE")
	String plate;
	@Column(name="TIMS_ID")
	BigDecimal timsId;
	@Column(name="CODE_O_S")
	String codeOs;
	@Column(name="NID")
	String nid;
	@Column(name="FATHER_NAME")
	String fatherName;
	@Column(name="NAME")
	String name;
	@Column(name="SURNAME")
	String surname;
	@Column(name="PURPOSE")
	String purpose;
	@Column(name="NAT")
	String nationality;
	
	
	
	public BorderPK getId() {
		return id;
	}
	public void setId(BorderPK id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Date getDoe() {
		return doe;
	}
	public void setDoe(Date doe) {
		this.doe = doe;
	}
	public String getCitizenType() {
		return citizenType;
	}
	public void setCitizenType(String citizenType) {
		this.citizenType = citizenType;
	}
	public String getTravel() {
		return travel;
	}
	public void setTravel(String travel) {
		this.travel = travel;
	}
	public String getBcg() {
		return bcg;
	}
	public void setBcg(String bcg) {
		this.bcg = bcg;
	}
	public Integer getDriver() {
		return driver;
	}
	public void setDriver(Integer driver) {
		this.driver = driver;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public BigDecimal getTimsId() {
		return timsId;
	}
	public void setTimsId(BigDecimal timsId) {
		this.timsId = timsId;
	}
	public String getCodeOs() {
		return codeOs;
	}
	public void setCodeOs(String codeOs) {
		this.codeOs = codeOs;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
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
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	
	
}
	
	
	
	
	
	
	
	
	