<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Sales Point</title>
	<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
	<div class="container-fluid">
                <a href="/do_logout">
                    <span class="glyphicon glyphicon-log-out" ></span> Salir
                </a>
		<#list errors as error>
		<div class="alert alert-warning" role="alert">${error}</div>
		</#list>
		<h2>Compras</h2>
		<p>Compras totales:</p>            
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellido</th>
						<th>Nick Name</th>
					</tr>
				</thead>
			<tbody>
                            <#list orders as order>
                            <tr>
                                <td>${order.getUser().getName()}</td>
                                <td>${order.getUser().getLastName()}</td>
                                <td>${order.getUser().getNickname()}</td>
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
                            <div id="order" class="modal-content">
                                <h2 id="order_date"></h2>
                                <p id="order_number"></p>
                                <p id="order_user"></p>
                                <p id="order_description"></p>
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