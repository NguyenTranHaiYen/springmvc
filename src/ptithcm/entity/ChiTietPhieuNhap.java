package ptithcm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ChiTietPhieuNhap")
public class ChiTietPhieuNhap {

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ProId")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "PhieuNhapId")
	private PhieuNhap phieuNhap;

	private Integer total;
	
	private Float price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public PhieuNhap getPhieuNhap() {
		return phieuNhap;
	}

	public void setPhieuNhap(PhieuNhap phieuNhap) {
		this.phieuNhap = phieuNhap;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ChiTietPhieuNhap [id=" + id + ", product=" + product + ", phieuNhap=" + phieuNhap + ", total=" + total
				+ "]";
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
