package matr.covid.api.readers.sum;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import matr.covid.api.dto.LayerDataDto;
import matr.covid.api.dto.LayerDto;
import matr.covid.api.entities.layer.LayerData;
import matr.covid.api.readers.DataParser;
import matr.covid.api.readers.git.CovidItemBean;
import matr.covid.api.repository.LayerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author osvaldo
 */
@Component
public class SummarizedDataParser implements DataParser {

    private static final int DELTA = 500;
    @Autowired
    private LayerDataRepository repository;

    @Override
    public LayerDto getLayer() {
        LayerDto layer = new LayerDto();
        layer.setDescription("summarized data from current data repository");
        layer.setId(3l);
        layer.setName("sumarizedResults");
        return layer;
    }

    @Override
    @Transactional
    public List<LayerDataDto> readData() {
        Map<String, CovidItemBean> itemData = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        long delta = (repository.count() / DELTA) + 1; //delta = 20
        for (int i = 0; i < delta; i++) {
            List<LayerData> layerData = repository.findByLayerId(1L, PageRequest.of(i, DELTA));

            layerData.stream().collect(ArrayList<CovidItemBean>::new, (list, l) -> {
                CovidItemBean ii = mapper.convertValue(l.getData(), CovidItemBean.class);
                list.add(ii);
            }, ArrayList<CovidItemBean>::addAll).stream().forEach(item -> {
                String cc = item.getCountry().toLowerCase().trim();
                CovidItemBean get = itemData.get(cc);
                if (get == null) {
                    itemData.put(cc, item);
                } else {
                    itemData.put(cc, summarize(get, item));

                }

            });

        }
        return itemData.values()
                .stream()
                .collect(ArrayList<LayerDataDto>::new, (list, item) -> {
                    LayerDataDto itemDto = new LayerDataDto();
                    itemDto.setData(mapper.convertValue(item, new TypeReference<Map<String, Object>>() {
                    }));
                    list.add(itemDto);
                }, ArrayList<LayerDataDto>::addAll);
    }

    @Override
    public boolean shouldRemoveAllData() {
        return true;
    }

    @Override
    public boolean appliesCoordinates() {
        return false;
    }

    @Override
    public int priority() {
        return 1;
    }

    private CovidItemBean summarize(CovidItemBean get, CovidItemBean item) {
        CovidItemBean cid = new CovidItemBean();
        cid.setActive(get.getActive() + item.getActive());
        cid.setConfirmed(get.getConfirmed() + item.getConfirmed());
        cid.setDeaths(get.getDeaths() + item.getDeaths());
        cid.setRecovered(get.getRecovered() + item.getRecovered());
        cid.setCountry(get.getCountry());
        return cid;
    }

}
