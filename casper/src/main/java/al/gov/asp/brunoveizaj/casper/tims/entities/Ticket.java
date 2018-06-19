package al.gov.asp.brunoveizaj.casper.tims.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="TICKET")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private long id;
	@Column(name="SN")
	private String serialNo;
	@Column(name="OFFICER")
	private String officer;
	@Column(name="TICKET_DATE")
	private Date ticketDate;
	@Column(name="TICKET_ADDRESS")
	private String ticketAddress;
	@Column(name="AMOUNT")
	private Double amount;
	@Column(name="PLATE")
	private String plate;
	@Column(name="PRODUCER")
	private String producer;
	@Column(name="OWNER")
	private String owner;
	@Column(name="OWNER_ADDRESS")
	private String ownerAddress;
	@Column(name="VIOLATOR")
	private String violator;
	@Column(name="VIOLATOR_NID")
	private String violatorNid;
	@Column(name="PAYED")
	private Integer paid;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getOfficer() {
		return officer;
	}
	public void setOfficer(String officer) {
		this.officer = officer;
	}
	public Date getTicketDate() {
		return ticketDate;
	}
	public void setTicketDate(Date ticketDate) {
		this.ticketDate = ticketDate;
	}
	public String getTicketAddress() {
		return ticketAddress;
	}
	public void setTicketAddress(String ticketAddress) {
		this.ticketAddress = ticketAddress;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getViolator() {
		return violator;
	}
	public void setViolator(String violator) {
		this.violator = violator;
	}
	public String getViolatorNid() {
		return violatorNid;
	}
	public void setViolatorNid(String violatorNid) {
		this.violatorNid = violatorNid;
	}
	public Integer getPaid() {
		return paid;
	}
	public void setPaid(Integer paid) {
		this.paid = paid;
	}
	
	
	
	
	
	
	
	
	

}
