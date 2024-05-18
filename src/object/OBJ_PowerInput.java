package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_PowerInput extends SuperObject {
        public OBJ_PowerInput(){
            name = "PowerInput";
            try{
                image = ImageIO.read(getClass().getResourceAsStream("/objects/powerInput.png"));
            } catch (IOException e){
                e.printStackTrace();
            }
            collision = true;
        }
}
