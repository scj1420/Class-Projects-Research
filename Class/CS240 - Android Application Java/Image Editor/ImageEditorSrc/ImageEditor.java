import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * Created by Seong-EunCho on 1/12/17.
 */

public class ImageEditor {
    public static void main(String[] args){
        for(int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
        Image im1 = new Image(args[0]);
        try {
            im1.ImportFile(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (args[2].equals("invert")){
            im1.invert();
        }
        else if (args[2].equals("grayscale")){
            im1.grayscale();
        }
        else if (args[2].equals("emboss")){
            im1.emboss();
        }
        else if (args[2].equals("motionblur")){
            if (Integer.parseInt(args[3]) <= 0){
                System.err.println("Invalid arguments");
                return;
            }
            else {
                im1.motionblur(Integer.parseInt(args[3]));
            }
        }
        else{
            System.err.println("Invalid arguments");
            return;
        }

        im1.ExportImage(args[1]);

    }
}
