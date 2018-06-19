package al.gov.asp.brunoveizaj.casper.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author Bruno
 */
@Entity
@Table(name = "\"PHOTO_CARD\"",schema = "repository")
public class PhotoCard implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "\"ID\"")
    private int id;
    @Column(name = "\"MY_IDC\"")
    private String idc;   
    @Column(name = "\"IDC\"")
    private String myIdc;   
    
   // @Lob
    @Column(name = "\"PHOTO\"")
    //@Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
    private byte[] photo;
    @Column(name = "\"CARD_ID\"")
    private Long cardId;

    public PhotoCard() {
    }

    public PhotoCard(int id) {
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getMyIdc() {
        return myIdc;
    }

    public void setMyIdc(String myIdc) {
        this.myIdc = myIdc;
    }


    
}
