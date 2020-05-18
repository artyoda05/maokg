package lab4;

import javax.media.j3d.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

public class Plane {
    private TransformGroup objectTransformGroup;
    private Transform3D planeTransform3D = new Transform3D();
    private float angle = 0;

    public BranchGroup createSceneGraph() {
        BranchGroup objRoot = new BranchGroup();

        objectTransformGroup = new TransformGroup();
        objectTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        makeObj();
        objRoot.addChild(objectTransformGroup);

        //налаштовуємо освітлення
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                100.0);
        Color3f light1Color = new Color3f(0.6f, 0.6f, 0.8f);
        Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        objRoot.addChild(light1);

        // встановлюємо навколишнє освітлення
        Color3f ambientColor = new Color3f(1f, 1f, 1f);
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        objRoot.addChild(ambientLightNode);

        return  objRoot;
    }

    public void makeObj() {
        //body
        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.rotX(Math.PI/2);
            transform3D.rotZ(Math.PI/2);
            transform3D.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getCylinder());
            objectTransformGroup.addChild(transformGroup);
        }

        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.setTranslation(new Vector3f(0.3f, 0f, 0.0f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getSphere());
            objectTransformGroup.addChild(transformGroup);
        }

        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.rotX(Math.PI/2);
            transform3D.rotZ(Math.PI/2);
            transform3D.setTranslation(new Vector3f(-0.375f, 0f, 0.0f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getCone());
            objectTransformGroup.addChild(transformGroup);
        }

        //wings
        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.setTranslation(new Vector3f(0.f, -0.03f, 0.0f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getWings());
            objectTransformGroup.addChild(transformGroup);
        }

        //wheels
        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.rotX(Math.PI/2);
            transform3D.setTranslation(new Vector3f(0.3f, -0.1f, 0.0f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getWheel());
            objectTransformGroup.addChild(transformGroup);
        }

        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.rotX(Math.PI/2);
            transform3D.setTranslation(new Vector3f(-0.21f, -0.095f, 0.05f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getWheel());
            objectTransformGroup.addChild(transformGroup);
        }

        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.rotX(Math.PI/2);
            transform3D.setTranslation(new Vector3f(-0.21f, -0.095f, -0.05f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getWheel());
            objectTransformGroup.addChild(transformGroup);
        }

        //tail
        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.setTranslation(new Vector3f(-0.45f, 0f, 0.0f));
            transformGroup.setTransform(transform3D);
            transformGroup.addChild(PlaneBuilder.getSmallWings());
            objectTransformGroup.addChild(transformGroup);
        }

        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.setTranslation(new Vector3f(-0.4f, 0.08f, 0.0f));
            transformGroup.setTransform(transform3D);
            transformGroup.addChild(PlaneBuilder.getTail());
            objectTransformGroup.addChild(transformGroup);
        }

        //windows
        for (float i = -0.2f; i <= 0.3f; i += 0.06f)
        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.rotX(Math.PI/2);
            transform3D.setTranslation(new Vector3f(i, 0.015f, 0.0f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getWindows());
            objectTransformGroup.addChild(transformGroup);
        }

        float[] enginesPositions = new float[] {-0.4f, -0.25f, 0.25f, 0.4f};

        //engines
        for(int i = 0; i < enginesPositions.length; i++)
        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.rotX(Math.PI/2);
            transform3D.rotZ(Math.PI/2);
            transform3D.setTranslation(new Vector3f(0.0f, -0.07f, enginesPositions[i]));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getOuterEngine());
            objectTransformGroup.addChild(transformGroup);
        }

        for(int i = 0; i < enginesPositions.length; i++)
        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.rotX(Math.PI/2);
            transform3D.rotZ(Math.PI/2);
            transform3D.setTranslation(new Vector3f(0.0f, -0.07f, enginesPositions[i]));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getInnerEngine());
            objectTransformGroup.addChild(transformGroup);
        }

        //front windows
        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.setTranslation(new Vector3f(0.381f, 0.015f, 0.023f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getFrontWindow());
            objectTransformGroup.addChild(transformGroup);
        }

        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.setTranslation(new Vector3f(0.381f, 0.015f, -0.023f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getFrontWindow());
            objectTransformGroup.addChild(transformGroup);
        }

        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.setTranslation(new Vector3f(0.36f, 0.015f, 0.068f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getFrontWindow());
            objectTransformGroup.addChild(transformGroup);
        }

        {
            Transform3D transform3D = new Transform3D();
            TransformGroup transformGroup = new TransformGroup();
            transform3D.setTranslation(new Vector3f(0.36f, 0.015f, -0.068f));
            transformGroup.setTransform(transform3D);

            transformGroup.addChild(PlaneBuilder.getFrontWindow());
            objectTransformGroup.addChild(transformGroup);
        }
    }

    public void rotate() {
        planeTransform3D.rotY(angle);
        angle -= 0.02;
        objectTransformGroup.setTransform(planeTransform3D);
    }
}
