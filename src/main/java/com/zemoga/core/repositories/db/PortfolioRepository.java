package com.zemoga.core.repositories.db;

import com.zemoga.core.repositories.db.model.Portfolio;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PortfolioRepository extends CrudRepository<Portfolio, Integer> {

  @Query("SELECT * FROM portfolio WHERE title = :title")
  Optional<Portfolio> findByTitle(@Param("title") String title);

}
