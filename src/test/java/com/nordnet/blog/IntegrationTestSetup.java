package com.nordnet.blog;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@SpringBootTest(classes = {Application.class}, webEnvironment = RANDOM_PORT)
public class IntegrationTestSetup {

    private static final String REDIS_IMG = "redis:6.2.7-alpine";
    private static final MountableFile FILE_EUROPE = MountableFile.forClasspathResource("redis/dump.rdb");
    private static final String DUMP_FILE_REDIS_CONTAINER = "/data/dump.rdb";
    private static final int REDIS_PORT = 6379;
    @Container
    static GenericContainer redis = new GenericContainer(DockerImageName.parse(REDIS_IMG))
            .withExposedPorts(REDIS_PORT)
            .withCopyFileToContainer(FILE_EUROPE, DUMP_FILE_REDIS_CONTAINER);

    @DynamicPropertySource
    static void setRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", () -> redis.getHost());
        registry.add("spring.redis.port", () -> redis.getFirstMappedPort());
    }
}
