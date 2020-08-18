package ptithcm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.Cart;
import ptithcm.entity.CartItem;
import ptithcm.entity.ChiTietPhieuNhap;
import ptithcm.entity.PhieuNhap;
import ptithcm.entity.Product;

import ptithcm.entity.User;

@Transactional
@Controller
public class ManageController {

	@Autowired
	SessionFactory factory;

	List<Product> listProduct;

	public List<Product> getListProduct() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Product";
		Query query = session.createQuery(hql);
		listProduct = query.list();
		return listProduct;
	}

	@RequestMapping(value = "manage", method = RequestMethod.GET)
	public String viewLogin(ModelMap mm, HttpSession session1) {
		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}
		mm.put("user", new User());
		return "manage";
	}

	@RequestMapping(value = "manage", method = RequestMethod.POST)
	public String manages(ModelMap model, HttpSession session1) {
		Session session = factory.getCurrentSession();
		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}
		model.put("user", new User());
		return "manage";
	}

	@RequestMapping(value = "manageproduct")
	public String manageProductList(ModelMap model, HttpSession session1) {

		Session session = factory.getCurrentSession();
		model.put("user", new User());

		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}

		Query query = session.createQuery("FROM Product");
		List<Product> list = query.list();
		int totalitems = list.size();
		model.addAttribute("listProduct", list);
		model.addAttribute("totalitems", totalitems);

		return "manageproduct";
	}

	@RequestMapping(value = "insert", method = RequestMethod.GET)
	public String insert(ModelMap model, HttpSession session1) {
		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}
		model.addAttribute("product", new Product());
		return "manageproduct_form";
	}

	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("product") Product product, HttpSession session1) {
		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.saveOrUpdate(product);
			t.commit();
			model.addAttribute("message", "Thêm thành công !");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm thất bại !");
		} finally {
			session.close();
		}
		return "manageproduct_form";
	}

	@RequestMapping("{proId}")
	public String edit(ModelMap model, @PathVariable("proId") int proId, HttpSession session1) {

		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}

		Session session = factory.getCurrentSession();
		Product product = (Product) session.get(Product.class, proId);
		model.addAttribute("product", product);
		// model.addAttribute("users", getUsers());
		return "manageproduct_update";
	}

	@RequestMapping(value = "managecart")
	public String managecart(ModelMap model, HttpSession session1) {

		Session session = factory.getCurrentSession();
		model.put("user", new User());

		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}

		Query query = session.createQuery("FROM Cart");
		List<Product> listCart = query.list();
		int totalitems = listCart.size();
		model.addAttribute("listCart", listCart);
		model.addAttribute("totalitems", totalitems);

		return "manage_cart";
	}

	@RequestMapping(value = "cart/{cartid}")
	public String chart(ModelMap model, @PathVariable("cartid") int cartid, HttpSession session1) {

		model.put("user", new User());

		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}
		int id = cartid;
		Session session = factory.getCurrentSession();
		String hql = "FROM Cart WHERE cartId = '" + cartid + "'";
		Query query = session.createQuery(hql);
		Cart h = (Cart) query.uniqueResult();
		int isPaid = h.getIsPaid();
		model.addAttribute("isPaid", isPaid);
		model.addAttribute("id", id);

		Session session2 = factory.getCurrentSession();
		String hql2 = "FROM CartItem WHERE cartid.cartId = '" + id + "'";
		Query query2 = session2.createQuery(hql2);
		List<CartItem> listItem = query2.list();
		model.addAttribute("listItem", listItem);

		float total = 0;
		for (CartItem number : listItem) {
			float discount = (float) number.getProid().getDiscount() / 100;
			System.out.println(discount);
			float totaldiscount = number.getProid().getPrice() * discount;
			System.out.println(totaldiscount);
			float money = number.getProid().getPrice() - totaldiscount;
			System.out.println(money);
			total += money * number.getQuantity();
			System.out.println(total);
		}
		model.addAttribute("total", total);

		return "chart";
	}

	@RequestMapping(value = "accept/{cartid}")
	public String accept(ModelMap model, @PathVariable("cartid") int cartid, HttpSession session1) {

		model.put("user", new User());

		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}

		Session session = factory.getCurrentSession();
		String hql = "FROM Cart WHERE cartId = '" + cartid + "'";
		Query query = session.createQuery(hql);
		Cart h = (Cart) query.uniqueResult();
		h.setIsPaid(3);
		User userId = h.getUserid();

		Cart cart = new Cart();
		cart.setUserid(userId);
		cart.setIsPaid(0);
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		cart.setBuyDate(date);
		session.save(cart);
		session.saveOrUpdate(h);

		return "redirect:/cart/{cartid}.html";
	}

	@RequestMapping(value = "finish/{id}")
	public String finish(ModelMap model, @PathVariable("id") int id, HttpSession session1) {

		model.put("user", new User());

		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}

		Session session = factory.getCurrentSession();
		String hql = "FROM Cart WHERE cartId = '" + id + "'";
		Query query = session.createQuery(hql);
		Cart h = (Cart) query.uniqueResult();
		h.setIsPaid(1);
		session.saveOrUpdate(h);

		return "redirect:/cart/{id}.html";
	}

	@RequestMapping(value = "/managephieunhap")
	public String quanliPhieuNhap(ModelMap model, HttpSession session1) {
		Session session = factory.getCurrentSession();
		model.put("user", new User());

		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}

		Query query = session.createQuery("FROM PhieuNhap");
		List<PhieuNhap> listPhieuNhap = query.list();
		int totalitems = listPhieuNhap.size();
		model.addAttribute("listPhieuNhap", listPhieuNhap);
		return "managePhieuNhap";
	}

	@RequestMapping(value = "phieuNhap/{id}")
	public String xemPhieuNhap(ModelMap model, @PathVariable("id") int id, HttpSession session1) {
		model.put("user", new User());
		model.put("id", id);
		String username = (String) session1.getAttribute("username");
		int role = (Integer) session1.getAttribute("roleid");
		if (username == null || role != 1) {
			return "error";
		}

		Session session = factory.getCurrentSession();
		String hql = "FROM ChiTietPhieuNhap WHERE phieuNhap.id = '" + id + "'";
		Query query = session.createQuery(hql);
		List<ChiTietPhieuNhap> list = query.list();
		model.addAttribute("list", list);
		return "xemPhieuNhap";
	}

	@RequestMapping(value = "/managechitietphieunhap")
	public String quanliChiTiet(ModelMap model, HttpSession session1) {
		if (session1.getAttribute("phieuNhap") == null) {
			PhieuNhap phieuNhap = new PhieuNhap();
			session1.setAttribute("phieuNhap", phieuNhap);
		}
		PhieuNhap pn = (PhieuNhap) session1.getAttribute("phieuNhap");
		List<ChiTietPhieuNhap> list = (List<ChiTietPhieuNhap>) pn.getChiTietPhieuNhap();
		if (list == null) {
			model.addAttribute("listChiTietPhieuNhap", list);

			return "phieuNhap";
		}
		Session session = factory.getCurrentSession();

		for (int i = 0; i < list.size(); i++) {
			String hql1 = "FROM Product WHERE proId= '" + list.get(i).getProduct().getProId() + "'";
			Query query1 = session.createQuery(hql1);
			Product x = (Product) query1.uniqueResult();
			list.get(i).setProduct(x);
		}
		model.addAttribute("listChiTietPhieuNhap", list);

		return "phieuNhap";
	}

	@RequestMapping(value = "/chitietphieunhap", method = RequestMethod.GET)
	public String createChiTietPhieunhap(ModelMap model) {

		model.put("chiTietPhieuNhap", new ChiTietPhieuNhap());

		Session session = factory.getCurrentSession();
		Query query = session.createQuery("FROM Product");
		List<Product> listPro = query.list();
		model.addAttribute("listPro", listPro);
		return "ChiTietPhieuNhap";
	}

	@RequestMapping(value = "/chitietphieunhap", method = RequestMethod.POST)
	public String createChiTietPhieunhap(ModelMap model,
			@ModelAttribute("chiTietPhieuNhap") ChiTietPhieuNhap chiTietPhieuNhap, HttpSession session1) {

		PhieuNhap pn = (PhieuNhap) session1.getAttribute("phieuNhap");
		List<ChiTietPhieuNhap> list = (List<ChiTietPhieuNhap>) pn.getChiTietPhieuNhap();

		if (list == null) {
			List<ChiTietPhieuNhap> l = new ArrayList<ChiTietPhieuNhap>();
			l.add(chiTietPhieuNhap);
			pn.setChiTietPhieuNhap(l);
		} else {
			int proid = chiTietPhieuNhap.getProduct().getProId();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getProduct().getProId() == proid) {
					list.get(i).setTotal(list.get(i).getTotal() + chiTietPhieuNhap.getTotal());
					pn.setChiTietPhieuNhap(list);
					return "redirect:/managechitietphieunhap.html";
				}
			}
			list.add(chiTietPhieuNhap);
			pn.setChiTietPhieuNhap(list);
		}
		session1.setAttribute("phieuNhap", pn);
		return "redirect:/managechitietphieunhap.html";
	}

	@RequestMapping(value = "deleteChiTiet/{proId}")
	public String deleteChiTiet(ModelMap model, @PathVariable("proId") int proId, HttpSession session1) {

		PhieuNhap pn = (PhieuNhap) session1.getAttribute("phieuNhap");
		List<ChiTietPhieuNhap> list = (List<ChiTietPhieuNhap>) pn.getChiTietPhieuNhap();

		list.removeIf(element -> element.getProduct().getProId() == proId);
		pn.setChiTietPhieuNhap(list);
		session1.setAttribute("phieuNhap", pn);
		model.put("user", new User());
		return "redirect:/managechitietphieunhap.html";
	}

	@RequestMapping(value = "/savePhieuNhap")
	public String savePhieuNhap(ModelMap model, HttpSession session1) throws ParseException {

		PhieuNhap pn = (PhieuNhap) session1.getAttribute("phieuNhap");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String datetime = formatter.format(date);
		Date date1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(datetime);
		pn.setDate(date1);
		List<ChiTietPhieuNhap> list = (List<ChiTietPhieuNhap>) pn.getChiTietPhieuNhap();
		List<ChiTietPhieuNhap> l= new ArrayList<ChiTietPhieuNhap>();
		for (int i=0;i<list.size();i++) {
			
			ChiTietPhieuNhap ctpn= new ChiTietPhieuNhap();
			
			PhieuNhap phieu= new PhieuNhap();
			phieu.setId(list.get(i).getPhieuNhap().getId());
			ctpn.setPhieuNhap(phieu);
			
			Product product= new Product();
			product.setProId(list.get(i).getProduct().getProId());
			ctpn.setProduct(product);
			
			ctpn.setTotal(list.get(i).getTotal());
			
			l.set(i, ctpn);
		}
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.saveOrUpdate(pn);// cái này chạy được => cái này insert phiếu nhập nè cậu
			for (ChiTietPhieuNhap ct : l) {
				session.saveOrUpdate(ct); //cái này không. cái này là insert list chi tiết phiếu nhập nằm trong cái session phiếu nhập
			} // mà cái list chi tiết phiếu nhập mình render lên giao diện được rồi nè. xem demo nè
			t.commit(); // mà giờ nhấn lưu phiếu nhập thì nó k insert đc 
			model.addAttribute("message", "Thêm thành công !");
		} catch (Exception e) {
			t.rollback();
		} finally {
			session.close();
		}
		System.out.println("helooooooooo");
		return "redirect:/managechitietphieunhap.html";
	}

}