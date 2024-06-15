const tableBody = document.querySelector("#odontologosTable tbody");

function fetchOdontologos() {
  fetch(`/odontologo`)
    .then(response => response.json())
    .then(data => {
      console.log(data);
      tableBody.innerHTML = ""; // Limpiar el contenido actual de la tabla

      data.forEach((odontologo, index) => {
        const row = document.createElement("tr");
        row.innerHTML = `
          <td>${odontologo.id}</td>
          <td>${odontologo.nombre}</td>
          <td>${odontologo.apellido}</td>
          <td>${odontologo.nroMatricula}</td>
          <td>
            <button class="modificar-btn" data-id="${odontologo.id}">Modificar</button>
          </td>
          <td>
            <div id="editForm-${odontologo.id}" style="display: none;">
              <input type="text" id="nombre-${odontologo.id}" value="${odontologo.nombre}" />
              <input type="text" id="apellido-${odontologo.id}" value="${odontologo.apellido}" />
              <input type="text" id="matricula-${odontologo.id}" value="${odontologo.nroMatricula}" />
              <button onclick="updateOdontologo(${odontologo.id})">Guardar</button>
            </div>
          </td>
        `;
        tableBody.appendChild(row);
      });
    })
    .catch(error => {
      console.error("Error fetching data:", error);
    });
}

document.addEventListener('DOMContentLoaded', function() {
  fetchOdontologos();

  document.addEventListener('click', function(event) {
    if (event.target.classList.contains('modificar-btn')) {
      const odontologoId = event.target.getAttribute('data-id');
      const editForm = document.getElementById(`editForm-${odontologoId}`);
      editForm.style.display = editForm.style.display === 'none' ? 'block' : 'none';
    }
  });
});

function updateOdontologo(id) {
  const nombre = document.getElementById(`nombre-${id}`).value;
  const apellido = document.getElementById(`apellido-${id}`).value;
  const matricula = document.getElementById(`matricula-${id}`).value;

  fetch(`/odontologo/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ nombre, apellido, nroMatricula: matricula })
  })
  .then(response => response.json())
  .then(data => {
    console.log('Update successful', data);
    fetchOdontologos(); // Refresh the list
    alert('Odontólogo actualizado con éxito!'); // Mostrar alerta de éxito
    window.location.reload(); // Forzar recarga de la página

  })
  .catch(error => {
    console.error('Error updating odontologo:', error);
  });
}


