package minecraft54.engine.graphics;

import minecraft54.engine.maths.EulerAngle;
import minecraft54.engine.maths.Matrix4f;
import minecraft54.engine.maths.vectors.Vector3f;

public class PerspectiveCamera{


    private final Vector3f position;
    private final EulerAngle rotation;

    private boolean dirtyProjection;
    private final Matrix4f projection;
    private Matrix4f view;

    private float fov,near,far;
    private int width,height;


    public PerspectiveCamera(int width,int height,float near,float far,float fov){
        this.width=width;
        this.height=height;
        this.near=near;
        this.far=far;
        this.fov=fov;

        position=new Vector3f();
        rotation=new EulerAngle();

        projection=new Matrix4f().setToPerspective(width,height,near,far,fov);
        view=new Matrix4f();
    }

    public void update(){
        if(dirtyProjection){
            projection.setToPerspective(width,height,near,far,fov);
            dirtyProjection=false;
        }
        view=Matrix4f.lookAt(position,rotation.direction());
    }

    public void resize(int width,int height){
        this.width=width;
        this.height=height;
        dirtyProjection=true;
    }

    public Matrix4f getView(){
        return view;
    }

    public Matrix4f getProjection(){
        return projection;
    }

    public Vector3f getPosition(){
        return position;
    }

    public EulerAngle getRotation(){
        return rotation;
    }

    public float getNearPlane(){
        return near;
    }

    public void setNearPlane(float near){
        this.near=near;
        dirtyProjection=true;
    }

    public float getFarPlane(){
        return far;
    }

    public void setFarPlane(float far){
        this.far=far;
        dirtyProjection=true;
    }

    public float getFov(){
        return fov;
    }

    public void setFov(float fov){
        this.fov=fov;
        dirtyProjection=true;
    }


}
