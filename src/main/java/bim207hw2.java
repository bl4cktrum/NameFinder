import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;


public class bim207hw2 {
    public static void main(String[] args) throws IOException {
        /*
         * Load sentence detector model
         */
        InputStream SentenceModelIn = bim207hw2.class.getResourceAsStream("en-sent.bin");
        SentenceModel sentenceModel = new SentenceModel(SentenceModelIn);
        SentenceDetectorME sentenceDetector = new SentenceDetectorME(sentenceModel);

        /*
         * Load tokenizer model
         */
        InputStream TokenizerModelIn = bim207hw2.class.getResourceAsStream("en-token.bin");
        TokenizerModel tokenizerModel = new TokenizerModel(TokenizerModelIn);
        Tokenizer tokenizer = new TokenizerME(tokenizerModel);

        /*
         * Load name finder model
         */
        InputStream NameFinderModelIn = bim207hw2.class.getResourceAsStream("en-ner-person.bin");
        TokenNameFinderModel nameFinderModel = new TokenNameFinderModel(NameFinderModelIn);
        NameFinderME nameFinder = new NameFinderME(nameFinderModel);

        /*
         * Connect url and get text
         */
        Document doc = null;
        try {
            doc = Jsoup.connect(args[0]).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element body = doc.body();  // Get body contents
        String text = body.text();  // Convert body to text


        String[] sentences = sentenceDetector.sentDetect(text);  // Detect sentences from text

        for(String sentence : sentences){

            String[] tokens = tokenizer.tokenize(sentence);//Tokenize each sentence
            Span [] nameS = nameFinder.find(tokens);//find names in each tokenized sentence

            for(Span s: nameS) {
                String name ="";
                for (int i = s.getStart(); i < s.getEnd(); i++)
                    name +=tokens[i]+" ";   //take full name
                System.out.println(name.trim());    //delete white space in the end
                nameFinder.clearAdaptiveData();   //clear adaptive data to prevent drop in speed
            }
        }
    }
}