<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Hero</title>

<!-- Javascript Files -->
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.9/angular.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/danialfarid-angular-file-upload/12.2.13/ng-file-upload.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.13.5/xlsx.full.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.13.5/jszip.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="resource/js/form.js"></script>
<style>
body {
 background-color: #f2f4f6;
/*  background-image: url(resource/images/Captura.PNG); */
}

</style>
</head>
<body>
	<div class="container">
		<br />
		<div align="center" class="card" >
			<br />
			<h1>Bienvenido</h1>
			<h5>Calcula los impuestos a tus productos subiendo un archivo Excel</h5>
			<p>El archivo excel debe contener la siguiente estructura:</p>
			
			<div>
				<img alt="Estructura archivo excel" src="resource/images/Captura.PNG">
			</div>

			<br />
			<div ng-app="MyApp" ng-controller="MyController">
				<div align="center">
				<label class="btn btn-outline-primary" style="margin-top: 5px;">Seleccionar archivo
   					 <input type="file" style="display:none;" ngf-select="SelectFile($file)"/>
 				</label>
<!-- 				<input type="file"  ngf-select="SelectFile($file)" />  -->
					<input type="button" class="btn btn-outline-primary" value="Cargar" ng-click="Upload()" />
				</div>
				<br />
				<br />
				<div align="center" ng-show="IsVisible">
				    <h5>Productos</h5>
				    <br />
					<table id="tblProductos" cellpadding="5" cellspacing="5" ng-show="IsVisible" class="table">
						<thead>
							<tr>
								<th scope="col">Id</th>
								<th scope="col">Nombre</th>
								<th scope="col">Descripcion</th>
								<th scope="col">Precio</th>
								<th scope="col">Cantidad</th>
							</tr>
						</thead>
						<tbody ng-repeat="m in Productos">
							<tr scope="row">
								<td>{{m.id}}</td>
								<td>{{m.nombre}}</td>
								<td>{{m.descripcion}}</td>
								<td>{{m.precio}}</td>
								<td>{{m.cantidad}}</td>
							</tr>
						</tbody>
					</table>
				</div>

				<p>
					<span id="welcomeText" class="cssStyling">{{msgFromServlet}}</span>
				</p>
				
				<br />
				<br />
				<div align="center" ng-show="IsVisible">
				<h5>Productos con iva %16</h5>
				    <br />
					<table id="tblProductosI" cellpadding="5" cellspacing="5" ng-show="IsVisible" class="table">
						<thead>
							<tr>
								<th scope="col">Id</th>
								<th scope="col">Nombre</th>
								<th scope="col">Descripcion</th>
								<th scope="col">Precio</th>
								<th scope="col">Cantidad</th>
								<th scope="col">%iva</th>
								<th scope="col">Precio más iva</th>
							</tr>
						</thead>
						<tbody ng-repeat="m in prod.productosI">
							<tr scope="row">
								<td>{{m.id}}</td>
								<td>{{m.nombre}}</td>
								<td>{{m.descripcion}}</td>
								<td>{{m.precio}}</td>
								<td>{{m.cantidad}}</td>
								<td>{{m.iva}}</td>
								<td>{{m.preciomasiva}}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<br />
				<br />
				<br />
			</div>
		</div>
	</div>
	
	    
</body>   

</html>