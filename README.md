# springboot-jms-activemq
ActiveMQ JMS Integration example in Spring Boot


To run this project you need
1. ActiveMQ Installed up and in running state with default configuration


Flow 

1. Import this project as Maven in your editor.
2. Run this project.
3. Once the project is up and running, open the browser.
4. **http://localhost:8080/jms/send/{helloWorld}** Here helloWorld is a Message you can replace with your custom message.
 This endpoint will add a message to ActiveMQ. 
 1. To see this message Open ActiveMQ in browser, 
 2. Go to /admin use **username : admin password : admin**
 3. Then click on Queues Link you will find "arg.message.queue"
 ![image](https://user-images.githubusercontent.com/19936392/119260415-4939c500-bbf0-11eb-93d7-c94da5303eb0.png)
 4. Under Number of pending messages you will see count.
 ![image](https://user-images.githubusercontent.com/19936392/119260448-70909200-bbf0-11eb-8ccf-47054285a089.png)
 5. Then click on the name of queue you will get screen like this
 ![image](https://user-images.githubusercontent.com/19936392/119260475-928a1480-bbf0-11eb-8aed-defa3f62f457.png)
 6. Then click on messageID you will be able to see the message
 ![image](https://user-images.githubusercontent.com/19936392/119260505-b188a680-bbf0-11eb-8654-ded37aee01ed.png)

5. Now hit api in browser **http://localhost:8080/jms/fetch**
6. you will get a message in the browser that was fetched from ActiveMQ queue.
7. Now under the queues you will see that count is 0.
![image](https://user-images.githubusercontent.com/19936392/119260557-f3195180-bbf0-11eb-9880-13495ca182ff.png)

Note : 

If you want to send dto as a message, the code is already present inside the repository. 
Will add the endpoint to that soon. 

If you want it in a microservice just use the receiver code in another microservice. Will share the code of that too.
 
