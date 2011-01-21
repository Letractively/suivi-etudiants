package absence;


import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Coupon
 *
 */
@Entity

public class Coupon implements Serializable {

	   
	@Id
	@Column(name = "id_coupon")
	private Integer idCoupon;
	private String motif;
	private String justificatif;
	private static final long serialVersionUID = 1L;

	public Coupon() {
		super();
	}   
	public Integer getIdCoupon() {
		return this.idCoupon;
	}

	public void setIdCoupon(Integer idCoupon) {
		this.idCoupon = idCoupon;
	}   
	public String getMotif() {
		return this.motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}   
	public String getJustificatif() {
		return this.justificatif;
	}

	public void setJustificatif(String justificatif) {
		this.justificatif = justificatif;
	}
   
}