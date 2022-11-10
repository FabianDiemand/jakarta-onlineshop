#!/bin/bash

# ADMIN_PASSWORD="admin"

# Create password for glassfish admin
if [[ -z $ADMIN_PASSWORD ]]; then
ADMIN_PASSWORD=$(date| md5sum | fold -w 8 | head -n 1)
echo "##########GENERATED ADMIN PASSWORD: $ADMIN_PASSWORD##########"
fi

# Set the generated password as the new admin password in a temporary glassfishpwd file
echo "AS_ADMIN_PASSWORD=" > /tmp/glassfishpwd
echo "AS_ADMIN_NEWPASSWORD=${ADMIN_PASSWORD}" >> /tmp/glassfishpwd

# Change the current glassfish admin password and start the server on the default domain (domain1)
asadmin --user=admin --passwordfile=/tmp/glassfishpwd change-admin-password --domain_name domain1
asadmin start-domain

echo "AS_ADMIN_PASSWORD=${ADMIN_PASSWORD}" > /tmp/glassfishpwd

# JDBC_CONNECTION_POOL="PostgresPool"
# JDBC_RESOURCE="jdbc/jak_onlineshop"

# Create connection pool with id PostgresPool
# asadmin --user=admin --passwordfile=/tmp/glassfishpwd create-jdbc-connection-pool --steadypoolsize=80 --maxpoolsize=320 --ping=true --datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource --restype javax.sql.ConnectionPoolDataSource --property portNumber=5432:password=shop_12345:user=onlineshop_user:serverName=jak-onlineshop-network:databaseName=jak_onlineshop PostgresPool
asadmin --user=admin --passwordfile=/tmp/glassfishpwd create-jdbc-connection-pool --steadypoolsize=80 --maxpoolsize=320 --ping=true --datasourceclassname org.postgresql.ds.PGConnectionPoolDataSource --restype javax.sql.ConnectionPoolDataSource --property password=shop_12345:user=onlineshop_user:url="jdbc\\:postgresql\\://host.docker.internal/jak_onlineshop" PostgresPool

# Create jdbc resource for prev created connection pool
asadmin --user=admin --passwordfile=/tmp/glassfishpwd create-jdbc-resource --connectionpoolid PostgresPool jdbc/jak_onlineshop

asadmin --user=admin --passwordfile=/tmp/glassfishpwd enable-secure-admin
asadmin --user=admin stop-domain
rm /tmp/glassfishpwd

exec "$@"
