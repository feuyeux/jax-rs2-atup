tomcat7-maven-plugin

.m2/settings.xml
<server>
       <id>Atup_Tomcat_Staging</id>
       <username>admin</username>
       <password>admin</password>
</server>

nano TOMCAT/conf/tomcat-users.xml
<?xml version='1.0' encoding='utf-8'?>
<tomcat-users>
  <role rolename="manager-gui"/>
  <role rolename="manager-script"/>
  <user username="admin" password="admin" roles="manager-script,manager-gui"/>
</tomcat-users>