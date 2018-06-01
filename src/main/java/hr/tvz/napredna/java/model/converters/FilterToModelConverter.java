package hr.tvz.napredna.java.model.converters;
import hr.tvz.napredna.java.model.*;
import org.springframework.core.convert.converter.Converter;

public class FilterToModelConverter implements Converter<Filter, FilterFormModel> {

@Override
public FilterFormModel convert(Filter filter) {
    FilterFormModel model = new FilterFormModel();

        model.setId(filter.getId());
        model.setIme(filter.getIme());
        model.setOpis(filter.getOpis());

        return model;
        }
}
