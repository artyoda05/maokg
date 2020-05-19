package lab5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.j3d.*;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.vecmath.*;

public class GunshipAnimation implements ActionListener, KeyListener {
    private Button zoomButton;
    private TransformGroup gunship;
    private Transform3D translateTransform;
    private Transform3D rotateTransformX;
    private Transform3D rotateTransformY;
    private Transform3D rotateTransformZ;

    private boolean isRightDirection = false;
    private boolean isLeftDirection = false;
    private boolean isUpDirection = false;
    private boolean isDownDirection = false;

    private float zoom = 1f;
    private float xloc = 0f;
    private float yloc = -1f;

    GunshipAnimation(TransformGroup gunship, Transform3D trans, JFrame frame) {
        zoomButton = new Button("Zoom");
        this.gunship = gunship;
        this.translateTransform = trans;

        rotateTransformX = new Transform3D();
        rotateTransformY = new Transform3D();
        rotateTransformZ = new Transform3D();

        Main.canvas.addKeyListener(this);
        Timer timer = new Timer(50, this);

        Panel p = new Panel();
        p.add(zoomButton);
        frame.add("North", p);
        zoomButton.addActionListener(this);
        zoomButton.addKeyListener(this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == zoomButton){
            zoom = zoom != 1.0f ? 1.0f : 0.5f;
        }
        else {
            translateTransform.setScale(new Vector3d(zoom, zoom, zoom));
            float zloc = -0.5f;
            translateTransform.setTranslation(new Vector3f(xloc, yloc, zloc));
            gunship.setTransform(translateTransform);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Invoked when a key has been typed.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            if (yloc <= 0)
                yloc += 0.03;
            if (!isUpDirection) {
                rotateTransformX.rotX(-Math.PI/2);
                translateTransform.mul(rotateTransformX);
                gunship.setTransform(translateTransform);
                rotateTransformZ.rotZ(-Math.PI);
                translateTransform.mul(rotateTransformZ);
                gunship.setTransform(translateTransform);
                isUpDirection = true;
            }
        }

        if (e.getKeyChar() == 'a') {
            if (xloc >= -1.5)
                xloc -= 0.03;
            if (!isLeftDirection) {
                rotateTransformX.rotX(Math.PI / 8);
                rotateTransformY.rotY(-Math.PI/2);
                translateTransform.mul(rotateTransformY);
                gunship.setTransform(translateTransform);
                translateTransform.mul(rotateTransformX);
                gunship.setTransform(translateTransform);
                isLeftDirection = true;
            }
        }

        if (e.getKeyChar() == 's') {
            if (yloc >= -2.05)
                yloc -= 0.03;
            if (!isDownDirection) {
                rotateTransformX.rotX(Math.PI/2);
                translateTransform.mul(rotateTransformX);
                gunship.setTransform(translateTransform);
                isDownDirection = true;
            }
        }

        if (e.getKeyChar() == 'd') {
            if (xloc <= 1.5)
                xloc += 0.03;
            if (!isRightDirection) {
                rotateTransformX.rotX(Math.PI / 8);
                rotateTransformY.rotY(Math.PI/2);
                translateTransform.mul(rotateTransformY);
                gunship.setTransform(translateTransform);
                translateTransform.mul(rotateTransformX);
                gunship.setTransform(translateTransform);
                isRightDirection = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'w') {
            if (isUpDirection) {
                rotateTransformZ.rotZ(-Math.PI);
                translateTransform.mul(rotateTransformZ);
                gunship.setTransform(translateTransform);
                rotateTransformX.rotX(Math.PI/2);
                translateTransform.mul(rotateTransformX);
                gunship.setTransform(translateTransform);
            }
            isUpDirection = false;
        }

        if (e.getKeyChar() == 'a') {
            if (isLeftDirection) {
                rotateTransformX.rotX(-Math.PI / 8);
                rotateTransformY.rotY(Math.PI/2);
                translateTransform.mul(rotateTransformX);
                gunship.setTransform(translateTransform);
                translateTransform.mul(rotateTransformY);
                gunship.setTransform(translateTransform);
            }
            isLeftDirection = false;
        }

        if (e.getKeyChar() == 's') {
            if (isDownDirection) {
                rotateTransformX.rotX(-Math.PI/2);
                translateTransform.mul(rotateTransformX);
                gunship.setTransform(translateTransform);
            }
            isDownDirection = false;
        }

        if (e.getKeyChar() == 'd') {
            if (isRightDirection) {
                rotateTransformX.rotX(-Math.PI / 8);
                rotateTransformY.rotY(-Math.PI/2);
                translateTransform.mul(rotateTransformX);
                gunship.setTransform(translateTransform);
                translateTransform.mul(rotateTransformY);
                gunship.setTransform(translateTransform);
            }
            isRightDirection = false;
        }
    }
}
