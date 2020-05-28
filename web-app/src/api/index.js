import OpenAPIClientAxios from 'openapi-client-axios'
import definition from './api.yaml'

const index = new OpenAPIClientAxios({
  definition: definition
})

const client = index.initSync()
export default client
