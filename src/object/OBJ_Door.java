package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject{
        public OBJ_Door(){
        name = "Test";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/testObject.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
