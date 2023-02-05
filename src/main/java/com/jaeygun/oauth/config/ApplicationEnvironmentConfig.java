package com.jaeygun.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationEnvironmentConfig {

    private final Environment env;

    @Autowired
    public ApplicationEnvironmentConfig(Environment env) {
        this.env = env;
    }

    public String getConfigValue(String config) {
        String result = env.getProperty(config);
        return result;
    }

}
