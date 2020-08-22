<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile List</title>
<base href="${pageContext.servletContext.contextPath}/">
<!-- css -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />

<!--js-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/font-awesome.css" rel="stylesheet">
<link href="css/easy-responsive-tabs.css" rel='stylesheet'
	type='text/css' />
<link
	href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800"
	rel="stylesheet">
<link
	href='//fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,900,900italic,700italic'
	rel='stylesheet' type='text/css'>
<link href="css/style2.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
	<div class="page-container">
		<div class="left-content">
			<div class="mother-grid-inner">
				<div class="container">

					<div class="row">
						<div class="col-md-12">
							<br />
							<div class="table-responsive">
								<div class="row">
									<div class="col col-xs-12">
										<h1 style="margin-top: 30px">Chi Tiết Phiếu Nhập</h1>
										<h4 style="margin-top: 10px; margin-bottom: 15px">Mã
											Phiếu Nhập: ${id}</h4>
										<a class="btn btn-success" href="managephieunhap.html" style="margin-top: 0px!important;  margin-bottom: 25px"><i style="margin-right: 10px"
											class="fa fa-reply"></i>Quay lại</a>
									</div>
								</div>
								<div class="clearfix"></div>
								<div class="row">
									<div class="col-md-12">
										<div class="table-responsive">
											<table id="mytable" class="table table-bordred table-striped">
												<thead>
													<th>Tên Sản Phẩm</th>
													<th>Hình Ảnh</th>
													<th>Số lượng</th>
												</thead>
												<tbody>
													<c:forEach var="item" items="${list}">
														<tr>
															<td>${item.product.name}</td>
															<td><image width="100px" src="${item.product.image}" /></td>
															<td>${item.total}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--slider menu-->
	<div class="sidebar-menu" style="height: 100%">
		<div class="menu">
			<ul id="menu">
				<li id="menu-home"><a href="manage.html"><i
						class="fa fa-home"></i><span>Home</span></a></li>

				<li id="menu-comunicacao"><a href="managephieunhap.html"><i
						class="fa fa-book nav_icon"></i><span>Quản Lí Phiếu Nhập</span></a></li>

				<li id="menu-comunicacao"><a href="manageproduct.html"><i
						class="fa fa-book nav_icon"></i><span>Quản Lí Sản Phẩm</span></a></li>

				<li><a href="managecart.html"><i
						class="fa fa-shopping-cart"></i><span>Quản Lí Giỏ Hàng</span></span></a></li>
			</ul>
		</div>
	</div>
	<div class="clearfix"></div>
</body>
</html>
