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
                                    <button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#liberarPedido">
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
				<div class="modal-content">
					<a href="/do_status/id/status">Liberar</a>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>