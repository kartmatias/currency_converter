# Currency Converter
Currency Converter

## External API dependency
http://api.exchangeratesapi.io
* get token and add into application.properties - exchangeratesapi.key=[your-key]

* for debug you can use json-server to mock api

### Mock API
* npm install -g json-server
* cd mock-json-api
* json-server --watch db.json

### Docker postgresql
* Install docker
* Install docker-composer
* Run docker-compose up

### Converter API documentation
* run project
* open http://localhost:8080/jsondoc-ui.html#
* put "http://localhost:8080/jsondoc" into edit box and click button "get documentation"

![JSONDoc](../main/api-doc/json-doc.png)