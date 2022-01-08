### fastJson

1、导入依赖

```xml
<dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.73</version>
            </dependency>
            
```

2、基本使用方法

- 将javaBean转化为json

  `String toJSON = JSON.toJSON(res).toString();`

  其中res为java对象

- 将json转化为javaBean

  `Data data = JSON.parseObject(s, Data.class);`

  其中s为json字符串

