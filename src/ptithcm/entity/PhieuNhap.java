package ptithcm.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PhieuNhap")	
public class PhieuNhap {

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Integer id;
	
	private Date date;	
	
	@OneToMany(mappedBy = "phieuNhap",fetch = FetchType.EAGER)
	private Collection<ChiTietPhieuNhap> chiTietPhieuNhap;
	
	@ManyToOne
	@JoinColumn(name = "IdNhaCungCap")
	private NhaCungCap nhaCungCap;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date string) {
		this.date = string;
	}

	public Collection<ChiTietPhieuNhap> getChiTietPhieuNhap() {
		return chiTietPhieuNhap;
	}

	public void setChiTietPhieuNhap(Collection<ChiTietPhieuNhap> chiTietPhieuNhap) {
		this.chiTietPhieuNhap = chiTietPhieuNhap;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}
	
}
