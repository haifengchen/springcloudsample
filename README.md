# springcloudsample

refer to https://github.com/piomin/sample-spring-microservices-new


oauth server doens't store user info into database, if want check another repository
https://github.com/haifengchen/springoauth2demo


this repository integrate spring cloud config, eureka, zuul, feign, oauth, swagger

oauth-service need start redis locally

swagger-ui  oauth:  client_1/123456


/**
*zuul doesn't integrate security,  let oauth token pass to detail microservices.
*If needed, integrate oauth with zuul, then check the token for detail service twice.
**/

integrate zuul with oauth security 
Private opinion, in real project, we can only expose zuul port publicly only, and hide microservices and remove security check for each detail services to improve the efficiency of communication to each other.