# Technical test - Universidad de la Salle.

Ariel David Herrera Ahumada

## Spring boot:

- Abre el proyecto.

- ejecuta el código.

- para probarlo usa en postman:

curl --location 'localhost:8080/search-horandvert' \
--header 'Content-Type: application/json' \
--data '{
    "searchword": "abcdefghijklmnopqrstuvwxyzabcdef",
    "rows": 4,
    "word": "dlt"
}'

para Horizontal y vertical

curl --location 'localhost:8080/search' \
--header 'Content-Type: application/json' \
--data '{
    "searchword": "abcdefghijklmnopqrstuvwxyzabcdef",
    "rows": 4,
    "word": "dlt"
}'

Y este para obtener la palabra en diagonal de la matriz.

¡gracias!.
