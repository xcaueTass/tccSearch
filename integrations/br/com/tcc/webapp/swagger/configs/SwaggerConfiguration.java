package br.com.tcc.webapp.swagger.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@Component
public class SwaggerConfiguration {

	private static final String PATH = "br.com.tcc.webapp.";

	@Autowired
	private ConfigurableEnvironment configurableEnvironment;

	public ConfigurableEnvironment getConfigurableEnvironment() {
		return configurableEnvironment;
	}

	public String getTitle() {
		return configurableEnvironment.getProperty(PATH.concat("title"));
	}

	public String getDescription() {
		return configurableEnvironment.getProperty(PATH.concat("description"));
	}

	public String getVersion() {
		return configurableEnvironment.getProperty(PATH.concat("version"));
	}

	public String getTermsOfServiceUrl() {
		return configurableEnvironment.getProperty(PATH.concat("termsOfServiceUrl"));
	}

	public String getLicense() {
		return configurableEnvironment.getProperty(PATH.concat("license"));
	}

	public String getLicenseUrl() {
		return configurableEnvironment.getProperty(PATH.concat("licenseUrl"));
	}

	public String getBasePackage() {
		return configurableEnvironment.getProperty(PATH.concat("basePackage"));
	}

	public String getVendorExtensions() {
		return configurableEnvironment.getProperty(PATH.concat("vendorExtensions"));
	}

	public String getName() {
		return configurableEnvironment.getProperty(PATH.concat("contact.name"));
	}

	public String getUrl() {
		return configurableEnvironment.getProperty(PATH.concat("contact.url"));
	}

	public String getEmail() {
		return configurableEnvironment.getProperty(PATH.concat("contact.email"));
	}

}
