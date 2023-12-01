package com.lams.loaring.adventurer.infra;

import com.lams.loaring.adventurer.domain.Adventurer;
import com.lams.loaring.adventurer.domain.Email;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventurerRepository extends JpaRepository<Adventurer, Long> {

	@EntityGraph(attributePaths = "adventureAuthority")
	Optional<Adventurer> findByEmail(Email email);

	boolean existsByEmail(Email email);

}
