package research.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import research.model.Project;
import research.model.ResearchGroup;

import java.util.List;


@Repository
public interface ProjectsRepository extends JpaRepository<Project,Long> {
    @Query("FROM Project p WHERE :researchGroup MEMBER p.researchGroupSet")
    List<Project> findProjectsByParticipatingGroup(@Param("researchGroup") ResearchGroup researchGroup);

//    List<Car> findAllByBrandIgnoreCaseAndAgeIsLessThanEqual(String brand, int maxAge);
//    List<Car> findAllByAgeIsLessThanEqual(int maxAge);
//
////    @Query("SELECT c.brand FROM cars c GROUP BY c.brand")
//    @Query(value = "SELECT  DISTINCT brand FROM cars", nativeQuery = true)
//    List<String> getAllBrands();
}
