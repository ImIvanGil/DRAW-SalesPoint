<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>User</title>
	<title>	Sales Point</title>
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h2>Mis Compras</h2>
  <p>Tus compras son las siguientes:</p>            
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
			<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#crearPedido">Open Modal</button>
        </td>
      </tr>
    </tbody>
  </table>
</div>

<!-- Modal crear -->
<div id="crearPedido" class="modal fade" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
    <label for="name"></label>
    	<h3 name="name">Nuevo pedido</h3><hr>
                <form class="form-horizontal" role="form" action="/create-order" method="POST">
		    <div class="form-group">
		      <label class="control-label col-sm-2" for="desc">Descripcion:</label><br>
		      <div class="col-sm-10">          
		        <textarea  class="form-control" name="desc" id="description" placeholder="Descripcion"></textarea>
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
</div>

</body>
</html>