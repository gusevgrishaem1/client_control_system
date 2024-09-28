const backendURL = 'http://localhost:8080';
var editingRow = null;

document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.sidebar a').forEach(item => {
        item.addEventListener('click', function() {
            document.querySelectorAll('.sidebar a').forEach(link => link.classList.remove('active'));
            document.querySelectorAll('.content-section').forEach(section => section.style.display = 'none');
            const contentId = this.getAttribute('data-content');
            document.getElementById(contentId).style.display = 'block';
            cancelClient();
            cancelCashRegister();
            cancelModel();
            cancelOFD();
            fetchAndDisplayCashRegisters();
            fetchAndDisplayClients();
            fetchAndDisplayModels();
            fetchAndDisplayOFDs();
            fetchAndDisplayTaxationSystems();
            filterModelTable(false);
            filterOFDTable(false);
            filterClientTable(false);
            filterCashRegisterTable(false);
            filterTaxationSystemTable(false);
            this.classList.add('active');
        });
    });
});

function loadDropdownData() {
    fetch(`${backendURL}/api/clients/combo`)
       .then(response => response.json())
       .then(clients => {
           let clientSelect = document.getElementById("cash-client-select");
           clientSelect.innerHTML = "<option value='' disabled selected>Выберите клиента</option>";
           clients.forEach(client => {
               let option = document.createElement("option");
               option.value = client.id;
               option.textContent = client.title;
               clientSelect.appendChild(option);
           });
       })
       .catch(error => console.error('Error fetching clients:', error));
    fetch(`${backendURL}/api/models/combo`)
       .then(response => response.json())
       .then(models => {
           let modelSelect = document.getElementById("cash-model-select");
           modelSelect.innerHTML = "<option value='' disabled selected>Выберите модель</option>";
           models.forEach(model => {
               let option = document.createElement("option");
               option.value = model.id;
               option.textContent = model.title;
               modelSelect.appendChild(option);
           });
       })
       .catch(error => console.error('Error fetching models:', error));
    fetch(`${backendURL}/api/ofds/combo`)
       .then(response => response.json())
       .then(ofds => {
           let ofdSelect = document.getElementById("cash-ofd-select");
           ofdSelect.innerHTML = "<option value='' disabled selected>Выберите ОФД</option>";
           ofds.forEach(ofd => {
               let option = document.createElement("option");
               option.value = ofd.id;
               option.textContent = ofd.title;
               ofdSelect.appendChild(option);
           });
       })
       .catch(error => console.error('Error fetching OFDs:', error));
       fetch(`${backendURL}/api/taxation-systems/combo`)
          .then(response => response.json())
          .then(snos => {
              let snoSelect = document.getElementById("cash-taxation-system-select");
              snoSelect.innerHTML = "<option value='' disabled selected>Выберите СНО</option>";
              snos.forEach(sno => {
                  let option = document.createElement("option");
                  option.value = sno.id;
                  option.textContent = sno.title;
                  snoSelect.appendChild(option);
              });
          })
          .catch(error => console.error('Error fetching OFDs:', error));
}

loadDropdownData();

var tableClient = new Tabulator("#client-table", {
    locale: true, // Включение локализации
    initialLocale: "ru", // Установка локали по умолчанию на русский
  layout: "fitColumns",
  pagination: true,
  paginationSize: 15,
  columns: [
      {title: "ID", field: "id", hozAlign: "center", width: 70, headerFilter: "input"},
      {title: "ИНН", field: "inn", hozAlign: "center", editor: "input", width: 150, headerFilter: "input"},
      {title: "Название", field: "title", editor: "input", width: 350, headerFilter: "input"},
      {title: "Телефон", field: "telephone", hozAlign: "center", editor: "input", width: 150, headerFilter: "input"},
      {title: "Архив", field: "archive", visible: false}
  ],
   rowContextMenu:[
            {
                label:"Редактировать",
                action:function(e, row) {
                  editClient(row);
                }
            },
            {
                label:"Архивировать",
                action:function(e, row) {
                  archiveClient(row)
                }
            },
            {
                label:"Восстановить",
                action:function(e, row) {
                    recoverClient(row)
                }
            }
      ],
  addRowPos: "top",
});

function filterTable(showArchived = false) {
    if(showArchived) {
        tableClient.setFilter("archive", "=", true);
    } else {
        tableClient.setFilter("archive", "=", false);
    }
}

document.getElementById("client-showArchived").addEventListener("click", function() {
    filterTable(true);
});

document.getElementById("client-showNotArchived").addEventListener("click", function() {
    filterTable(false);
});

filterTable(false);

function editClient(row) {
    editingRow = row;
    var data = row.getData();
    document.getElementById('client-id').value = data.id;
    document.getElementById('client-inn').value = data.inn;
    document.getElementById('client-title').value = data.title;
    document.getElementById('client-telephone').value = data.telephone;
    document.getElementById('client-save-button').style.display = 'inline-block';
    document.getElementById('client-cancel-button').style.display = 'inline-block';
    document.getElementById('client-add-button').style.display = 'none';
}

function archiveClient(row) {
    var data = row.getData()
    fetch(`${backendURL}/api/clients?id=` + data.id, {method: 'DELETE'})
    .then(() => {
        fetchAndDisplayClients();
        loadDropdownData();
    })
    .catch(error => console.error('Error deleting client:', error));
}

function recoverClient(row) {
    var data = row.getData()
    fetch(`${backendURL}/api/clients/recover?id=` + data.id, {method: 'PUT'})
    .then(() => {
        fetchAndDisplayClients();
        loadDropdownData();
    })
    .catch(error => console.error('Error deleting client:', error));
}

document.getElementById("client-form").addEventListener("submit", function (event) {
    event.preventDefault();

    var id = null;
    var inn = document.getElementById('client-inn').value;
    var title = document.getElementById('client-title').value;
    var telephone = document.getElementById('client-telephone').value;

    fetch(`${backendURL}/api/clients`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({id: id, inn: inn, title: title, telephone: telephone})
    })
    .then(response => response.json())
    .then(newClient => {
        fetchAndDisplayClients();
        loadDropdownData();
        resetClientForm();
    })
    .catch(error => console.error('Error adding client:', error));
});

function cancelClient() {
    editingRow = null;

    document.getElementById('client-form').reset();
    document.getElementById('client-id').value = '';
    document.getElementById('client-save-button').style.display = 'none';
    document.getElementById('client-cancel-button').style.display = 'none';
    document.getElementById('client-add-button').style.display = 'inline-block';
}

function saveClient() {
    if (editingRow) {
        var data = editingRow.getData()
        var id = data.id
        var archive = data.archive
        var inn = document.getElementById('client-inn').value;
        var title = document.getElementById('client-title').value;
        var telephone = document.getElementById('client-telephone').value;

        fetch(`${backendURL}/api/clients`, {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({id: id, inn: inn, title: title, telephone: telephone, archive: archive})
        })
        .then(response => {
            fetchAndDisplayClients();
            loadDropdownData();
            cancelClient();
        })
        .catch(error => console.error('Error adding client:', error));
    }
}

document.getElementById('client-save-button').addEventListener('click', function() {
    saveClient();
});

document.getElementById('client-cancel-button').addEventListener('click', function() {
    cancelClient();
});

// Таблица кассовых аппаратов
var tableCashRegister = new Tabulator("#cash-register-table", {
    layout: "fitColumns",
    pagination: true,
    paginationSize: 15,
    locale: true, // Включение локализации
    initialLocale: "ru", // Установка локали по умолчанию на русский
    columns: [
        {title: "ID", field: "id", hozAlign: "center", width: 70, headerFilter: "input"},
        {title: "Клиент", field: "clientTitle", hozAlign: "center", editor: "input", width: 200, headerFilter: "input"},
        {title: "Заводской номер", field: "factoryNumber", editor: "input", width: 200, headerFilter: "input"},
        {title: "Адрес установки", field: "installationAddress", editor: "input", width: 250, headerFilter: "input"},
        {title: "Регистрационный номер", field: "registrationNumber", editor: "input", width: 250, headerFilter: "input"},
        {title: "Модель", field: "modelTitle", editor: "input", width: 200, headerFilter: "input"},
        {title: "СНО", field: "taxationSystemTitle", editor: "input", width: 200, headerFilter: "input"},
        {title: "ОФД", field: "ofdTitle", editor: "input", width: 200, headerFilter: "input"},
        {title: "Дата активации ОФД", field: "activationDate", editor: "input", width: 200, headerFilter: "input"},
        {title: "Дата деактивации ОФД", field: "deactivationDate", editor: "input", width: 200, headerFilter: "input"},
        {title: "Тип ФН", field: "fiscalType", editor: "input", width: 200, headerFilter: "input"},
        {title: "Заводской номер ФН", field: "fiscalFactoryNumber", editor: "input", width: 200, headerFilter: "input"},
        {title: "Дата активации ФН", field: "fiscalActivationDate", editor: "input", width: 200, headerFilter: "input"},
        {title: "Дата деактивации ФН", field: "fiscalDeactivationDate", editor: "input", width: 200, headerFilter: "input"},
        {title: "Версия прошивки", field: "version", editor: "input", width: 200, headerFilter: "input"},
        {title: "Дата установки прошивки", field: "installationDate", editor: "input", width: 250, headerFilter: "input"},
    ],
    rowContextMenu: [
        {
            label: "Редактировать",
            action: function(e, row) {
                editCashRegister(row);
            }
        },
        {
            label: "Архивировать",
            action: function(e, row) {
                archiveCashRegister(row);
            }
        },
        {
            label: "Восстановить",
            action: function(e, row) {
                recoverCashRegister(row);
            }
        }
    ],
    addRowPos: "top",
});

// Фильтрация таблицы кассовых аппаратов
function filterCashRegisterTable(showArchived = false) {
    if (showArchived) {
        tableCashRegister.setFilter("archive", "=", true);
    } else {
        tableCashRegister.setFilter("archive", "=", false);
    }
}

document.getElementById("cash-showArchived").addEventListener("click", function() {
    filterCashRegisterTable(true);
});

document.getElementById("cash-showNotArchived").addEventListener("click", function() {
    filterCashRegisterTable(false);
});

filterCashRegisterTable(false);

function archiveCashRegister(row) {
    var data = row.getData();
    fetch(`${backendURL}/api/cash-registers?id=` + data.id, {method: 'DELETE'})
    .then(() => {
        loadDropdownData();
        fetchAndDisplayCashRegisters();
    })
    .catch(error => console.error('Error archiving cash register:', error));
}

function recoverCashRegister(row) {
    var data = row.getData();
    fetch(`${backendURL}/api/cash-registers/recover?id=` + data.id, {method: 'PUT'})
    .then(() => {
        loadDropdownData();
        fetchAndDisplayCashRegisters();
    })
    .catch(error => console.error('Error recovering cash register:', error));
}

document.getElementById("cash-register-form").addEventListener("submit", function(event) {
    event.preventDefault();

    var clientId = document.getElementById('cash-client-select').value;
    var modelId = document.getElementById('cash-model-select').value;
    var taxationSystemId = document.getElementById('cash-taxation-system-select').value;
    var ofdId = document.getElementById('cash-ofd-select').value;
    var factoryNumber = document.getElementById('cash-factoryNumber').value;
    var installationAddress = document.getElementById('cash-installationAddress').value;
    var registrationNumber = document.getElementById('cash-registrationNumber').value;
    var activationDate = document.getElementById('cash-activationDate').value;
    var deactivationDate = document.getElementById('cash-deactivationDate').value;
    var fiscalType = document.getElementById('cash-fiscalType').value;
    var fiscalFactoryNumber = document.getElementById('cash-fiscalFactoryNumber').value;
    var fiscalActivationDate = document.getElementById('cash-fiscalActivationDate').value;
    var fiscalDeactivationDate = document.getElementById('cash-fiscalDeactivationDate').value;
    var version = document.getElementById('cash-version').value;
    var installationDate = document.getElementById('cash-installationDate').value;

    var method = 'POST';
    var url = `${backendURL}/api/cash-registers`;
    var json = JSON.stringify({
           clientId, modelId, taxationSystemId, ofdId, factoryNumber, installationAddress, registrationNumber,
           activationDate, deactivationDate, fiscalType, fiscalFactoryNumber,
           fiscalActivationDate, fiscalDeactivationDate, version, installationDate
    });

    fetch(url, {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: json})
    .then(response => response.json())
    .then(newCashRegister => {
        fetchAndDisplayCashRegisters();
        loadDropdownData();
        cancelCashRegister();
    })
    .catch(error => console.error('Error adding cash register:', error));
});

function saveCashRegister() {
    if (editingRow) {
        var id = editingRow.getData().id
        var archive = editingRow.getData().archive
        var clientId = document.getElementById('cash-client-select').value;
        var modelId = document.getElementById('cash-model-select').value;
        var taxationSystemId = document.getElementById('cash-taxation-system-select').value;
        var ofdId = document.getElementById('cash-ofd-select').value;
        var factoryNumber = document.getElementById('cash-factoryNumber').value;
        var installationAddress = document.getElementById('cash-installationAddress').value;
        var registrationNumber = document.getElementById('cash-registrationNumber').value;
        var activationDate = document.getElementById('cash-activationDate').value;
        var deactivationDate = document.getElementById('cash-deactivationDate').value;
        var fiscalType = document.getElementById('cash-fiscalType').value;
        var fiscalFactoryNumber = document.getElementById('cash-fiscalFactoryNumber').value;
        var fiscalActivationDate = document.getElementById('cash-fiscalActivationDate').value;
        var fiscalDeactivationDate = document.getElementById('cash-fiscalDeactivationDate').value;
        var version = document.getElementById('cash-version').value;
        var installationDate = document.getElementById('cash-installationDate').value;
        var method = 'PUT'
        var url = `${backendURL}/api/cash-registers`
        var json = JSON.stringify({
                               id, clientId, modelId, taxationSystemId, ofdId, factoryNumber, installationAddress, registrationNumber,
                               activationDate, deactivationDate, fiscalType, fiscalFactoryNumber,
                               fiscalActivationDate, fiscalDeactivationDate, version, installationDate, archive
                           })
        fetch(url, {method: method, headers: {'Content-Type': 'application/json'}, body: json})
        .then(response => {
            fetchAndDisplayCashRegisters();
            loadDropdownData();
            cancelCashRegister();
        })
        .catch(error => console.error('Error saving cash register:', error))
    }
}



function cancelCashRegister() {
    editingRow = null;

    document.getElementById('cash-register-form').reset();
    document.getElementById('cash-save-button').style.display = 'none';
    document.getElementById('cash-cancel-button').style.display = 'none';
    document.getElementById('cash-register-add-button').style.display = 'inline-block';
}

function editCashRegister(row) {
    editingRow = row;
    var data = row.getData();
    document.getElementById('cash-client-select').value = data.clientId;
    document.getElementById('cash-model-select').value = data.modelId;
    document.getElementById('cash-taxation-system-select').value = data.taxationSystemId;
    document.getElementById('cash-ofd-select').value = data.ofdId;
    document.getElementById('cash-factoryNumber').value = data.factoryNumber;
    document.getElementById('cash-installationAddress').value = data.installationAddress;
    document.getElementById('cash-registrationNumber').value = data.registrationNumber;
    document.getElementById('cash-activationDate').value = data.activationDate;
    document.getElementById('cash-deactivationDate').value = data.deactivationDate;
    document.getElementById('cash-fiscalType').value = data.fiscalType;
    document.getElementById('cash-fiscalFactoryNumber').value = data.fiscalFactoryNumber;
    document.getElementById('cash-fiscalActivationDate').value = data.fiscalActivationDate;
    document.getElementById('cash-fiscalDeactivationDate').value = data.fiscalDeactivationDate;
    document.getElementById('cash-version').value = data.version;
    document.getElementById('cash-installationDate').value = data.installationDate;
    document.getElementById('cash-save-button').style.display = 'inline-block';
    document.getElementById('cash-cancel-button').style.display = 'inline-block';
    document.getElementById('cash-register-add-button').style.display = 'none';
}

document.getElementById('cash-cancel-button').addEventListener('click', function() {
    cancelCashRegister();
});

document.getElementById('cash-save-button').addEventListener('click', function() {
    saveCashRegister();
});

function fetchAndDisplayCashRegisters() {
    fetch(`${backendURL}/api/cash-registers`)
    .then(response => response.json())
    .then(data => {
        tableCashRegister.setData(data);
    })
    .catch(error => console.error('Error fetching cash registers:', error));
}

function fetchAndDisplayClients() {
    fetch(`${backendURL}/api/clients`)
    .then(response => response.json())
    .then(data => {
        tableClient.setData(data);
    })
    .catch(error => console.error('Error fetching clients:', error));
}

var tableModel = new Tabulator("#model-table", {
    layout: "fitColumns",
    pagination: true,
    locale: true, // Включение локализации
    initialLocale: "ru", // Установка локали по умолчанию на русский
    paginationSize: 15,
    columns: [
        {title: "ID", field: "id", hozAlign: "center", width: 70, headerFilter: "input"},
        {title: "Название модели", field: "title", editor: "input", width: 250, headerFilter: "input"},
        {title: "Архив", field: "archive", visible: false}
    ],
    rowContextMenu:[
         {
             label: "Редактировать",
             action: function(e, row) {
                 editModel(row);
             }
         },
         {
             label: "Архивировать",
             action: function(e, row) {
                 archiveModel(row);
             }
         },
         {
             label: "Восстановить",
             action: function(e, row) {
                 recoverModel(row);
             }
         }
    ],
    addRowPos: "top",
});

function editModel(row) {
    editingRow = row;
    var data = row.getData();
    document.getElementById('model-title').value = data.title;
    document.getElementById('model-save-button').style.display = 'inline-block';
    document.getElementById('model-cancel-button').style.display = 'inline-block';
    document.getElementById('model-add-button').style.display = 'none';
}

function archiveModel(row) {
    var data = row.getData();
    fetch(`${backendURL}/api/models?id=` + data.id, {method: 'DELETE'})
    .then(() => {
        loadDropdownData();
        fetchAndDisplayModels();
    })
    .catch(error => console.error('Error archiving model:', error));
}

function recoverModel(row) {
    var data = row.getData();
    fetch(`${backendURL}/api/models/recover?id=` + data.id, {method: 'PUT'})
    .then(() => {
        loadDropdownData();
        fetchAndDisplayModels();
    })
    .catch(error => console.error('Error recovering model:', error));
}

function fetchAndDisplayModels() {
    fetch(`${backendURL}/api/models`)
    .then(response => response.json())
    .then(data => {
        tableModel.setData(data);
    })
    .catch(error => console.error('Error fetching models:', error));
}

function filterModelTable(showArchived) {
    if (showArchived) {
        tableModel.setFilter("archive", "=", true);
    } else {
        tableModel.setFilter("archive", "=", false);
    }
}

function saveModel() {
    if (editingRow) {
        var data = editingRow.getData()
        var id = data.id
        var archive = data.archive
        var title = document.getElementById('model-title').value;
        var method = 'PUT';
        var url = `${backendURL}/api/models`;
        var json = JSON.stringify({title, id, archive});
        fetch(url, {
            method: method,
            headers: {'Content-Type': 'application/json'},
            body: json})
        .then(response => {
            fetchAndDisplayModels();
            loadDropdownData();
            cancelModel();
        })
        .catch(error => console.error('Error adding cash register:', error));
    }
}

function cancelModel() {
    editingRow = null;
    document.getElementById('model-form').reset();
    document.getElementById('model-save-button').style.display = 'none';
    document.getElementById('model-cancel-button').style.display = 'none';
    document.getElementById('model-add-button').style.display = 'inline-block';
}

document.getElementById("model-form").addEventListener("submit", function(event) {
    event.preventDefault();

    var title = document.getElementById('model-title').value;

    var method = 'POST';
    var url = `${backendURL}/api/models`;
    var json = JSON.stringify({title});

    fetch(url, {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: json})
    .then(response => response.json())
    .then(model => {
        fetchAndDisplayModels();
        loadDropdownData();
        cancelModel();
    })
    .catch(error => console.error('Error adding cash register:', error));
});

document.getElementById("model-showArchived").addEventListener("click", function() {
    filterModelTable(true);
});

document.getElementById("model-showNotArchived").addEventListener("click", function() {
    filterModelTable(false);
});

document.getElementById('model-cancel-button').addEventListener('click', function() {
    cancelModel();
});

document.getElementById('model-save-button').addEventListener('click', function() {
    saveModel();
});

var tableOfd = new Tabulator("#ofd-table", {
    layout: "fitColumns",
    pagination: true,
    paginationSize: 15,
    locale: true, // Включение локализации
    initialLocale: "ru", // Установка локали по умолчанию на русский
    columns: [
        {title: "ID", field: "id", hozAlign: "center", width: 70, headerFilter: "input"},
        {title: "Название ОФД", field: "title", editor: "input", width: 250, headerFilter: "input"},
        {title: "ИНН", field: "inn", editor: "input", width: 150, headerFilter: "input"},
        {title: "Архив", field: "archive", visible: false}
    ],
    rowContextMenu:[
         {
             label: "Редактировать",
             action: function(e, row) {
                 editOFD(row);
             }
         },
         {
             label: "Архивировать",
             action: function(e, row) {
                 archiveOFD(row);
             }
         },
         {
             label: "Восстановить",
             action: function(e, row) {
                 recoverOFD(row);
             }
         }
    ],
    addRowPos: "top",
});

function editOFD(row) {
    editingRow = row;
    var data = row.getData();
    document.getElementById('ofd-title').value = data.title;
    document.getElementById('ofd-inn').value = data.inn;
    document.getElementById('ofd-save-button').style.display = 'inline-block';
    document.getElementById('ofd-cancel-button').style.display = 'inline-block';
    document.getElementById('ofd-add-button').style.display = 'none';
}

function archiveOFD(row) {
    var data = row.getData();
    fetch(`${backendURL}/api/ofds?id=` + data.id, {method: 'DELETE'})
    .then(() => {
        loadDropdownData();
        fetchAndDisplayOFDs();
    })
    .catch(error => console.error('Error archiving OFD:', error));
}

function recoverOFD(row) {
    var data = row.getData();
    fetch(`${backendURL}/api/ofds/recover?id=` + data.id, {method: 'PUT'})
    .then(() => {
        loadDropdownData();
        fetchAndDisplayOFDs();
    })
    .catch(error => console.error('Error recovering OFD:', error));
}

function fetchAndDisplayOFDs() {
    fetch(`${backendURL}/api/ofds`)
    .then(response => response.json())
    .then(data => {
        tableOfd.setData(data);
    })
    .catch(error => console.error('Error fetching OFDs:', error));
}

function filterOFDTable(showArchived) {
    if (showArchived) {
        tableOfd.setFilter("archive", "=", true);
    } else {
        tableOfd.setFilter("archive", "=", false);
    }
}

function saveOFD() {
    if (editingRow) {
        var data = editingRow.getData();
        var id = data.id;
        var archive = data.archive;
        var title = document.getElementById('ofd-title').value;
        var inn = document.getElementById('ofd-inn').value;
        var method = 'PUT';
        var url = `${backendURL}/api/ofds`;
        var json = JSON.stringify({title, inn, id, archive});
        fetch(url, {
            method: method,
            headers: {'Content-Type': 'application/json'},
            body: json})
        .then(response => {
            fetchAndDisplayOFDs();
            loadDropdownData();
            cancelOFD();
        })
        .catch(error => console.error('Error saving OFD:', error));
    }
}

function cancelOFD() {
    editingRow = null;
    document.getElementById('ofd-form').reset();
    document.getElementById('ofd-save-button').style.display = 'none';
    document.getElementById('ofd-cancel-button').style.display = 'none';
    document.getElementById('ofd-add-button').style.display = 'inline-block';
}

document.getElementById("ofd-form").addEventListener("submit", function(event) {
    event.preventDefault();

    var title = document.getElementById('ofd-title').value;
    var inn = document.getElementById('ofd-inn').value;

    var method = 'POST';
    var url = `${backendURL}/api/ofds`;
    var json = JSON.stringify({title, inn});

    fetch(url, {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: json})
    .then(response => response.json())
    .then(ofd => {
        fetchAndDisplayOFDs();
        loadDropdownData();
        cancelOFD();
    })
    .catch(error => console.error('Error adding OFD:', error));
});

document.getElementById("ofd-showArchived").addEventListener("click", function() {
    filterOFDTable(true);
});

document.getElementById("ofd-showNotArchived").addEventListener("click", function() {
    filterOFDTable(false);
});

document.getElementById('ofd-cancel-button').addEventListener('click', function() {
    cancelOFD();
});

document.getElementById('ofd-save-button').addEventListener('click', function() {
    saveOFD();
});

var tableTaxationSystem = new Tabulator("#taxation-system-table", {
    layout: "fitColumns",
    pagination: true,
    paginationSize: 15,
    locale: true, // Включение локализации
    initialLocale: "ru", // Установка локали по умолчанию на русский
    columns: [
        {title: "ID", field: "id", hozAlign: "center", width: 70, headerFilter: "input"},
        {title: "Название системы налогообложения", field: "title", editor: "input", width: 250, headerFilter: "input"},
        {title: "Архив", field: "archive", visible: false}
    ],
    rowContextMenu:[
         {
             label: "Редактировать",
             action: function(e, row) {
                 editTaxationSystem(row);
             }
         },
         {
             label: "Архивировать",
             action: function(e, row) {
                 archiveTaxationSystem(row);
             }
         },
         {
             label: "Восстановить",
             action: function(e, row) {
                 recoverTaxationSystem(row);
             }
         }
    ],
    addRowPos: "top",
});

function editTaxationSystem(row) {
    editingRow = row;
    var data = row.getData();
    document.getElementById('taxation-system-title').value = data.title;
    document.getElementById('taxation-system-save-button').style.display = 'inline-block';
    document.getElementById('taxation-system-cancel-button').style.display = 'inline-block';
    document.getElementById('taxation-system-add-button').style.display = 'none';
}

function archiveTaxationSystem(row) {
    var data = row.getData();
    fetch(`${backendURL}/api/taxation-systems?id=` + data.id, {method: 'DELETE'})
    .then(() => {
        loadDropdownData();
        fetchAndDisplayTaxationSystems();
    })
    .catch(error => console.error('Error archiving taxation system:', error));
}

function recoverTaxationSystem(row) {
    var data = row.getData();
    fetch(`${backendURL}/api/taxation-systems/recover?id=` + data.id, {method: 'PUT'})
    .then(() => {
        loadDropdownData();
        fetchAndDisplayTaxationSystems();
    })
    .catch(error => console.error('Error recovering taxation system:', error));
}

function fetchAndDisplayTaxationSystems() {
    fetch(`${backendURL}/api/taxation-systems`)
    .then(response => response.json())
    .then(data => {
        tableTaxationSystem.setData(data);
    })
    .catch(error => console.error('Error fetching taxation systems:', error));
}

function filterTaxationSystemTable(showArchived) {
    if (showArchived) {
        tableTaxationSystem.setFilter("archive", "=", true);
    } else {
        tableTaxationSystem.setFilter("archive", "=", false);
    }
}

function saveTaxationSystem() {
    if (editingRow) {
        var data = editingRow.getData();
        var id = data.id;
        var archive = data.archive;
        var title = document.getElementById('taxation-system-title').value;
        var method = 'PUT';
        var url = `${backendURL}/api/taxation-systems`;
        var json = JSON.stringify({title, id, archive});
        fetch(url, {
            method: method,
            headers: {'Content-Type': 'application/json'},
            body: json})
        .then(response => {
            fetchAndDisplayTaxationSystems();
            loadDropdownData();
            cancelTaxationSystem();
        })
        .catch(error => console.error('Error saving taxation system:', error));
    }
}

function cancelTaxationSystem() {
    editingRow = null;
    document.getElementById('taxation-system-form').reset();
    document.getElementById('taxation-system-save-button').style.display = 'none';
    document.getElementById('taxation-system-cancel-button').style.display = 'none';
    document.getElementById('taxation-system-add-button').style.display = 'inline-block';
}

document.getElementById("taxation-system-form").addEventListener("submit", function(event) {
    event.preventDefault();

    var title = document.getElementById('taxation-system-title').value;

    var method = 'POST';
    var url = `${backendURL}/api/taxation-systems`;
    var json = JSON.stringify({title});

    fetch(url, {
        method: method,
        headers: {'Content-Type': 'application/json'},
        body: json})
    .then(response => response.json())
    .then(taxationSystem => {
        fetchAndDisplayTaxationSystems();
        loadDropdownData();
        cancelTaxationSystem();
    })
    .catch(error => console.error('Error adding taxation system:', error));
});

document.getElementById("taxation-system-showArchived").addEventListener("click", function() {
    filterTaxationSystemTable(true);
});

document.getElementById("taxation-system-showNotArchived").addEventListener("click", function() {
    filterTaxationSystemTable(false);
});

document.getElementById('taxation-system-cancel-button').addEventListener('click', function() {
    cancelTaxationSystem();
});

document.getElementById('taxation-system-save-button').addEventListener('click', function() {
    saveTaxationSystem();
});

document.getElementById('logoutButton').addEventListener('click', function() {
    fetch('/logout', {
        method: 'POST',
        credentials: 'same-origin' // Отправляем сессионные куки, если они нужны
    })
    .then(response => {
        if (response.ok) {
            // Перенаправляем на страницу логина после успешного выхода
            window.location.href = '/login-page';
        } else {
            console.error('Ошибка при выходе');
        }
    })
    .catch(error => console.error('Ошибка:', error));
});


fetchAndDisplayCashRegisters();
fetchAndDisplayClients();
fetchAndDisplayModels();
fetchAndDisplayOFDs();
fetchAndDisplayTaxationSystems();
filterModelTable(false);
filterOFDTable(false);
filterClientTable(false);
filterCashRegisterTable(false);
filterTaxationSystemTable(false);

