package matr.covid.api.repository;

import java.util.List;
import matr.covid.api.entities.LayerData;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author osvaldo
 */
@Repository
public interface LayerDataRepository extends JpaRepository<LayerData, Long> {

    @Query("SELECT ld from LayerData ld WHERE ld.layerId=:layerId AND within(ld.coordinate,:bound)=true")
    List<LayerData> getByLayerIdAndPosition(@Param("layerId") Long layerId, @Param("bound") Geometry bound, Pageable page);

    List<LayerData> findAllByLayerId(@Param("layerId") final Long layerId);

    long deleteByLayerId(@Param("layerId") final Long layerId);

    List<LayerData> findByLayerId(@Param("layerId") final Long id, Pageable page);
}
