package br.com.fiap.importusersbatch.mapper;

/**
 * Interface that mapping lines
 */
public interface LineMapper<T> {
    T mapLine(String line, int lineNumber) throws Exception;
}
