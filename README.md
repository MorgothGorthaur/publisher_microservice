# publisher
This microservice provides an API for working with a queue of proxies and scenarios. The program makes it possible to add new proxies and scenarios to the queue or load them from a remote source (file or HTTP source). Additionally, it asynchronously validates proxies; currently, it can validate two types of proxies: direct and HTTP. It also makes it possible to fetch data from the queue (an authorization token is required for this).
## Publisher's API

- `POST: /publisher/{proxy/scenario}/all` – Adds a list of proxies/scenarios.
- `POST: /publisher/{proxy/scenario}/` – Adds a single proxy/scenario.
- `POST: /publisher/proxy/remote` – Loads proxies from the default remote source (specified in properties).
- `POST: /publisher/proxy/remote/custom` – Loads proxies from a custom remote source.
- `DELETE: /publisher/{proxy/scenario}/count/{size}` – Fetches data of a specified size from the queue.
- `DELETE: /publisher/{proxy/scenario}/` – Fetches a proxy/scenario from the queue.
- `DELETE: /publisher/{proxy/scenario}/all` – Fetches all proxies/scenarios.

## JSON Examples

### Proxy Example:

```json
{
	"proxyNetworkConfig": {
		"hostname": "20.210.113.32",
		"port": 8080
	},
	"proxyCredentials": {
		"username": "",
		"password": ""
	}
}
```

### Scenario Example:
```json
{
  "name": "oracle scenario",
  "site": "https://www.oracle.com/",
  "steps" : [
    {
      "action": "sleep",
      "value": "3000:5000"
    },
    {
      "action" : "clickXpath",
      "value" : "/html/body/div[2]/div/section[1]/div[1]/div[2]/nav/ul/li[6]/button"
    }
  ]
}
```

### Proxy Source Example

For loading from a file:

```json
{
    "source" : "proxy.json",
    "storage" : "file",
    "type" : "http"
}
```

For loading by URL:

```json
{
    "source" : "https://freeapiproxies.azurewebsites.net/proxyapi",
    "storage" : "url",
    "type" : "http"
}
```

Note, that remote HTTP API must have such a format:
```json
{
		"ip" : "20.210.113.32",
		"port": "8080",
		"username" : "user", /*optional*/
		"password" : "111"  /*optional*/
}
```

## Used Technologies
 ### Back-end:
- Spring Web
- Spring Security
- java-jwt
- okhttp
- AssertJ
- Mockito
 ### Server build:
- maven

## Requirement
- java 17
- maven

## Run
```bash
    cd .../project/directory
    mvn clean package
    java -jar target/publisher-0.0.1-SNAPSHOT.jar 
```