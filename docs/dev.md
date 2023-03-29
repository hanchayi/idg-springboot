# 开发文档

## 最快的 maven repository--阿里镜像仓库

``` xml
  <mirrors>
    <mirror>
      <id>alimaven</id>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
      <mirrorOf>central</mirrorOf>        
    </mirror>
  </mirrors>
```

## 热重载

``` xml
<!-- devtools 热部署 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<optional>runtime</optional>
			<scope>true</scope>
		</dependency>

    <plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<fork>true</fork>
					<addResources>true</addResources>
				</configuration>
			</plugin>
```

## maven仓库
https://developer.aliyun.com/mvn/search

## 注册登陆
https://juejin.cn/post/6992562025318760461#heading-4