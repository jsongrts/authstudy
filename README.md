# authstudy

This toy project shows
* how to use Jersey 2 and Tomcat 8 to create a RESTful service
* gradle file for web application
* a simple home-made ORM framework
* how to use Gson
* how to setup server.xml and web.xml to enable BASIC authentication

# how to enable BASIC auth in Tomcat 8

We should do 3 things to enable BASIC support in Tomcat 8 (I assume we store the username/password in database):
* create a users table and roles table in the database (note that table names are configurable) 
* edit server.xml to contain a Realm element

<Realm className="org.apache.catalina.realm.JDBCRealm"
                       driverName="com.mysql.jdbc.Driver"
                       connectionURL="jdbc:mysql://localhost/stockapp?user=root"
                       userTable="user" userNameCol="name" userCredCol="pass"
                       userRoleTable="role" roleNameCol="role_name" />

* edit web.xml of your web application to tell Tomcat that accesses to this web application needs authencation

<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
		 http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
    <login-config>
        <auth-method>BASIC</auth-method>
    </login-config>

    <security-constraint>
        <web-resource-collection>
            <web-resource-name>Everything</web-resource-name>
            <url-pattern>/*</url-pattern>
        </web-resource-collection>

        <auth-constraint>
            <role-name>user</role-name>
        </auth-constraint>

        <user-data-constraint>
            <transport-guarantee>NONE</transport-guarantee>
        </user-data-constraint>
    </security-constraint>
</web-app>
