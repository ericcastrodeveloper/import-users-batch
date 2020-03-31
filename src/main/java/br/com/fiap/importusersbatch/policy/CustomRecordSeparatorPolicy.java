package br.com.fiap.importusersbatch.policy;

import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;

/**
 * Class that has the policy separator
 */
public class CustomRecordSeparatorPolicy implements RecordSeparatorPolicy {
    @Override
    public boolean isEndOfRecord(String s) {
        return s.equals("") || s.length()  < 56;
    }

    @Override
    public String postProcess(String s) {
        return conta_ocorrencias('-', s) > 1 ? "" : s;
    }

    @Override
    public String preProcess(String s) {
        return s;
    }

    public static int conta_ocorrencias(char caracter, String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == caracter) {
                count++;
            }
        }
        return count;
    }
}
