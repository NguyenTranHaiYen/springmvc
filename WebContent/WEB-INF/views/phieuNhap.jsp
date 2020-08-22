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
	<div class="container">
		<div class="row">
			<h1 style="margin-top: 30px">Phiếu Nhập</h1>
			<div class="row">
				<a class="btn btn-success col-6" href="chitietphieunhap.html"><i
					class="fa fa-plus-circle"></i>Thêm Sản Phẩm Cho Phiếu Nhập</a> 
					<a class="btn btn-success"
					href="managephieunhap.html"><i
					style="margin-right: 10px; margin-top: 0px !important"
					class="fa fa-reply"></i>Quay lại</a>
			</div>
		</div>
		<div class="row" style="margin-top:20px">
			<div class="col-xs-3">
							<label>Chọn Nhà Cung Cấp: </label>
			</div>
			<div class="col-xs-7">
			<form:form action="savePhieuNhap.html" method="POST">
							<select class="form-control col-sm-2" name="nhacungcap">
								<c:forEach items="${listncc}" var="item">
									<option value="${item.id}" label="${item.name}" selected="selected"></option>
								</c:forEach>
							</select> 
			<input class="btn btn-success col-4" type="submit" value="Lưu Phiếu Nhập">
			</form:form>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12">
				<br />
				<div class="table-responsive">
					<table id="mytable" class="table table-bordred table-striped">
						<thead>
							<th>Tên Sản Phẩm</th>
							<th>Hình Ảnh</th>
							<th>Số lượng</th>
							<th>Giá Nhập</th>
							<th style="width: 50px;">Delete</th>
						</thead>
						<tbody>
							<c:forEach var="item" items="${listChiTietPhieuNhap}">
								<tr>
									<td>${item.product.name}</td>
									<td><image width="100px" src="${item.product.image}" /></td>
									<td>${item.total}</td>
									<td>${item.price}</td>
									<td><a class="btn btn-success"
										href="deleteChiTiet/${item.product.proId}.html"><i
											class="fa fa-cart-arrow-down"></i></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>
</body>
</html>
