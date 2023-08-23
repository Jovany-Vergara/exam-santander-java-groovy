// https://mvnrepository.com/artifact/com.github.groovy-wslite/groovy-wslite
@Grapes(
    @Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='1.1.3')
)
import wslite.rest.RESTClient
import wslite.rest.Response

enum METHOD {
  GET,
  POST
}


Response get(api, path){
  RESTClient client = new RESTClient(api)
  client.get(path: path)
}

Response executeRestClient(api, path, method) {
  [
    (METHOD.GET): { get(api, path) }
  ][method]()
}



Response response = executeRestClient("https://jsonplaceholder.typicode.com", "/todos/1", METHOD.GET)
println(response)
