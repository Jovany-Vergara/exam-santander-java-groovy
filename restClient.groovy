// https://mvnrepository.com/artifact/com.github.groovy-wslite/groovy-wslite
@Grapes(
    [
      @Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='1.1.3'),
    ]
)
import wslite.rest.RESTClient
import wslite.rest.Response

enum METHOD {
  GET,
  POST,
  DELETE
}


Response get(api, path){
  RESTClient client = new RESTClient(api)
  client.get(path: path)
}

Response post(api, path, body){
  RESTClient client = new RESTClient(api)
  client.post(path: path){
    json body
  }
}

Response delete(api, path){
  RESTClient client = new RESTClient(api)
  client.delete(path: path)
}
Response executeRestClient(api, path, method, body = []) {
  [
    (METHOD.GET): { get(api, path) },
    (METHOD.POST): { post(api, path, body) },
    (METHOD.DELETE): { delete(api, path) },
  ][method]()
}


// Exmaple GET
Response get_response = executeRestClient("https://jsonplaceholder.typicode.com", "/todos/1", METHOD.GET)
//[id:1, completed:false, title:delectus aut autem, userId:1]
println(get_response.json)
//

// Example POST
Response post_response = executeRestClient("https://jsonplaceholder.typicode.com", "/posts", METHOD.POST, [title: 'foo', body: 'bar', userId: 1])
println post_response.json

// Example DELETE
Response delete_response = executeRestClient("https://jsonplaceholder.typicode.com", "/posts/1", METHOD.DELETE)
println delete_response.json
