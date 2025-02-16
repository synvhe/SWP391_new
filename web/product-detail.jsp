<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Chi Tiết Sản Phẩm - Organic</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .price {
                font-size: 1.5rem;
                font-weight: bold;
                color: green;
            }
            .old-price {
                font-size: 1.2rem;
                color: gray;
                text-decoration: line-through;
                margin-right: 10px;
            }
            .discount-badge {
                font-size: 1rem;
                color: white;
                background-color: red;
                border-radius: 5px;
                padding: 2px 5px;
                margin-left: 10px;
            }


        </style>
    </head>
    <body>

        <div class="container my-4">
            <div class="row">

                <div class="col-md-6">

                    <div class="main-image mb-3">
                        <img src="${images[0].url}" alt="${product.name}" id="mainImage" class="img-fluid rounded">
                    </div>




                    <div class="image-gallery d-flex flex-wrap gap-2">
                        <c:forEach var="image" items="${images}">
                            <img 
                                src="${image.url}" 
                                alt="${image.altText}" 
                                class="img-thumbnail" 
                                style="width: 100px; height: 100px; cursor: pointer;" 
                                onclick="changeMainImage('${image.url}')">
                        </c:forEach>
                    </div>
                </div>




                <div class="col-md-6">
                    <h1 class="section-title">${product.name}</h1>
                    <p class="text-muted">
                        Thương hiệu: 
                        <c:choose>
                            <c:when test="${supplier != null}">
                                <span class="text-primary fw-bold">${supplier.name}</span>
                            </c:when>
                            <c:otherwise>
                                <span class="text-secondary">Không xác định</span>
                            </c:otherwise>
                        </c:choose>
                        | Tình trạng: 
                        <span class="text-success">
                            <c:choose>
                                <c:when test="${product.quantity > 0}">
                                    Còn hàng
                                </c:when>
                                <c:otherwise>
                                    Hết hàng
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </p>




                    <p class="text-muted">
                        Trọng lượng: 
                        <c:choose>
                            <c:when test="${product.weight != null}">
                                ${product.weight} 
                                <c:choose>
                                    <c:when test="${units != null}">
                                        ${units.name}
                                    </c:when>
                                    <c:otherwise>
                                        (Đơn vị không xác định)
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                            <c:otherwise>
                                Không xác định
                            </c:otherwise>
                        </c:choose>
                    </p>



                    <div class="price-details my-3">
                        <c:choose>
                            <c:when test="${product.discount > 0}">
                                <span class="old-price">
                                    <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" />đ
                                </span>
                                <span class="price">
                                    <fmt:formatNumber value="${product.price - (product.price * product.discount / 100)}" type="number" groupingUsed="true" />đ
                                </span>
                                <span class="discount-badge">-${product.discount}%</span>
                            </c:when>
                            <c:otherwise>
                                <span class="price">
                                    <fmt:formatNumber value="${product.price}" type="number" groupingUsed="true" />đ
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </div>


                    <div class="input-group my-3 w-50">
                        <button class="btn btn-outline-secondary" type="button" onclick="updateQuantity(-1)">-</button>
                        <input type="number" class="form-control text-center" id="quantity" value="1" min="1">
                        <button class="btn btn-outline-secondary" type="button" onclick="updateQuantity(1)">+</button>
                    </div>



                    <div class="d-flex gap-2">
                        <button class="btn btn-success rounded-1 px-4">Thêm Vào Giỏ Hàng</button>
                        <button class="btn btn-primary rounded-1 px-4">Thanh Toán</button>
                    </div>


                    <div class="mt-4 border rounded p-3 bg-light">
                        <h5>Duy nhất tại Organic Shop</h5>
                        <ul class="list-unstyled">
                            <li><i class="bi bi-truck"></i> Free ship 5km cho đơn hàng từ 499k</li>
                            <li><i class="bi bi-clock"></i> Giao hàng trong 2h</li>   
                            <li><i class="bi bi-heart"></i> Thực phẩm hữu cơ tốt cho sức khỏe</li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="mt-5">
                <h4 class="section-title">Mô tả sản phẩm</h4>
                <p>${product.description}</p>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
        <script>
                            function changeMainImage(imageUrl) {
                                document.getElementById('mainImage').src = imageUrl;
                            }

                            function updateQuantity(amount) {
                                var quantityInput = document.getElementById('quantity');
                                var currentQuantity = parseInt(quantityInput.value);
                                var newQuantity = currentQuantity + amount;
                                if (newQuantity > 0) {
                                    quantityInput.value = newQuantity;
                                }
                            }
        </script>
    </body>
</html>
