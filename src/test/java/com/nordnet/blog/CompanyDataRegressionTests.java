package com.nordnet.blog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.shaded.org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

@DisplayName("Regression Test company-app")
class CompanyDataRegressionTests extends IntegrationTestSetup {
	@Autowired
	private WebTestClient testClient;

	@Test
	@DisplayName("Get Nordnet company data on id")
	void getNordnetCompanyData() throws Exception {
		var result = testClient.get().uri("companies/nordnet")
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();

		assertEquals(Path.of("nordnet.json"), result);
	}

	private void assertEquals(Path expectedJsonData, String actual) throws Exception {
		final File file = Path.of("src/test/resources/expected").resolve(expectedJsonData).toFile();
		final String expected = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		JSONAssert.assertEquals(expected, actual, true);
	}

}
