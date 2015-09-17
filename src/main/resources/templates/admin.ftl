<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Sales Point</title>
	<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
	
	<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">DRAW</a>
				</div>
				<div>
					<ul class="nav navbar-nav">
						<li class="active"><a href="/orders">Orders</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
                                                <#if user??>
                                                <li>
							<a href="/do_logout">
								<span class="glyphicon glyphicon-log-out" ></span> Salir
							</a>
						</li>
                                                    <#if user.getIsAdmin()>
                                                    <li>
                                                        <a href="/admin">
                                                                <span class="glyphicon glyphicon-user" ></span> Admin
                                                        </a>
                                                    </li>
                                                    </#if>
                                                <#else>
                                                <li>
							<a data-toggle="modal" data-target="#registrar">
								<span class="glyphicon glyphicon-user" ></span> Registrate
							</a>
						</li>
						<li>
							<a data-toggle="modal" data-target="#crear">
								<span class="glyphicon glyphicon-log-in"></span> Inicia Session
							</a>
						</li>
                                                </#if>
						
					</ul>
				</div>
			</div>
		</nav>
	
		
		<#list errors as error>
		<div class="alert alert-warning" role="alert">${error}</div>
		</#list>
		<h2>Compras</h2>
		<div>Compras totales:</div>            
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Nick Name</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
	        <#list orders as order>
	            <tr>
	                <td>${order.getUser().getName()}</td>
	                <td>${order.getUser().getLastName()}</td>
	                <td>${order.getUser().getNickname()}</td>
	                <td>${order.getStatus()}</td>
	                <td>
	                    <button type="button" class="btn btn-info btn-xs" onclick="load(${order.getOrderId()})">
	                        Ver
	                    </button>
	                </td>
	            </tr>
	        </#list>
			</tbody>
		</table>

		<!-- Modal crear -->
		<div id="liberarPedido" class="modal fade" role="dialog">
			<div class="modal-dialog">
                <!-- Modal content-->
                <div id="order" class="modal-content " style="padding:1cm">
                    <h3 align="center">Creado el: <b> <div id="order_date"></div></b></h3> <hr>
                    Id: <b><div id="order_number"></div></b>
                    Nombre cliente: <b><div id="order_user"></div></b>
                    Descripcion: <b><div id="order_description"></div></b><br>
                    <a id="order_status" class="btn btn-primary" href="#">Liberar</a>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
        <script>
            function load(orderId) {
                $.ajax({
                    // En data puedes utilizar un objeto JSON, un array o un query string
                    data: {},
                    //Cambiar a type: POST si necesario
                    type: "GET",
                    // Formato de datos que se espera en la respuesta
                    dataType: "json",
                    // URL a la que se enviará la solicitud Ajax
                    url: "/order/" + orderId,
		}).done(function(order, textStatus, jqXHR) {
                    $("#order_date").html(order.createdAt);
                    $("#order_number").html(order.orderId);
                    $("#order_user").html(order.user.name);
                    $("#order_description").html(order.description);
                    $("#order_status").attr("href", "/do_status/" + order.orderId + "/LIBERAR");
                    $('#liberarPedido').modal('show');
		});
            }
        </script>
</body>
</html>