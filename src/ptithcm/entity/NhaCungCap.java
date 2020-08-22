package ptithcm.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NhaCungCap")	
public class NhaCungCap {

	@Id
	@GeneratedValue
	@Column(name = "Id")
	private Integer id;
	
	private String name;
	
	private String phone;
	
	private String address;
	
	@OneToMany(mappedBy = "nhaCungCap",fetch = FetchType.EAGER)
	private Collection<PhieuNhap> phieuNhap;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Collection<PhieuNhap> getPhieuNhap() {
		return phieuNhap;
	}

	public void setPhieuNhap(Collection<PhieuNhap> phieuNhap) {
		this.phieuNhap = phieuNhap;
	}
	
}
