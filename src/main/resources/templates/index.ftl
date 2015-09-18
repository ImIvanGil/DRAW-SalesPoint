<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Sales Point</title>
	<link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">DRAW</a>
				</div>
				<div>
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">Home</a></li>
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
		<div class="jumbotron" align="center">
			<h1>
				<span class="glyphicon glyphicon-shopping-cart"></span>SALES POINT
			</h1>
			<p>Ventas en Linea.</p> 
		</div>
		<!-- Modal Registrar -->
		<div id="registrar" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Registrar</h4>
					</div>
					<form class="form-horizontal" role="form" action="/do_signup" method="POST">
						<div class="form-group">
							<label class="control-label col-sm-2" for="nombre">Nombre:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="nombre" name="name" placeholder="Introduce tu nombre">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="apellido">Apellido:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="apellido" name="last_name" placeholder="Introduce tu apellido">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="nickName">Nick Name:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="nickName" name="nickname" placeholder="Nick name">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">Password:</label>
							<div class="col-sm-10">          
								<input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="re-pwd">Confirm Password:</label>
							<div class="col-sm-10">         
								<input type="password" class="form-control" id="re-pwd" name="confirm_password" placeholder="Confirm password">
							</div>
						</div>
						<div class="form-group">        
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary">Crear</button>
							</div>
						</div>
					</form>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal crear -->
		<div id="crear" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Inicia Session</h4>
					</div>
					<form class="form-horizontal" role="form" action="/do_login" method="POST">
						<div class="form-group">
							<label class="control-label col-sm-2" for="nickName">Nick Name:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="nickname" name="nickname" placeholder="Nick name">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">Password:</label>
							<div class="col-sm-10">     
								<input type="password" class="form-control" name="password" id="pwd" placeholder="Enter password">
							</div>
						</div>
						<div class="form-group">        
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-primary">Iniciar Session</button>
							</div>
						</div>
					</form>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>