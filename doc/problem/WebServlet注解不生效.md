### WebServlet 注解不生效

#### 1、解决方法：

- 查看web.xml配置文件中的两个属性，version、metadata-complete

```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1"
         metadata-complete="false">
</web-app>
```



- 其中version必须大于3.0
- Metadata-complete 设置为false



#### 2、原因：

**1.1 version > 3.0**

因为web-app标签3.0以上版本才可以使用servlet的注解的，这是必要条件

**1.2 Metadata-complete**

该属性为true，则容器在部署时只依赖部署描述文件，忽略所有标注，如果不配置该属性，或者将其设置为false，则表示启动注解支持。当metadata-complete="false"时，web.xml和注解对于Servlet的影响同时起作用，两种方法定义的url-partten都可以访问到该Servlet，但是当通过web.xml定义的url-partten访问时，注解定义的属性将失效。所以如果你想用servlet注解来配置servlet的话，一定记得修改该属性为metadata-complete="false "。

