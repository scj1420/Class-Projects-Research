/**
 * Created by Seong-EunCho on 1/16/17.
 */

public class Picture {
    private int width;
    private int height;
    private Pixel[][] pic;

    public Picture(int w, int h){
        width = w;
        height = h;
        pic = new Pixel[height][width];
    }
    public void addPixel(Pixel p, int w, int h){
        pic[h][w] = p;
    }

    public Pixel[][] getPic(){
        return pic;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
}
