import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class QTreeNode {
    private final int currDepth; // the current depth of this node
    private final Vector center; // the center of this node
    private final QTreeNode[] nodes; // the child nodes

    private final ArrayList<GameObject> objects; // the objects stored at this node

    public QTreeNode(float centerX, float centerY, float halfWidth, int stopDepth) {
        this.currDepth = stopDepth;


        this.center = new Vector(centerX, centerY, 0.0f);

        this.objects = new ArrayList<GameObject>();

        float offsetX = 0.0f;
        float offsetY = 0.0f;

        if (stopDepth > 0) {

            this.nodes = new QTreeNode[4];


            float step = halfWidth * 0.5f;


            for (int i = 0; i < 4; i++) {


                offsetX = (((i & 1) == 0) ? step : -step);
                offsetY = (((i & 2) == 0) ? step : -step);

                nodes[i] = new QTreeNode(centerX + offsetX, centerY + offsetY, step, stopDepth - 1);
            }
        }
        else {
            this.nodes = null;
        }
    }

    public void insertObject(final Circle collider) {
        int index = 0;
        boolean straddle = false;
        float delta;


        final float[] objPos = center.vec;
        final float[] nodePos = center.vec;

        for (int i = 0; i < 2; i++) {

            delta = nodePos[i] - objPos[i];


            if (Math.abs(delta) <= collider.getRadius()) {
                straddle = true;
                break;
            }


            if (delta > 0.0f) {
                index |= (1 << i);
            }
        }

        if (!straddle && currDepth > 0) {
            
            nodes[index].insertObject(collider);
        }
    }

    public void clean() {
        objects.clear();


        if (currDepth > 0) {
            for (int i = 0; i < 4; i++) {
                nodes[i].clean();
            }
        }
    }

}
