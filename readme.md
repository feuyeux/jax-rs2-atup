#Java Restful Web Service使用指南#
#综合示例 ATUP#
[**http://feuyeux.github.io/jax-rs2-atup/**](http://feuyeux.github.io/jax-rs2-atup)


Context.xml:
	
	<Resource name="jdbc/AtupDataSource"
	auth="Container" type="javax.sql.DataSource"
	driverClassName="org.gjt.mm.mysql.Driver"
	url="jdbc:mysql://localhost:3306/jaxrs2_atup"
	username="root"
	password="root"
	maxActive="20"
	maxIdle="10"
	maxWait="-1" />

![atup-topology.png](atup-topology.png)

#### atup-core ####
#### atup-user ####
REST WADL:
http://localhost:8008/atup-user/rest-api/application.wadl
#### atup-case ####
#### atup-device ####
#### atup-page  ####


#### Unit Test ####
> mvn clean install -PTI
> 
> curl -X POST http://localhost:8080/atup-device/rest-api/devices/detect
> 
> curl -X POST http://localhost:8080/atup-case/rest-api/testjobs/launch

