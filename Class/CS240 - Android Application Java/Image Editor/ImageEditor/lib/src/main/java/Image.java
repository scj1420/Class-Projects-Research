import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;


/**
 * Created by Seong-EunCho on 1/15/17.
 */

public class Image {
    private String MagicNumber; //P3
    private int MaximumColorValue; //255
    private Picture currentPicture;
    private String fileName;

    private Vector<String> data;



    public Image(String name){
        fileName = name;

    }

    public void invert(){
        for (int i = 0; i < currentPicture.getHeight(); i++){
            for (int j = 0; j < currentPicture.getWidth(); j++){
                currentPicture.getPic()[i][j].red = 255 - currentPicture.getPic()[i][j].red;
                currentPicture.getPic()[i][j].green = 255 - currentPicture.getPic()[i][j].green;
                currentPicture.getPic()[i][j].blue = 255 - currentPicture.getPic()[i][j].blue;
            }
        }
    }

    public void grayscale(){
        for (int i = 0; i < currentPicture.getHeight(); i++){
            for (int j = 0; j < currentPicture.getWidth(); j++){
                int sum = currentPicture.getPic()[i][j].red + currentPicture.getPic()[i][j].green + currentPicture.getPic()[i][j].blue;
                int average = sum/3;
                currentPicture.getPic()[i][j].red = average;
                currentPicture.getPic()[i][j].green = average;
                currentPicture.getPic()[i][j].blue = average;
            }
        }
    }

    public void emboss(){

        Picture embossedPic = new Picture(currentPicture.getWidth(), currentPicture.getHeight());
        for (int i = 0; i < embossedPic.getHeight(); i++){
            for (int j = 0; j < embossedPic.getWidth(); j++){
                Pixel p = new Pixel();
                p.red = 128;
                p.green = 128;
                p.blue = 128;
                embossedPic.addPixel(p, j, i);
            }
        }

        for (int i = 1; i < currentPicture.getHeight(); i++){
            for (int j = 1; j < currentPicture.getWidth(); j++){
                int redDiff = currentPicture.getPic()[i][j].red - currentPicture.getPic()[i-1][j-1].red;
                int greenDiff = currentPicture.getPic()[i][j].green - currentPicture.getPic()[i-1][j-1].green;
                int blueDiff = currentPicture.getPic()[i][j].blue - currentPicture.getPic()[i-1][j-1].blue;

                int maxDiff = redDiff;
                if(Math.abs(maxDiff) < Math.abs(greenDiff)) {
                    maxDiff = greenDiff;
                }
                if(Math.abs(maxDiff) < Math.abs(blueDiff)) {
                    maxDiff = blueDiff;
                }
                int v = 128 + maxDiff;
                if (v < 0){
                    v = 0;
                }
                else if (v > 255){
                    v = 255;
                }

                embossedPic.getPic()[i][j].red = v;
                embossedPic.getPic()[i][j].green = v;
                embossedPic.getPic()[i][j].blue = v;
            }
        }
        currentPicture = embossedPic;
    }

    public void motionblur(int n){
        Picture blurred = new Picture(currentPicture.getWidth(), currentPicture.getHeight());
        blurred = currentPicture;
        for (int i = 0; i < blurred.getHeight(); i++){
            for (int j = 0; j < blurred.getWidth(); j++){
                int l = n;
                int redSum = currentPicture.getPic()[i][j].red;
                int greenSum = currentPicture.getPic()[i][j].green;
                int blueSum = currentPicture.getPic()[i][j].blue;
                int redAve = redSum;
                int greenAve = greenSum;
                int blueAve = blueSum;
                if ((j+l) >= currentPicture.getWidth()){
                    l -= (j + l) - currentPicture.getWidth();
                }
                if (l != 0) {
                    for (int k = 1; k < l; k++) {
                        redSum += currentPicture.getPic()[i][j + k].red;
                        greenSum += currentPicture.getPic()[i][j + k].green;
                        blueSum += currentPicture.getPic()[i][j + k].blue;
                    }
                    redAve = redSum/l;
                    greenAve = greenSum/l;
                    blueAve = blueSum/l;
                }

                blurred.getPic()[i][j].red = redAve;
                blurred.getPic()[i][j].blue = blueAve;
                blurred.getPic()[i][j].green = greenAve;
            }
        }
    }

    public void ImportFile(String name) throws IOException {

        InputStream IS = null;
        BufferedInputStream BIS = null;
        int width;
        int height;
        data = new Vector<>();

        try{
            // open input stream for reading purpose.
            IS = new FileInputStream(name);

            // input stream is converted to buffered input stream
            BIS = new BufferedInputStream(IS);

            // scanner object is initialized with buffered input stream
            Scanner s = new Scanner(BIS);

            s.useDelimiter("(\\s+)(#[^\\n]*\\n)?(\\s*)|(#[^\\n]*\\n)(\\s*)");

            MagicNumber = s.next();

            width = s.nextInt();
            height = s.nextInt();
            currentPicture = new Picture(width, height);

            MaximumColorValue = s.nextInt();
            while (s.hasNext()) {
                for (int i = 0; i < currentPicture.getHeight(); i++) {
                    for (int j = 0; j < currentPicture.getWidth(); j++) {
                        Pixel currentPixel = new Pixel();
                        currentPixel.red = s.nextInt();
                        currentPixel.green = s.nextInt();
                        currentPixel.blue = s.nextInt();
                        currentPicture.addPixel(currentPixel, j, i);
                    }
                }
            }
            s.close();

        }catch(Exception e){
            // if any I/O error occurs
            System.err.println("Invalid arguments");
            e.printStackTrace();
        }finally{
            // releases any system resources associated with the stream
            if(IS!=null)
                IS.close();
            if(BIS!=null)
                BIS.close();
        }
    }

    public void ExportImage(String name){
        FileWriter OS = null;
        BufferedWriter BOS = null;

        try{
            OS = new FileWriter(name);
            BOS = new BufferedWriter(OS);
            PrintWriter pw = new PrintWriter(BOS);
            pw.println(MagicNumber);
            pw.println(currentPicture.getWidth() + " " + currentPicture.getHeight());
            pw.println(MaximumColorValue);
            for (int i = 0; i < currentPicture.getHeight(); i++){
                for (int j = 0; j < currentPicture.getWidth(); j++){
                    pw.println(currentPicture.getPic()[i][j].red);
                    pw.println(currentPicture.getPic()[i][j].green);
                    pw.println(currentPicture.getPic()[i][j].blue);
                }
            }

            pw.close();
        }
        catch (Exception e){
            System.err.println("Failed!");
            e.printStackTrace();
        }

    }

    public String toString(){
        //StringBuilder sb = new StringBuilder();
        //sb.append(fileName);

        System.out.println(data.size());
        for (int i = 0; i < data.size(); i++) {
            //sb.append(data.get(i));
            System.out.println(data.get(i));
        }
        //return sb.toString();
        return fileName;

    }

}
