package hr.tvz.napredna.java.model.converters;
import hr.tvz.napredna.java.model.Filter;
import hr.tvz.napredna.java.model.FilterFormModel;
import org.springframework.core.convert.converter.Converter;

public class ModelToFilterConverter implements Converter<FilterFormModel, Filter> {

    @Override
    public Filter convert(FilterFormModel model) {
        Filter filter = new Filter();

        if (model.getId() != null)
            filter.setId(model.getId());
        filter.setIme(model.getIme());
        filter.setOpis(model.getOpis());

        return filter;
    }
}
