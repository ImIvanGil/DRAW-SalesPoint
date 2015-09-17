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
                <a href="/admin">
                    <span class="glyphicon glyphicon-user" ></span> Admin
                </a>
		<#list errors as error>
		<div class="alert alert-warning" role="alert">${error}</div>
		</#list>
		<h2>Mis Compras <button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#crearPedido">Crear pedido</button></h2>
		<p>Tus compras son las siguientes:</p>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Compra</th>
					<th>Fecha</th>
					<th>Estatus</th>
				</tr>
			</thead>
			<tbody>
				<#list orders as order>
				<tr>
					<td>${order.getOrderId()}</td>
					<td>${order.getCreatedAt()}</td>
					<td>${order.getStatus()}</td>
				</tr>
				</#list>
			</tbody>
		</table>
		<div id="crearPedido" class="modal fade" role="dialog">
			<div class="modal-content">
				<h3 name="name">Nuevo pedido</h3>
				<form class="form-horizontal" role="form" action="/create_order" method="POST">
					<div class="form-group">
						<label class="control-label col-sm-2" for="desc">Descripcion:</label>
						<div class="col-sm-10">          
							<textarea  class="form-control" name="description" id="description" placeholder="Descripcion"></textarea>
						</div>
					</div>
					<div class="form-group">        
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-success">Crear</button>
						</div>
					</div>
				</form>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>