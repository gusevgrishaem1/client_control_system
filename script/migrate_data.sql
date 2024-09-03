delete from ofd_data;
delete from taxation_system_data;
delete from client_data;
delete from model_data;
delete from cash_register_data;

INSERT INTO ofd_data (id, inn, title, pr_archive)
SELECT id, individual_taxpayer_number, name, false
FROM fiscal_data_operator;

INSERT INTO taxation_system_data (id, title, pr_archive)
SELECT id, name, false
FROM taxation_system;

INSERT INTO client_data (id, inn, telephone, title, pr_archive)
SELECT id, individual_taxpayer_number, telephone, name, false
FROM client;

INSERT INTO model_data (id, title, pr_archive)
SELECT id, name, false
FROM cashbox_model;

INSERT INTO cash_register_data
(id, ofd_contract_activation_date, pr_archive,
ofd_contract_deactivation_date, nn_factory, fiscal_activation_date,
fiscal_deactivation_date, fiscal_nn_factory, fiscal_type, nm_install_address,
os_install_date, nn_registration, os_nm_version, id_client, id_model, id_ofd, id_taxation_system)
select
c.id, fdoa.activation_date, false, fdoa.deactivation_date, c.factory_number, fm.activation_date, fm.deactivation_date,
fm.factory_number, fm.`type`, c.installation_address, f.installation_date, c.registration_number,
f.version, c.client_id, c.cashbox_model_id, fdoa.fiscal_data_operator_id, c.taxation_system_id
from cashbox c
left join cashbox_model cm on cm.id = c.cashbox_model_id
left join taxation_system ts on ts.id = c.taxation_system_id
left join fiscal_memory fm on fm.cashbox_id = c.id
left join fiscal_data_operator_agreement fdoa on fdoa.cashbox_id = c.id
left join firmware f on f.cashbox_id = c.id;