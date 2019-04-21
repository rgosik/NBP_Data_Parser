package pl.parser.nbp;

import java.util.List;

public class MainClass {

    public static void main(String [ ] args) throws Exception
    {
        InputManager.initCode(args[0]);
        InputManager.initDates(args[1], args[2]);

        Service.getXmlFiles();
    }
}
