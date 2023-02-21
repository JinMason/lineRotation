import java.applet.Applet;
import java.awt.*;
import java.awt.geom.Line2D;

public class LineRotation extends Applet implements Runnable{
    static double lineSize = 50;
    double centerX ;
    double centerY ;
    double oldX;
    double oldY;
    double newX ;
    double newY ;
    @Override
    public void init() {
        System.out.println("init start");
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        System.out.println(centerX);
        this.oldX = centerX + lineSize;
        this.oldY = centerY;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) getGraphics();
        g2.draw(new Line2D.Double(centerX,centerY,newX,newY));
    }

    @Override
    public void run() {
        new Thread(()-> {
            while (true) {
                try {
                    System.out.println("run start");
                    repaint();
                    cal(oldX,oldY);
                    this.oldX = newX;
                    this.oldY = newY;
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void cal(double oldX, double oldY){
        double angle = getAngle(oldX,oldY);
        System.out.println(angle);
        this.newX = centerX + Math.cos(angle + Math.toRadians(1)) * lineSize;
        this.newY= centerY+ Math.sin(angle + Math.toRadians(1)) * lineSize;
    }

    public double getAngle(double x, double y){
        double dx = x - centerX;
        double dy = y - centerY;
        double angle = Math.atan2(dy,dx);
//        double angle = (Math.atan(end.y/end.x)) - Math.atan(start.x/ start.y);
        //arctan 각도 구함
        //sin 높이 y값 구함
        //cos 밑변길이 x값구함
//        double angle = (Math.atan(dy/dx) - Math.atan() * (180/Math.PI);
        return angle ;
    }
    /*
     * 0<d<90 x(+),y(-)
     * 90<d<180 x(+),y(+)
     * 180<d<270 x(-),y(+)
     * 270<d<0 x(-),y(-)
     */


}