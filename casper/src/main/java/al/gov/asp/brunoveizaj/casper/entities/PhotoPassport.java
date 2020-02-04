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
@Table(name = "\"PHOTO_PASSPORT\"",schema = "repository")
public class PhotoPassport implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "\"ID\"")
    private int id;
    @Column(name = "\"MY_IDP\"")
    private String idp;   
    @Column(name = "\"IDP\"")
    private String myIdp;   
  //  @Lob
    @Column(name = "\"PHOTO\"")
    private byte[] photo;
    @Column(name = "\"PASSPORT_ID\"")
    private Long passportId;

    public PhotoPassport() {
    }

    public PhotoPassport(int id) {
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    public String getMyIdp() {
        return myIdp;
    }

    public void setMyIdp(String myIdp) {
        this.myIdp = myIdp;
    }

    
    
    
}
