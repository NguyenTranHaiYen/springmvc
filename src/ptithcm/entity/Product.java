package ptithcm.entity;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name="Products")
public class Product {
	
	@Id @GeneratedValue
	@Column(name="Id")
	private Integer proId;
	
	private String name;
	
	private Float price;
	
	private Integer discount;
	
	@ManyToOne
	@JoinColumn(name="cateId")
	private Category category;

	private String description;
	
	private String information;
	
	private String image;
	
	private Integer quantity;
	
	private Integer total;

	@OneToMany(mappedBy = "proid",fetch = FetchType.EAGER)
	private Collection<CartItem> cartitem;
	
	@OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
	private Collection<ChiTietPhieuNhap> chiTietPhieuNhap;

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Collection<CartItem> getCartitem() {
		return cartitem;
	}

	public void setCartitem(Collection<CartItem> cartitem) {
		this.cartitem = cartitem;
	}

	public Collection<ChiTietPhieuNhap> getChiTietPhieuNhap() {
		return chiTietPhieuNhap;
	}

	public void setChiTietPhieuNhap(Collection<ChiTietPhieuNhap> chiTietPhieuNhap) {
		this.chiTietPhieuNhap = chiTietPhieuNhap;
	}

}
