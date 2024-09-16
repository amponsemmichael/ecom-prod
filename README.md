# Spring Actuator: Features and Best Practices

## Introduction
Spring Boot Actuator is a sub-project of Spring Boot that adds several production-ready features to your application. It provides built-in endpoints and tools for monitoring and managing your Spring Boot application.

## Setup

To add Spring Actuator to your project, include the following dependency in your `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Configure Actuator in your `application.properties` or `application.yml`:

```properties
# Enable all Actuator endpoints
management.endpoints.web.exposure.include=*

# Customize base path for actuator endpoints (optional)
management.endpoints.web.base-path=/management
```

## Key Features and Code Examples

1. Health Checks
   - Provides information about application health
   - Customizable for specific needs

Example of a custom health indicator:

```java
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode).build();
        }
        return Health.up().build();
    }

    private int check() {
        // Your logic to check health
        return 0;
    }
}
```

2. Metrics
   - Exposes various metrics about the application
   - Integrates with monitoring systems like Prometheus

Example of creating a custom metric:

```java
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class CustomMetricService {

    private final Counter customCounter;

    public CustomMetricService(MeterRegistry meterRegistry) {
        this.customCounter = Counter.builder("custom.metric")
                                    .description("A custom metric")
                                    .register(meterRegistry);
    }

    public void incrementCustomMetric() {
        this.customCounter.increment();
    }
}
```

3. Info Endpoint
   - Displays arbitrary application information

Example of customizing the info endpoint:

```java
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("example", "This is custom info")
               .withDetail("version", "1.0.0");
    }
}
```

## Best Practices

1. Security
   - Secure actuator endpoints, especially in production
   - Use Spring Security to control access

Example of securing Actuator endpoints:

```java
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatcher(EndpointRequest.toAnyEndpoint())
            .authorizeRequests()
            .anyRequest().hasRole("ACTUATOR")
            .and()
            .httpBasic();
    }
}
```

2. Customization
   - Customize endpoints to expose only necessary information
   - Create custom health indicators for application-specific health checks

3. Metrics
   - Use micrometer for consistent metrics collection
   - Choose appropriate metrics for your application's needs

4. Monitoring
   - Integrate with monitoring tools (e.g., Prometheus, Grafana)
   - Set up alerts for critical metrics

5. Documentation
   - Document custom endpoints and metrics
   - Provide clear guidelines for operations team

6. Performance
   - Be mindful of the performance impact of enabled endpoints
   - Use sampling for high-volume metrics

7. Versioning
   - Include application version in the info endpoint
   - Use git commit information for precise versioning

8. Healthchecks
   - Implement meaningful health checks for external dependencies
   - Use the health endpoint for load balancer checks

By following these best practices and utilizing the code examples provided, you can effectively leverage Spring Actuator to enhance the manageability and observability of your Spring Boot applications.
