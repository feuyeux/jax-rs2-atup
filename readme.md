#Java Restful Web Service使用指南#
#综合示例 ATUP#
[**http://feuyeux.github.io/jax-rs2-atup/**](http://feuyeux.github.io/jax-rs2-atup)

![atup-topology.png](atup-topology.png)

### build ###
> mvn clean install -DskipTests -PCI

### Unit Test ###
> mvn clean install -PTI
>
> curl -X POST http://localhost:8080/atup-device/rest-api/devices/detect
>
> curl -X POST http://localhost:8080/atup-case/rest-api/testjobs/launch

### Datasource Setting ###
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
	
### Maven Setting ###
####resources properties####
[http://mojo.codehaus.org/properties-maven-plugin/usage.html](http://mojo.codehaus.org/properties-maven-plugin/usage.html)

     pom.xml
     <build>
            <finalName>atup-user</finalName>
            <resources>
                <resource>
                    <directory>src/main/resources</directory>
                    <filtering>true</filtering>
                </resource>
            </resources>

     <profile>
        <id>CI</id>
        <build>
            <plugins>
                <plugin>
                    <groupId>org.codehaus.mojo</groupId>
                    <artifactId>properties-maven-plugin</artifactId>
                    <version>1.0-alpha-2</version>
                    <executions>
                        <execution>
                            <phase>initialize</phase>
                            <goals>
                                <goal>read-project-properties</goal>
                            </goals>
                            <configuration>
                                <files>
                                    <file>src/main/resources/section/staging.properties</file>
                                </files>
                            </configuration>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>
    </profile>

    resources/section/staging.properties
    db.server.address=10.11.72.54
    db.driver=com.mysql.jdbc.Driver
    db.url=jdbc:mysql://${db.server.address}:3306/jaxrs2_atup
    db.username=root
    db.password=root

####token replacer####
[https://code.google.com/p/maven-replacer-plugin](https://code.google.com/p/maven-replacer-plugin/)

    pom.xml
    <properties>
        <main.basedir>${project.parent.basedir}</main.basedir>
    </properties>

    <profile>
        <id>CI</id>
        <build>
            <plugins>
                <plugin>
                    <groupId>com.google.code.maven-replacer-plugin</groupId>
                    <artifactId>replacer</artifactId>
                    <version>1.5.2</version>
                    <executions>
                        <execution>
                            <phase>validate</phase>
                            <goals>
                                <goal>replace</goal>
                            </goals>
                        </execution>
                    </executions>
                    <configuration>
                        <file>${main.basedir}/atup-page/src/main/webapp/js/index_buildtime.js</file>
                        <outputFile>${main.basedir}/atup-page/src/main/webapp/js/index.js</outputFile>
                        <replacements>
                            <replacement>
                                <token>@buildtime@</token>
                                <value>${maven.build.timestamp}</value>
                            </replacement>
                        </replacements>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </profile>

    index_buildtime.js
    function markVersion() {
        jQuery('#buildDiv').html("Build Time: @buildtime@");
    }

####file replace####

    pom.xml
    <id>CI</id>
            <build>
                <plugins>

                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-antrun-plugin</artifactId>
                        <version>1.7</version>
                        <executions>
                            <execution>
                                <phase>initialize</phase>
                                <configuration>
                                    <target>
                                        <copy file="src/main/webapp/js/atupCommon_staging.js"
                                              tofile="${project.build.directory}/${project.artifactId}-${project.version}/js/atupCommon.js" overwrite="true" />
                                    </target>
                                </configuration>
                                <goals>
                                    <goal>run</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>

###Modules###

#### atup-core ####

#### atup-user ####
REST WADL:
[http://localhost:8080/atup-user/rest-api/application.wadl](http://localhost:8080/atup-user/rest-api/application.wadl)

- atupAdmin jaxman
- atupJobKiller jaxman
- atupDeviceKeeper jaxman
- xer xer

#### atup-case ####

#### atup-device ####

#### atup-page  ####