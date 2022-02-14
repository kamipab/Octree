public class QTree implements BroadPhase{
    private final QTreeNode node;

    public QTree(final float worldExtends, int worldDepth) {
        node = new QTreeNode(0,0,worldExtends, worldDepth);
    }


    public void insertObject(final GameObject obj, final Circle collider) {
        node.insertObject(obj.circleCollider);
    }

    @Override
    public void insertObject(GameObject obj) {

    }

    public void clean() {
        node.clean();
    }
}
