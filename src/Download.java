import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.List;

public class Download {

    public static List<Quote> download(File url) throws IOException {

        List<Quote> quotes;
        final InputStream in = new FileInputStream(url);
        ObjectMapper map = new ObjectMapper();
        try {
            quotes = (map.readValue(url, new TypeReference<List<Quote>>(){}));
        }
        finally { in.close(); }
        return quotes;
    }
    }

