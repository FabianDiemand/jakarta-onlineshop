FROM openjdk:11
ENV GLASSFISH_HOME /usr/local/glassfish
ENV GLASSFISH_LIB /usr/local/glassfish/glassfish/domains/domain1/lib/postgresql-42.5.0.jar
ENV PATH ${GLASSFISH_HOME}/bin:${PATH}
ENV GLASSFISH_PKG glassfish-6.2.5.zip
ENV GLASSFISH_URL https://www.eclipse.org/downloads/download.php?file=/ee4j/glassfish/glassfish-6.2.5.zip
ENV POSTGRES_DRIVER_URL https://jdbc.postgresql.org/download/postgresql-42.5.0.jar
ENV POSTGRES_PKG postgresql-42.5.0.jar

RUN mkdir -p ${GLASSFISH_HOME}

WORKDIR ${GLASSFISH_HOME}

# Download Glassfish 6.2.5, unzip it, move it to the predefined location GLASSFISH_HOME and delete the remains
RUN set -x &&\
  curl -fSL ${GLASSFISH_URL} -o ${GLASSFISH_PKG} &&\
  unzip -o ${GLASSFISH_PKG} &&\
  rm -f ${GLASSFISH_PKG} &&\
  mv glassfish6/* ${GLASSFISH_HOME} &&\
  rm -Rf glassfish6

# Download the PostgreSQL JDBC Driver, move it into the lib directory of the Glassfish Server
RUN set -x &&\
  curl -fSL ${POSTGRES_DRIVER_URL} -o ${POSTGRES_PKG} &&\
  mv ${POSTGRES_PKG} ${GLASSFISH_LIB}


# For safety: Create user and group that holds persmissions for glassfish tasks
RUN addgroup glassfish_grp &&\
  adduser --system glassfish &&\
  usermod -G glassfish_grp glassfish &&\
  chown -R glassfish:glassfish_grp ${GLASSFISH_HOME} &&\
  chmod -R 777 ${GLASSFISH_HOME}

# docker-entrypoint.sh does the administrative tasks needed for glassfish to run properly
COPY ./scripts/docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh

USER glassfish

ENTRYPOINT [ "/docker-entrypoint.sh" ]

EXPOSE 4848 8080 8181
CMD ["asadmin", "start-domain", "-v"]