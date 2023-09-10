# Spring Security FilterChain POC

- According to Spring documentation: <br/>
The client sends a request to the application, and the container creates a FilterChain, which contains the Filter instances and Servlet that should process the HttpServletRequest, based on the path of the request URI. 
In a Spring MVC application, the Servlet is an instance of DispatcherServlet. 
At most, one Servlet can handle a single HttpServletRequest and HttpServletResponse. 
- DOC: <a href="https://docs.spring.io/spring-security/reference/servlet/architecture.html">Spring Security Architecture<a/>

In this case, we use the filter chain to deal with a request header in a specific endpoint and divert the flow based in any logic we want to apply.