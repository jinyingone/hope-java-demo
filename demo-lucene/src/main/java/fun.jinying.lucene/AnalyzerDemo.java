package fun.jinying.lucene;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

/**
 * @author jy
 * @date 2021/4/20
 */
public class AnalyzerDemo {
    public static void main(String[] args) throws IOException {
        StandardAnalyzer standardanalyzer = new StandardAnalyzer();
        String text = "a:b#c";
        TokenStream tokenStream = standardanalyzer.tokenStream("xxx", text);
        CharTermAttribute attr = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            System.out.println(attr.toString());
        }
    }
}
