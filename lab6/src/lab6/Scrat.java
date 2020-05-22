package lab6;

import javax.vecmath.*;

import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.behaviors.vp.*;
import javax.swing.JFrame;
import com.sun.j3d.loaders.*;
import com.sun.j3d.loaders.objectfile.*;
import java.util.Hashtable;
import java.util.Enumeration;

public class Scrat extends JFrame{
    private Canvas3D myCanvas3D;

    public Scrat() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        SimpleUniverse simpUniv = new SimpleUniverse(myCanvas3D);

        simpUniv.getViewingPlatform().setNominalViewingTransform();

        createSceneGraph(simpUniv);
        addLight(simpUniv);

        OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
        ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
        simpUniv.getViewingPlatform().setViewPlatformBehavior(ob);

        setTitle("Scrat");
        setSize(1200,645);
        getContentPane().add("Center", myCanvas3D);
        setVisible(true);
    }

    private void createSceneGraph(SimpleUniverse su){
        ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
        BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);
        String name;
        BranchGroup scratBranchGroup = new BranchGroup();
        TextureLoader t = new TextureLoader("model/glacier.jpg", myCanvas3D);
        Background scratBackground = new Background(t.getImage());

        Scene scratScene = null;
        try{
            scratScene = f.load("model/scrat.obj");
        }
        catch (Exception e){
            System.out.println("File loading failed:" + e);
        }
        Hashtable roachNamedObjects = scratScene.getNamedObjects();
        Enumeration enumer = roachNamedObjects.keys();
        while (enumer.hasMoreElements()){
            name = (String) enumer.nextElement();
            System.out.println("Name: " + name);
        }

        // start animation
        Transform3D startTransformation = new Transform3D();
        startTransformation.setScale(2.0/6);
        Transform3D combinedStartTransformation = new Transform3D();
        combinedStartTransformation.mul(startTransformation);

        TransformGroup scratStartTransformGroup = new TransformGroup(combinedStartTransformation);

        // moves for 0,3 seconds
        int movesDuration = 700;
        // launch animation after timeStart seconds
        int startTime = 0;
        // moves count
        int movesCount = 100;

        // tale
        Appearance taleApp = new Appearance();
        setToMyDefaultAppearance(taleApp, new Color3f(0.3686f, 0.3176f, 0.2549f));
        Alpha taleRotAlpha = new Alpha(movesCount, Alpha.INCREASING_ENABLE, startTime, 0, movesDuration * 2,0,0,0,0,0);

        Shape3D tale = (Shape3D) roachNamedObjects.get("tale");
        tale.setAppearance(taleApp);
        TransformGroup taleTG = new TransformGroup();
        taleTG.addChild(tale.cloneTree());

        Transform3D taleRotAxis = new Transform3D();
        taleRotAxis.set(new Vector3d(0.0, 0.0, 0.0));
        taleRotAxis.setRotation(new AxisAngle4d(0, 0, -0.1, Math.PI/2));

        RotationInterpolator taleRot = new RotationInterpolator(taleRotAlpha, taleTG, taleRotAxis, 0.0f, (float) Math.PI*2);
        taleRot.setSchedulingBounds(bs);
        taleTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        taleTG.addChild(taleRot);

        Appearance bodyAppearance = new Appearance();
        setToMyDefaultAppearance(bodyAppearance, new Color3f(0.5764f, 0.3294f, 0.1607f));

        // right_hand
        Alpha rHandAlpha = new Alpha(movesCount, Alpha.INCREASING_ENABLE, startTime, 0, movesDuration,0,0,0,0,0);

        Shape3D rightHand = (Shape3D) roachNamedObjects.get("right_hand");
        rightHand.setAppearance(bodyAppearance);
        TransformGroup rightHandTG = new TransformGroup();
        rightHandTG.addChild(rightHand.cloneTree());

        Transform3D rightHandRotAxis = new Transform3D();
        rightHandRotAxis.rotZ(Math.PI / 8);

        RotationInterpolator wheel2rot = new RotationInterpolator(rHandAlpha, rightHandTG, rightHandRotAxis, 0.0f, (float) Math.PI/2); // Math.PI*2
        wheel2rot.setSchedulingBounds(bs);
        rightHandTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        rightHandTG.addChild(wheel2rot);

        // left_hand
        Alpha lHandAlpha = new Alpha(movesCount, Alpha.INCREASING_ENABLE, startTime, 0, movesDuration,0,0,0,0,0);

        Shape3D leftHand = (Shape3D) roachNamedObjects.get("left_hand");
        leftHand.setAppearance(bodyAppearance);
        TransformGroup leftHandTG = new TransformGroup();
        leftHandTG.addChild(leftHand.cloneTree());

        Transform3D leftHandRotAxis = new Transform3D();
        leftHandRotAxis.rotZ(Math.PI / 8);

        RotationInterpolator leftHandRot = new RotationInterpolator(lHandAlpha, leftHandTG, leftHandRotAxis, 0.0f,(float) -Math.PI/2);
        leftHandRot.setSchedulingBounds(bs);
        leftHandTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        leftHandTG.addChild(leftHandRot);

        // body
        TransformGroup sceneGroup = new TransformGroup();
        sceneGroup.addChild(taleTG);
        sceneGroup.addChild(rightHandTG);
        sceneGroup.addChild(leftHandTG);

        TransformGroup tgBody = new TransformGroup();
        Shape3D nShape = (Shape3D) roachNamedObjects.get("body");
        nShape.setAppearance(bodyAppearance);
        tgBody.addChild(nShape.cloneTree());
        sceneGroup.addChild(tgBody.cloneTree());

        Appearance eyeApp = new Appearance();
        setToMyDefaultAppearance(eyeApp, new Color3f(1.0f, 1.0f, 1.0f));

        Appearance noseApp = new Appearance();
        setToMyDefaultAppearance(noseApp, new Color3f(0.0f, 0.0f, 0.0f));

        //left eye
        TransformGroup tgLeftEye = new TransformGroup();
        Shape3D leftEyeShape = (Shape3D) roachNamedObjects.get("left_eye");
        leftEyeShape.setAppearance(eyeApp);
        tgLeftEye.addChild(leftEyeShape.cloneTree());
        sceneGroup.addChild(tgLeftEye.cloneTree());

        TransformGroup tgLeftEye1 = new TransformGroup();
        Shape3D leftEyeShape1 = (Shape3D) roachNamedObjects.get("left_eye1");
        leftEyeShape1.setAppearance(noseApp);
        tgLeftEye1.addChild(leftEyeShape1.cloneTree());
        sceneGroup.addChild(tgLeftEye1.cloneTree());

        //right eye
        TransformGroup tgRightEye = new TransformGroup();
        Shape3D rightEyeShape = (Shape3D) roachNamedObjects.get("right_eye");
        rightEyeShape.setAppearance(eyeApp);
        tgRightEye.addChild(rightEyeShape.cloneTree());
        sceneGroup.addChild(tgRightEye.cloneTree());

        TransformGroup tgRightEye1 = new TransformGroup();
        Shape3D rightEyeShape1 = (Shape3D) roachNamedObjects.get("right_eye1");
        rightEyeShape1.setAppearance(noseApp);
        tgRightEye1.addChild(rightEyeShape1.cloneTree());
        sceneGroup.addChild(tgRightEye1.cloneTree());

        //nose
        TransformGroup tgLNose = new TransformGroup();
        Shape3D noseShape1 = (Shape3D) roachNamedObjects.get("nose");
        noseShape1.setAppearance(noseApp);
        tgLNose.addChild(noseShape1.cloneTree());
        sceneGroup.addChild(tgLNose.cloneTree());

        Appearance nutApp = new Appearance();
        setToMyDefaultAppearance(nutApp, new Color3f(0.3647f, 0.2627f, 0.1725f));

        //nut
        TransformGroup tgNut = new TransformGroup();
        Shape3D nutShape = (Shape3D) roachNamedObjects.get("nut");
        nutShape.setAppearance(nutApp);
        tgNut.addChild(nutShape.cloneTree());
        sceneGroup.addChild(tgNut.cloneTree());

        TransformGroup whiteTransXformGroup = translate(
                scratStartTransformGroup,
                new Vector3f(0.0f,0.0f,-0.5f));

        TransformGroup whiteRotXformGroup = rotate(whiteTransXformGroup, new Alpha(10, 6500));
        scratBranchGroup.addChild(whiteRotXformGroup);
        scratStartTransformGroup.addChild(sceneGroup);

        // adding the scrat background to branch group
        BoundingSphere bounds = new BoundingSphere(new Point3d(120.0,250.0,100.0), Double.MAX_VALUE);
        scratBackground.setApplicationBounds(bounds);
        scratBranchGroup.addChild(scratBackground);

        scratBranchGroup.compile();
        su.addBranchGraph(scratBranchGroup);
    }

    private void addLight(SimpleUniverse su){
        BranchGroup bgLight = new BranchGroup();
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        Color3f lightColour1 = new Color3f(1.0f,1.0f,1.0f);
        Vector3f lightDir1 = new Vector3f(-1.0f,0.0f,-0.5f);
        DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
        light1.setInfluencingBounds(bounds);
        bgLight.addChild(light1);
        su.addBranchGraph(bgLight);
    }

    private TransformGroup translate(Node node, Vector3f vector){

        Transform3D transform3D = new Transform3D();
        transform3D.setTranslation(vector);
        TransformGroup transformGroup =
                new TransformGroup();
        transformGroup.setTransform(transform3D);

        transformGroup.addChild(node);
        return transformGroup;
    }

    private TransformGroup rotate(Node node, Alpha alpha){
        TransformGroup xformGroup = new TransformGroup();
        xformGroup.setCapability(
                TransformGroup.ALLOW_TRANSFORM_WRITE);

        RotationInterpolator interpolator =
                new RotationInterpolator(alpha, xformGroup);

        interpolator.setSchedulingBounds(new BoundingSphere(
                new Point3d(0.0,0.0,0.0),1.0));

        xformGroup.addChild(interpolator);
        xformGroup.addChild(node);

        return xformGroup;
    }

    private static void setToMyDefaultAppearance(Appearance app, Color3f col) {
        app.setMaterial(new Material(col, col, col, col, 150.0f));
    }

    public static void main(String[] args) {
        new Scrat();
    }

}
