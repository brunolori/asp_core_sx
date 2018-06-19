package al.gov.asp.brunoveizaj.casper.hl;

import java.io.Serializable;
import java.util.List;


public class BorderList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	List<BorderDTO> borders;


	public List<BorderDTO> getBorders() {
		return borders;
	}


	public void setBorders(List<BorderDTO> borders) {
		this.borders = borders;
	}
	
	
	
	
}
