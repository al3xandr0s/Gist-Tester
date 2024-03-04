FROM openjdk:11
RUN apt-get update
RUN apt-get install -y maven



# Copy the pom.xml  and .env files and install dependencies
COPY pom.xml /usr/local/service/pom.xml
COPY .env /usr/local/service/.env
COPY src /usr/local/service/src

# Set the working directory
WORKDIR /usr/local/service

# Build the application
RUN mvn package

CMD ["cucumber", "--require", "features", "--format", "pretty", "--format", "html", "--out", "output/report.html"]

