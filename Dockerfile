# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app
COPY . .

# Install Playwright browser dependencies
RUN mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install-deps"

RUN mvn clean install -DskipTests

# Stage 2: Run tests
FROM maven:3.9.6-eclipse-temurin-21

WORKDIR /app
COPY --from=builder /app .

# Reinstall browser dependencies in final container
RUN mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install-deps"

CMD ["mvn", "test", "-Dsuite=parallel", "-Dheadless=true"]
