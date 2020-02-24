# spring-dependency-management-bug

1. Run the application using DemoApplication.java 

Error message: 

```
***************************
APPLICATION FAILED TO START
***************************

Description:

The dependencies of some of the beans in the application context form a cycle:

   org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaConfiguration
┌─────┐
|  dataSource defined in com.example.demo.DemoApplication
↑     ↓
|  firstDS defined in com.example.demo.DemoApplication
↑     ↓
|  org.springframework.boot.autoconfigure.jdbc.DataSourceInitializerInvoker
└─────┘
```


