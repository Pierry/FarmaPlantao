package com.github.pierry.farmaplantaoconcordia.repositories;

import com.github.pierry.farmaplantaoconcordia.common.DateHelper;
import com.github.pierry.farmaplantaoconcordia.common.JsonHelper;
import com.github.pierry.farmaplantaoconcordia.domain.Drugstore;
import com.github.pierry.farmaplantaoconcordia.domain.IDrugstoreRepository;
import java.util.List;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

@EBean public class DrugstoreRepository implements IDrugstoreRepository {

  @Bean(JsonHelper.class) JsonHelper jsonHelper;
  @Bean(DateHelper.class) DateHelper dateHelper;

  @Override public Drugstore get() {
    List<Drugstore> stores = jsonHelper.stores();
    if (stores == null) {
      return null;
    }
    for (Drugstore drugstore : stores) {
      if (drugstore.getDate().equals(dateHelper.current())) {
        return drugstore;
      }
    }
    return null;
  }
}
