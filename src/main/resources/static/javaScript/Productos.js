let filaEditando = null;

function guardarProducto() {

    const nombre = document.getElementById("nombre").value;
    const categoria = document.getElementById("categoria").value;
    const precio = document.getElementById("precio").value;
    const stock = document.getElementById("stock").value;

    const tabla = document.getElementById("tablaProductos");

    if (
        nombre.trim() === "" ||
        categoria.trim() === "" ||
        precio.trim() === "" ||
        stock.trim() === ""
    ) {
        alert("Complete todos los campos");
        return;
    }


    if (filaEditando) {

        filaEditando.cells[0].textContent = nombre;
        filaEditando.cells[1].textContent = categoria;
        filaEditando.cells[2].textContent = `S/. ${precio}`;
        filaEditando.cells[3].textContent = stock;

        filaEditando = null;

    } else {

        tabla.innerHTML += `
        <tr>
            <td>${nombre}</td>
            <td>${categoria}</td>
            <td>S/. ${precio}</td>
            <td>${stock}</td>
            <td>
                <button
                    class="btn btn-sm btn-primary"
                    onclick="editarProducto(this)">
                    <i class="bi bi-pencil"></i>
                </button>

                <button
                    class="btn btn-sm btn-danger"
                    onclick="eliminarProducto(this)">
                    <i class="bi bi-trash"></i>
                </button>
            </td>
        </tr>
        `;
    }

    // Limpiar campos
    document.getElementById("nombre").value = "";
    document.getElementById("categoria").value = "";
    document.getElementById("precio").value = "";
    document.getElementById("stock").value = "";

    // Cerrar modal
    const modal = bootstrap.Modal.getInstance(
        document.getElementById("modalProducto")
    );

    modal.hide();

}



function editarProducto(boton) {

    filaEditando = boton.closest("tr");

    const nombre =
        filaEditando.cells[0].textContent;

    const categoria =
        filaEditando.cells[1].textContent;

    const precio =
        filaEditando.cells[2]
            .textContent
            .replace("S/. ", "");

    const stock =
        filaEditando.cells[3].textContent;

    document.getElementById("nombre").value = nombre;
    document.getElementById("categoria").value = categoria;
    document.getElementById("precio").value = precio;
    document.getElementById("stock").value = stock;

    const modal = new bootstrap.Modal(
        document.getElementById("modalProducto")
    );

    modal.show();
}

function eliminarProducto(boton) {

    const confirmar = confirm(
        "¿Desea eliminar este producto?"
    );

    if (confirmar) {
        boton.closest("tr").remove();
    }
}


function buscarProducto() {

    const texto = document
        .getElementById("buscarProducto")
        .value
        .toLowerCase();

    const filas = document.querySelectorAll(
        "#tablaProductos tr"
    );

    filas.forEach(fila => {

        const nombre =
            fila.cells[0].textContent.toLowerCase();

        const categoria =
            fila.cells[1].textContent.toLowerCase();

        if (
            nombre.includes(texto) ||
            categoria.includes(texto)
        ) {
            fila.style.display = "";
        } else {
            fila.style.display = "none";
        }

    });
}

