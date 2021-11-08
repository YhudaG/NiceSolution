package nice.com.solution.repository;

import nice.com.solution.model.Rich;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRichRepository extends JpaRepository<Rich, Long> {
}
