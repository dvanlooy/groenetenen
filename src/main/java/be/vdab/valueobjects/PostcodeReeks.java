package be.vdab.valueobjects;

import be.vdab.constraints.Postcode;
import be.vdab.constraints.PostcodeReeksVanKleinerDanOfGelijkAanTot;

@PostcodeReeksVanKleinerDanOfGelijkAanTot
public class PostcodeReeks {
	@Postcode private Integer vanpostcode;
	@Postcode private Integer totpostcode;

	public Integer getVanpostcode() {
		return vanpostcode;
	}

	public Integer getTotpostcode() {
		return totpostcode;
	}

	public void setVanpostcode(Integer vanpostcode) {
		this.vanpostcode = vanpostcode;
	}

	public void setTotpostcode(Integer totpostcode) {
		this.totpostcode = totpostcode;
	}

	public boolean bevat(Integer postcode) {
		// bevat de reeks een bepaalde postcode ? (gebuikt in de repository
		// layer)
		return postcode >= vanpostcode && postcode <= totpostcode;
	}
}