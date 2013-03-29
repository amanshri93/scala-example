package util

import com.unboundid.ldap.listener.InMemoryDirectoryServer
import com.unboundid.ldap.listener.InMemoryDirectoryServerConfig
import com.unboundid.ldap.listener.InMemoryListenerConfig
import com.unboundid.ldap.sdk.LDAPConnection
import com.unboundid.ldap.sdk.LDAPException
import com.unboundid.ldap.sdk.SearchResultEntry

import models._

object LdapUtil {

  def start(): InMemoryDirectoryServer = {
    val config = new InMemoryDirectoryServerConfig("dc=org");
    val listenerConfig = new InMemoryListenerConfig("test", null, 12345, null, null, null);
    config.setListenerConfigs(listenerConfig);
    config.setSchema(null); // do not check (attribute) schema
    val server = new InMemoryDirectoryServer(config);
    server.startListening();

    server.add("dn: dc=org", "objectClass: top", "objectClass: domain", "dc: org");
    server.add("dn: dc=geomajas,dc=org", "objectClass: top", "objectClass: domain", "dc: geomajas");
    server.add("dn: dc=roles,dc=geomajas,dc=org", "objectClass: top", "objectClass: domain", "dc: roles");
    server.add("dn: dc=staticsecurity,dc=geomajas,dc=org", "objectClass: top", "objectClass: domain",
      "dc: staticsecurity");
    server.add("dn: cn=testgroup,dc=roles,dc=geomajas,dc=org", "objectClass: groupOfUniqueNames",
      "cn: testgroup");

    server.add("dn: cn=user@test.com,dc=staticsecurity,dc=geomajas,dc=org", "objectClass: person", "locale: nl_BE",
      "sn: NormalUser", "givenName: Joe", "memberOf: cn=testgroup,dc=roles,dc=geomajas,dc=org", "userPassword: password");
    server.add("dn: admin=dev@test.com,dc=staticsecurity,dc=geomajas,dc=org", "objectClass: person", "locale: nl_BE",
      "sn: Administrator", "givenName: Cindy", "memberOf: cn=testgroup,dc=roles,dc=geomajas,dc=org", "userPassword: password");

    server
  }

  def authenticate(email: String, password: String): Option[Account] = {
    val server = start

    val conn = server.getConnection();
    val entry = conn.getEntry("cn=" + email + ",dc=staticsecurity,dc=geomajas,dc=org");

    val permission = entry.getAttributeValue("sn").toString
    val retPass = entry.getAttributeValue("userPassword")
    server.shutDown(true)

    println(permission);
    println(password);
    println(retPass);
    if (retPass.equals(password)) {
      println("password valid")
      val account = new Account(email, password, permission)
      toOption(account)
    } else {
      None
    }
  }

  def findByEmail(email: String): Option[Account] = {
    val server = start
    val conn = server.getConnection();
    val entry = conn.getEntry("cn=" + email + ",dc=staticsecurity,dc=geomajas,dc=org");

    val permission = entry.getAttributeValue("sn").toString
    val retPass = entry.getAttributeValue("userPassword")
    println(permission);
    println(retPass);
    server.shutDown(true)

    val account = new Account(email, retPass, permission)
    toOption(account)
  }

  def toOption[T](value: T): Option[T] = if (value == null) None else Some(value)

}