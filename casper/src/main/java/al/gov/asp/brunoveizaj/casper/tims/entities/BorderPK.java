package al.gov.asp.brunoveizaj.casper.tims.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class BorderPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Column(name="TRAVEL_DOC_NO")
	String docNo;
	@Column(name="DOC_STATE")
	String docState;
	@Column(name="RECORD_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	Date crossingDate;
	
	
	public BorderPK() {}
	
	
	public BorderPK(String docNo, String docState, Date crossingDate) {
		super();
		this.docNo = docNo;
		this.docState = docState;
		this.crossingDate = crossingDate;
	}
	
	
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getDocState() {
		return docState;
	}
	public void setDocState(String docState) {
		this.docState = docState;
	}
	public Date getCrossingDate() {
		return crossingDate;
	}
	public void setCrossingDate(Date crossingDate) {
		this.crossingDate = crossingDate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crossingDate == null) ? 0 : crossingDate.hashCode());
		result = prime * result + ((docNo == null) ? 0 : docNo.hashCode());
		result = prime * result + ((docState == null) ? 0 : docState.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BorderPK other = (BorderPK) obj;
		if (crossingDate == null) {
			if (other.crossingDate != null)
				return false;
		} else if (!crossingDate.equals(other.crossingDate))
			return false;
		if (docNo == null) {
			if (other.docNo != null)
				return false;
		} else if (!docNo.equals(other.docNo))
			return false;
		if (docState == null) {
			if (other.docState != null)
				return false;
		} else if (!docState.equals(other.docState))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "BorderPK [docNo=" + docNo + ", docState=" + docState + ", crossingDate=" + crossingDate + "]";
	}
	
	
	
	
	
	

}
