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

</head>
<body>

	<div class="container">

		<div class="row">
			<div class="col-md-12">
				<br />
				<div class="table-responsive">
					<div class="row">
						<div class="col col-xs-12">
							<h1 style="margin-top: 30px">Chi Tiết Phiếu Nhập </h1>
							<h4 style="margin-top: 30px">Mã Phiếu Nhập: ${id}</h4>
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

</body>
</html>
