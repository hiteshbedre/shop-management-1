package com.mettyoung.shopmanagement.common.converter;

import com.mettyoung.shopmanagement.common.model.ListContainer;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;

public class CsvHttpMessageConverter<E, L extends ListContainer<E>> extends AbstractHttpMessageConverter<L> {

    public CsvHttpMessageConverter() {
        super(new MediaType("text", "csv"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return ListContainer.class.isAssignableFrom(aClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected L readInternal(Class<? extends L> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        final Class<E> elementType = getParameterizedClass(aClass);
        final HeaderColumnNameMappingStrategy strategy = new HeaderColumnNameMappingStrategy();
        strategy.setType(elementType);

        CsvToBean csvToBean = new CsvToBeanBuilder<>(new InputStreamReader(httpInputMessage.getBody()))
                .withMappingStrategy(strategy)
                .build();
        try {
            L instance = aClass.newInstance();
            instance.setItems(csvToBean.parse());
            return instance;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeInternal(L l, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        throw new UnsupportedOperationException("The CsvHttpMessageConverter does not support write to CSV");
    }

    // Let's keep it simple!
    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @SuppressWarnings("unchecked")
    private Class<E> getParameterizedClass(Class<? extends L> aClass) {
        return (Class<E>) ((ParameterizedType) aClass.getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }
}
