<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Admin</title>
	<title>	Sales Point</title>
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
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
      <tr>
        <td>John</td>
        <td>Doe</td>
        <td>john@example.com</td>
        <td>
        	<!-- Trigger the modal with a button -->
			<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#liberarPedido">Open Modal</button>
        </td>
      </tr>
    </tbody>
  </table>
</div>

<!-- Modal crear -->
<div id="liberarPedido" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
    <label for="name"></label>
    	<h3 name="name">Liberar pedido</h3><hr>
    	<form class="form-horizontal" role="form" action="/do-status" method="POST">
		    <p align="center">Liberar Compra?</p>
		    <div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10" align="right">
		        <button type="submit"  class="btn btn-danger">Liberar</button>
		      </div>
		    </div>
		 </form>
       <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>