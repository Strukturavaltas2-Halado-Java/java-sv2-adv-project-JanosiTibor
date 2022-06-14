package research.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import research.model.ResearchGroup;

public interface ResearchGroupRepository extends JpaRepository<ResearchGroup,Long> {
}
