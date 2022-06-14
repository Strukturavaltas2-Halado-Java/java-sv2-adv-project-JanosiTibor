package research.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import research.model.Project;


@Repository
public interface ProjectsRepository extends JpaRepository<Project,Long> {
//    List<Car> findAllByBrandIgnoreCaseAndAgeIsLessThanEqual(String brand, int maxAge);
//    List<Car> findAllByAgeIsLessThanEqual(int maxAge);
//
////    @Query("SELECT c.brand FROM cars c GROUP BY c.brand")
//    @Query(value = "SELECT  DISTINCT brand FROM cars", nativeQuery = true)
//    List<String> getAllBrands();
}
