package matr.covid.api.repository;

import java.util.List;
import matr.covid.api.entities.layer.ZipCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author osvaldo
 */
@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCode, Long> {

    @Query("SELECT zc FROM ZipCode zc WHERE zc.zip LIKE %:zipcode%")
    List<ZipCode> searchByCode(String zipcode);

}
