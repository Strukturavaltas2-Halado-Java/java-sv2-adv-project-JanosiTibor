package research.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import research.model.ResearchGroup;

public interface ResearchGroupsRepository extends JpaRepository<ResearchGroup,Long> {
}
