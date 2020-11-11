<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="utf-8">
<head>
    <title>Nguyễn Đức Thành</title>
    <meta charset="utf-8">
    <%@ include file="css.jsp"%>
</head>
<body>
<div id="wrapper">
    <%@include file="menuleft.jsp"%>
    <div id="page-wrapper" class="gray-bg">
        <div class="row">
            <!-- Navbar top -->
            <nav class="navbar navbar-static-top" role="navigation">
                <div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary" href="javascript:void(0);"><i class="fa fa-navicon"></i></a>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li>
                        <a href=""><i class="fa fa-sign-out"></i> Logout</a>
                    </li>
                </ul>
            </nav>
            <!--./ end navbar top -->
        </div>
        <div class="wrapper wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <div class="col-lg-3 pl-5">
                                <ol class="breadcrumb">
                                    <li>
                                        <a href="">Trang chủ</a>
                                    </li>
                                    <li>
                                        <a href="">Danh sách khách hàng</a>
                                    </li>
                                </ol>
                            </div>
                            <div class="col-lg-9">
<%--                                <c:if test='${requestScope["message"] != null}'>--%>
<%--                                    <span class="message111" ><b style="color: red; margin-left: 30%">${requestScope["message"]}</b></span></>--%>
<%--                                </c:if>--%>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="panel panel-default border-square">
                                    <div class="panel-heading border-square">
                                        <div class="row">.
                                            <div class="col-sm-10 text-left">
                                                <form method="get">
                                                    <div class="col-sm-5"></div>
                                                    <div class="col-sm-7">
                                                        <!-- tìm kiếm -->
                                                        <div class="row">
                                                            <div style="width: 1000px;">
                                                                <div style="margin-right: 30px;width: 150px;float: left;">
                                                                </div>
                                                                <div style="margin-right: 30px;width: 150px;float: left;">
                                                                    <select class="form-control select2" name="cat" id="cat" onChange="submit(this.form)">
                                                                        <option value="">Category</option>
                                                                        <c:forEach items='${requestScope["categories"]}' var="categories">
                                                                            <option value="${categories.getCat_id()}" >${categories.getCat_name()}</option>
                                                                        </c:forEach>
<%--                                                                        <c:if test='${ cat == categories.getCat_id() }'>selected</c:if>--%>
                                                                    </select>
                                                                </div>
                                                                <div style="float: left;">
                                                                    <input type="text" name="key"  id="key" class="form-control" value="" placeholder="Nhập từ khóa...">
                                                                </div>
                                                                <div style="padding: 0; margin-left: -35px;float: left;">
                                                                    <button id="button1"  class="btn btn-warning" type="submit" style="padding: 6px 35px;"><i class="fa fa-search"></i></button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <%--                                                            </form>--%>
                                                        <!-- tìm kiếm -->
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="col-sm-2 text-right" style="margin-top: -20px">
                                                <a href="?action=insert" class="btn btn-primary">
                                                    <i class="fa fa-plus-square fa-fw"></i>Thêm mới
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- thông báo -->
                                    <div class="alert alert-info" id="time" style='display:none'>
                                        <!-- thông báo -->
                                        <div class="panel-body">
                                        </div>
                                        <div style="float: right;"></div>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th style="text-align: center">###</th>
                                                <th style="text-align: center">Product Name</th>
                                                <th style="text-align: center">Category</th>
                                                <th style="text-align: center">Price</th>
                                                <th style="text-align: center">Quantity</th>
                                                <th style="text-align: center">Color</th>
                                                <th style="text-align: center">Descrition</th>
                                                <th style="text-align: center">Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach items='${requestScope["products"]}' var="pr">
                                                <tr>
                                                    <td>${pr.getId()}</td>
                                                    <td>${pr.getName()}</td>
                                                    <td>${pr.getCat_name()}</td>
                                                    <td><fmt:formatNumber type="number" maxIntegerDigits="10"  value="${pr.getPrice()} "/></td>
                                                    <td>${pr.getAmount()}</td>
                                                    <td>${pr.getColor()}</td>
                                                    <td>${pr.getDescription()}</td>
                                                    <td>
                                                        <a href="?action=update&id=${pr.getId()}" class="btn">
                                                            <i class="glyphicon glyphicon-edit"></i> Sửa
                                                        </a>
                                                        <a href="?action=delete&cat=&key=&id=${pr.getId()}">
                                                            <i class="glyphicon glyphicon-trash"></i> Xóa
                                                        </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
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
</div>
<%@ include file="js.jsp"%>
</body>
</html>