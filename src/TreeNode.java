package xml.xmleditor;
import java.util.ArrayList;

public class TreeNode {
    int level;
    StringBuilder nodeName;
    TreeNode parent;
    StringBuilder data = new StringBuilder();
    ArrayList <TreeNode> children ;


    public TreeNode(StringBuilder nodeName , int l ,TreeNode p)  {
        this.nodeName = nodeName;
        level =l;
        parent = p;
        children = new ArrayList <>();
        data.append('"');
    }

    public StringBuilder getNodeName() {
        return nodeName;
    }

    public int getLevel() {
        return level;
    }

    public int getNumChildren() {
        return children.size();
    }

    public TreeNode getParent() {
        return parent;
    }
    public String getData(){
        return data.toString();
    }

}
