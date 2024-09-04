## Starting Guide


This application shows two solutions to find metrics for endpoints and system your main application is communicating.

Both version can be viewed in Grafana that must be imported and all calls can be found in Postman Collections.

#### Starting:
execute the `docker-compose.yml` that is in `service A` with the command:

`docker compose up --build -d`

Grafana:
UI: `http://localhost:3000/`

QueryEndpoint: `http://localhost:9090/api/v1/query?query=`

#### Credentials:
username: `admin`

password: `admin`

#### Endpoints to generate errors

Service A:

`http://localhost:8080/api/error/wws`
`http://localhost:8080/api/error/dms`
`http://localhost:8080/api/error/esb`

### Version 1
This approach publishes with the classes `CustomerMetricsConfiguration`  `PublicHealthController` `SystemCheckService`
own generated `UP` metrics for each endpoint, that prometheus can scrape then.

These scrape configuration can be found in `A/docker/prometheus`

The application offers flexible endpoints with: `http://localhost:8080/api/status/{servicename}`

Pre-configured are the Services: `ESB`, `WWS` and `DMS`

The logic for these endpoints healthcheck must be implemented.

Where to go: class: `SystemCheckService` method: `performHealthCheck`


### Version 2

This approach implements queries only configured in Grafana, to respond with a `1` or `0` based on which endpoint getting more errors then a set endpoint:

These two queries are presented:

the avg increase of errors at configured endpoint in the last 30min :

`sum(increase(http_server_requests_seconds_count{status=~"5..", uri="/api/error/dms"}[30m]))`

the boolean evaluation if it reaches the self configured limit, here `100`:

`(sum(increase(http_server_requests_seconds_count{status=~"5..", uri="/api/error/dms"}[30m])) < bool 100)`