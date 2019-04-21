package pl.parser.nbp;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.*;

public class Service {

    static String getDirTxt(int startYear, int endYear) throws Exception{

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        List<String> dirTxt = new ArrayList<String>();
        List<Integer> measuredYears = new ArrayList<Integer>();
        Set<URLConnection> connections = new HashSet<URLConnection>();

        String finalDirTxt = new String();

        for(int i = startYear; i <= endYear; i++) {
            measuredYears.add(i);
        }

        for(int year: measuredYears){
            if( year == currentYear){
                connections.add(new URL("https://www.nbp.pl/kursy/xml/dir.txt").openConnection());
            } else {
                connections.add( new URL("https://www.nbp.pl/kursy/xml/dir" + year + ".txt").openConnection());
            }
        }

        for(URLConnection conn: connections){
            dirTxt.add(new Scanner(conn.getInputStream()).
                    useDelimiter("\\Z").next());
        }

        for(String dirTxtOfYear: dirTxt){
            finalDirTxt = finalDirTxt + dirTxtOfYear + "\n";
        }

        return finalDirTxt;
    }

    static List<String> trimDirTxt(int startDate, int endDate) throws Exception{

        String dirTxt = Service.getDirTxt(InputManager.startYear, InputManager.endYear);

        BufferedReader bufReader = new BufferedReader(new StringReader(dirTxt));
        String xmlName = null;
        List<String> validXmlNames = new ArrayList<String>();

        while((xmlName = bufReader.readLine()) != null )
        {
            if(xmlName.charAt(0) == 'c'){
                int date = Integer.parseInt(xmlName.substring(xmlName.length() - 6));
                if(date >= startDate && date <= endDate){
                    validXmlNames.add(xmlName);
                }
            }
        }
        return validXmlNames;
    }

    static List<File> getXmlFiles() throws Exception{

        List<String> validXmlNames = Service.trimDirTxt(InputManager.formatedStartDate, InputManager.formatedEndDate);

        ReadableByteChannel rbc;
        FileOutputStream xmlFos;

        List<File> xmlFiles = new ArrayList<File>();

        int threads = validXmlNames.size();

        for (int i = 0; i < threads; i++) {

            URL website = new URL("http://www.nbp.pl/kursy/xml/" + validXmlNames.get(i) + ".xml");
            rbc = Channels.newChannel(website.openStream());

            String s = Integer.toString(i);
            xmlFiles.add(new File("file" + s + ".xml"));
            xmlFiles.get(i).deleteOnExit();

            xmlFos = new FileOutputStream(xmlFiles.get(i));
            xmlFos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

            xmlFos.close();
            rbc.close();
        }

        return xmlFiles;
    }

}
