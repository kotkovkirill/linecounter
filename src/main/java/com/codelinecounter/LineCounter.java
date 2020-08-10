package com.codelinecounter;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

/**
 * This class is used to count a lines for given reader.
 * Internally it uses state machine implemented in java.io.StreamTokenizer with addition of ignoring empty lines.
 */
public class LineCounter {

    /**
     * Count lines of code from given reader
     * @param reader reader to count lines
     * @return number of lines
     */
    public int countLines(Reader reader) {

        if(reader == null) throw new RuntimeException("Reader must not be null");

        int counter = 0;
        boolean lineNotEmpty = false;

        StreamTokenizer streamTokenizer = new StreamTokenizer(reader);

        streamTokenizer.slashStarComments(true);
        streamTokenizer.slashSlashComments(true);
        streamTokenizer.eolIsSignificant(true);

        try {

            while(streamTokenizer.nextToken() != StreamTokenizer.TT_EOF){
                if(streamTokenizer.ttype != StreamTokenizer.TT_EOL) {
                    lineNotEmpty = true;
                }

                if(streamTokenizer.ttype == StreamTokenizer.TT_EOL) {
                    if(lineNotEmpty) {
                        counter++;
                        lineNotEmpty = false;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return counter;
    }
}
