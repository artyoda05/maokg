package lab4;

import com.sun.j3d.utils.geometry.*;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.vecmath.Color3f;

public class PlaneBuilder {
    private static int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;

    public static Primitive getCylinder() {
        return new Cylinder(0.1f, 0.6f, primflags,  getGrayAppearance());
    }

    public static Primitive getSphere() {
        return new Sphere(0.1f, primflags, getGrayAppearance());
    }

    public static Primitive getCone() {
        return new Cone(0.1f, 0.15f, primflags, getGrayAppearance());
    }

    public static Primitive getWings() {
        return new Box(0.1f, 0.006f, 0.7f, primflags, getDarkGrayAppearance());
    }

    public static Primitive getSmallWings() {
        return new Box(0.03f, 0.006f, 0.2f, primflags, getDarkGrayAppearance());
    }

    public static Primitive getTail() {
        return new Box(0.08f, 0.15f, 0.006f, primflags, getDarkGrayAppearance());
    }

    public static Primitive getWheel() {
        return new Cylinder(0.025f, 0.01f, primflags, getBlackAppearance());
    }

    public static Primitive getWindows() {
        return new Cylinder(0.02f, 0.2f, primflags,  getBlueAppearance());
    }

    public static Primitive getFrontWindow() {
        return new Box(0.018f, 0.012f, 0.02f, primflags, getBlueAppearance());
    }

    public static Primitive getOuterEngine() {
        return new Cylinder(0.035f, 0.1f, primflags,  getDarkGrayAppearance());
    }

    public static Primitive getInnerEngine() {
        return new Cylinder(0.03f, 0.12f, primflags,  getBlackAppearance());
    }

    private static Appearance getGrayAppearance() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f ambient = new Color3f(0.9f, 0.9f, 0.9f);
        Color3f diffuse = new Color3f(0.2f, 0.2f, 0.2f);
        Color3f specular = new Color3f(0.55f, 0.55f, 0.55f);
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    private static Appearance getDarkGrayAppearance() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f ambient = new Color3f(0.5f, 0.5f, 0.5f);
        Color3f diffuse = new Color3f(0.1f, 0.1f, 0.1f);
        Color3f specular = new Color3f(0.3f, 0.3f, 0.3f);
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    private static Appearance getBlackAppearance() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f ambient = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f diffuse = new Color3f(0.2f, 0.2f, 0.2f);
        Color3f specular = new Color3f(0.55f, 0.55f, 0.55f);
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }

    private static Appearance getBlueAppearance() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f ambient = new Color3f(0.0f, 0.0f, 0.9f);
        Color3f diffuse = new Color3f(0.0f, 0.0f, 0.8f);
        Color3f specular = new Color3f(0.0f, 0.0f, 0.8f);
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
    }
}
