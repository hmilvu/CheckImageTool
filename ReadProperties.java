import java.io.*;
import java.util.*;


public class ReadProperties {


public static Properties getPropertiesObj(String strFilePath) {

Properties proRe = null;

InputStream is;

proRe = new Properties();

is = ReadProperties.class.getClassLoader().getResourceAsStream(

strFilePath);

if (is == null) {

System.out.println("Property file is null.");

return null;

}

try {

proRe.load(is);

} catch (IOException e) {

e.printStackTrace();
return null;

}

try {

is.close();

} catch (IOException e) {

e.printStackTrace();

return null;

}

return proRe;

}

}