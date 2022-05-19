<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Servicio RESTFul</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.0/css/dataTables.bootstrap5.min.css"/>
	</head>
<body>
	<div class="col-lg-8 mx-auto p-3 py-md-5">
    <header class="d-flex align-items-center pb-3 mb-5 border-bottom">
        <a href="/" class="d-flex align-items-center text-dark text-decoration-none">
            <span class="fs-4"><b>Servicio REST con JAX-RS</b> - Cursos y Alumnos<span>
        </a>
    </header>

    <main>
        <h1>Diccionario de Servicios</h1>
        <br>

        <div class="row g-5">
            <div class="col-md-12 col-lg-12">
                <table id="dtBasicExample" class="datatable table table-bordered table-hover">
				 	<thead class="">
				 		<tr>
				 			<th scope="col">M&eacute;todo</th>
				 			<th scope="col">URI</th>
				 			<th scope="col">Descripci&oacute;n</th>
				 			<th scope="col">Params</th>
				 			<th scope="col">Tipo</th>
				 		</tr>
				 	</thead>
				 	<tbody>
				 		<tr>
				 			<th scope="row"><div class="text-center"><span class="badge bg-success">GET</span></div></th>
				 			<td><code>{ip}:{puerto}/JerseyRestWs/rest/curso</code></td>
				 			<td width="20%">Lista todos los cursos</td>
				 			<td width="15%">
				 				<code style="color: black;">
				 					N/A
				 				</code>
				 			</td>
				 			<td width="15%">-</td>
				 		</tr>
				 		<tr>
				 			<th scope="row"><div class="text-center"><span class="badge bg-success">GET</span></div></th>
				 			<td><code>{ip}:{puerto}/JerseyRestWs/rest/curso/{id}</code></td>
				 			<td width="20%">Obtener informaci&oacute;n de un curso espec&iacute;fico</td>
				 			<td width="15%">
				 				<code style="color: black;">
				 					id
				 				</code>
				 			</td>
				 			<td width="15%">PathParam</td>
				 		</tr>
				 		<tr>
				 			<th scope="row"><div class="text-center"><span class="badge bg-primary">POST</span></div></th>
				 			<td><code>{ip}:{puerto}/JerseyRestWs/rest/curso</code></td>
				 			<td width="20%">Crear un nuevo Curso</td>
				 			<td width="15%">
				 				<code style="color: black;">
				 					nombreCurso:"" <br>
				 					nombreProfesor:""<br>
				 					numeroTelefono:""<br>
				 					email:""
				 				</code>
				 			</td>
				 			<td width="15%">x-www-form-urlencoded</td>
				 		</tr>
				 		<tr>
				 			<th scope="row"><div class="text-center"><span class="badge" style="background-color: #7952B3">PUT</span></div></th>
				 			<td><code>{ip}:{puerto}/JerseyRestWs/rest/curso/{id}</code></td>
				 			<td width="20%">Actualizar datos de un curso</td>
				 			<td width="15%">
				 				<code style="color: black;">
				 					nombreCurso:""<br>
				 					nombreProfesor:""<br>
				 					numeroTelefono:""<br>
				 					email:""
				 				</code>
				 			</td>
				 			<td width="15%">x-www-form-urlencoded</td>
				 		</tr>
				 		<tr>
				 			<th scope="row"><div class="text-center"><span class="badge bg-danger">DELETE</span></div></th>
				 			<td><code>{ip}:{puerto}/JerseyRestWs/rest/curso/{id}</code></td>
				 			<td width="20%">Eliminar curso</td>
				 			<td width="15%">
				 				<code style="color: black;">
				 					id
				 				</code>
				 			</td>
				 			<td width="15%">PathParam</td>
				 		</tr>
				 		<tr>
				 			<th scope="row"><div class="text-center"><span class="badge bg-success">GET</span></div></th>
				 			<td><code>{ip}:{puerto}/JerseyRestWs/rest/curso/{id}/alumnos</code></td>
				 			<td width="20%">Listar alumnos pertenecientes a un curso</td>
				 			<td width="15%">
				 				<code style="color: black;">
				 					id
				 				</code>
				 			</td>
				 			<td width="15%">PathParam</td>
				 		</tr>
				 		<tr>
				 			<th scope="row"><div class="text-center"><span class="badge bg-success">GET</span></div></th>
				 			<td><code>{ip}:{puerto}/JerseyRestWs/rest/curso/{id}/alumnos/{idAlumno}</code></td>
				 			<td width="20%">Obtener un alumno filtrado por el curso al que pertenece</td>
				 			<td width="15%">
				 				<code style="color: black;">
				 					id, idAlumno
				 				</code>
				 			</td>
				 			<td width="15%">PathParam</td>
				 		</tr>
				 		<tr>
				 			<th scope="row"  class="align-middle"><div class="text-center"><span class="badge bg-primary">POST</span></div></th>
				 			<td  class="align-middle"><code>{ip}:{puerto}/JerseyRestWs/rest/curso/{id}/alumnos</code></td>
				 			<td width="20%" class="align-middle">Crear alumno asociandolo a un curso en espec&iacute;fico</td>
				 			<td width="15%"  class="align-middle">
				 				<code style="color: black;">
				 					{id}
				 				</code>
				 				<hr>
				 				<code style="color: black;">
				 					nombreAlumno:""
									fechaNacimiento:"YYYY-MM-dd"
									numeroTelefono:""
									email:""
				 				</code>
				 			</td>
				 			<td width="15%" class="align-middle">
				 			PathParam;<br>
				 			x-www-form-urlencoded</td>
				 		</tr>
				 		<tr>
				 			<th scope="row"  class="align-middle"><div class="text-center"><span class="badge" style="background-color: #7952B3">PUT</span></div></th>
				 			<td  class="align-middle"><code>{ip}:{puerto}/JerseyRestWs/rest/curso/{id}/alumnos/{idAlumno}</code></td>
				 			<td width="20%" class="align-middle">Modificar datos de un alumno perteneciente a un curso espec&iacute;fico</td>
				 			<td width="15%"  class="align-middle">
				 				<code style="color: black;">
				 					id; idAlumno
				 				</code>
				 				<hr>
				 				<code style="color: black;">
				 					nombreAlumno:""
									fechaNacimiento:"YYYY-MM-dd"
									numeroTelefono:""
									email:""
				 				</code>
				 			</td>
				 			<td width="15%" class="align-middle">
				 			PathParam;<br>
				 			PathParam;<br>
				 			x-www-form-urlencoded</td>
				 		</tr>
				 		
				 		<tr>
				 			<th scope="row"  class="align-middle"><div class="text-center"><span class="badge bg-danger">DELETE</span></div></th>
				 			<td  class="align-middle"><code>{ip}:{puerto}/JerseyRestWs/rest/curso/{id}/alumnos/{idAlumno}</code></td>
				 			<td width="20%" class="align-middle">Eliminar alumno perteneciente a un curso espec&iacute;fico</td>
				 			<td width="15%"  class="align-middle">
				 				<code style="color: black;">
				 					id; idAlumno
				 				</code>
				 				<hr>
				 				<code style="color: black;">
				 					nombreAlumno:""
									fechaNacimiento:"YYYY-MM-dd"
									numeroTelefono:""
									email:""
				 				</code>
				 			</td>
				 			<td width="15%" class="align-middle">
				 			PathParam;<br>
				 			PathParam;<br>
				 			x-www-form-urlencoded</td>
				 		</tr>
				 	</tbody>
				</table>
            </div>
        </div>
    </main>
    <footer class="pt-5 my-5 text-muted border-top">
        Desarrollado por @angelicacruz  &copy; 2022 UDB
    </footer>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.11.0/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="https://cdn.datatables.net/1.11.0/js/dataTables.bootstrap5.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#dtBasicExample").DataTable({
				'searching': false,
				'lengthMenu': [ 5, 10, 15],
				'ordering': false,
				'pageLength': 5
			});
		})
	</script>
</body>
</html>