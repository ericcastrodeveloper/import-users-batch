package br.com.fiap.importusersbatch.mapper;

import org.springframework.batch.item.file.transform.FieldSet;

import java.net.BindException;

/**
 * Interface that mapping fields
 */
public interface FieldSetMapper<T> {

    T mapFieldSet(FieldSet fieldSet) throws BindException;
}
