package matr.covid.api.service;

import java.util.ArrayList;
import java.util.Map;
import matr.covid.api.entities.layer.ZipCode;
import matr.covid.api.readers.git.FipsLoader;
import matr.covid.api.repository.ZipCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author osvaldo
 */
@Service
public class ZipCodeService {
    
    private static final Logger LOG = LoggerFactory.getLogger(ZipCodeService.class);
    @Autowired
    private ZipCodeRepository repository;
    @Autowired
    private FipsLoader loader;
    
    @Transactional
    public void synchronizeZipcodes() {
        
        if (repository.count() == 0) {
            LOG.info("going to syncrhonize zipcodes-fips equivalence");
            Map<String, FipsLoader.FipsZipcode> doLoad = loader.doLoad();
            
            ArrayList<ZipCode> collect = doLoad.values().stream().collect(ArrayList<ZipCode>::new, (l, i) -> {
                ZipCode zc = new ZipCode();
                zc.setClassFp(i.getClassFp());
                zc.setCountyName(i.getCountyName());
                zc.setFips(i.getFips());
                zc.setState(i.getState());
                zc.setZip(i.getZip());
                
                l.add(zc);
            }, ArrayList<ZipCode>::addAll);
            LOG.info("Going to store {} zipcode equivalences", collect.size());
            
            repository.saveAll(collect);
            repository.flush();
        } else {
            LOG.warn("Zipcodes already syncrhonized. skiping.");
        }
        
    }
}
