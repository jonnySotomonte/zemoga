package com.zemoga.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@Configuration
@EnableJdbcRepositories(basePackages = {"com.zemoga.core.repositories.db"})
public class RepositoriesConfig {

}
