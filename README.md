# API REST

Se trata de uma API Rest com sistema que realiza o cadastro de clientes, juntamente com seu CEP. 
O cadastro do cliente é composto por um ID gerado automaticamente do tipo UUID, pelo nome e pelo seu endereço, que 
é fornecido através do CEP.

O sistema então realiza a consulta do CEP fornecido no site viacep, e cadastra o cliente com um endereço completo (CEP, rua, estado, pais e etc...).

Após o cadastro do cliente através do método POST, é possível realizar nesta API: 
- consulta de todos os clientes cadastrados através do GET
- consulta de um unico cliente através do GET{id}
- Excluir um cliente pelo ID através do DELETE
- Alterar um cliente pelo IT através do PUT.
