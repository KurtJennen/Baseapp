package be.luxuryoverdosis.framework.data.dto;

public class TokenHeaderDTO extends BaseDTO {
	private String alg;
	private String typ;
	private String kid;
	
	public TokenHeaderDTO() {
		super();
	}

	public String getAlg() {
		return alg;
	}
	public void setAlg(String alg) {
		this.alg = alg;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getKid() {
		return kid;
	}
	public void setKid(String kid) {
		this.kid = kid;
	}
	
}