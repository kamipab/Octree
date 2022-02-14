import javafx.scene.shape.Circle;

public class GameObject {

        public final Circle circleCollider;


        public GameObject(final float radius) {
            circleCollider = new Circle(radius);
        }
    }
