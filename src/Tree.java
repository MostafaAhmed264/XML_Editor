package xml.xmleditor;

public class Tree {
    TreeNode root;
    TreeNode currentNode;

    int test =0;
    public void addNode(StringBuilder nodeName ,int l ){
        if (root == null){
            root = new TreeNode(nodeName,l,null);
            currentNode = root;

        }
        else {
            if (l> currentNode.getLevel()) {

                TreeNode t = new TreeNode(nodeName, l, currentNode);
                currentNode.children.add(t);
                currentNode = t;
            }
            else if(l== currentNode.getLevel()){

                currentNode=currentNode.getParent();
                TreeNode t = new TreeNode(nodeName, l, currentNode);
                currentNode.children.add(t);
                currentNode = t;

            }
            else if(l< currentNode.getLevel()){
                while(l != currentNode.getLevel())
                {
                    currentNode = currentNode.getParent();
                }
                if(currentNode.getLevel()==0){
                    TreeNode t=new TreeNode(nodeName,l,currentNode);
                    currentNode.children.add(t);
                    currentNode = t;
                }
                else {
                    currentNode = currentNode.getParent();

                    TreeNode t = new TreeNode(nodeName, l, currentNode);
                    currentNode.children.add(t);
                    currentNode = t;
                }
            }
        }

    }
    public void constructTree(String file){
        int i = 0;
        int c=0;


        while (i<file.length()-1) {
            int j = 0;
            boolean hasData = false;
            while (file.charAt(i) != '<' && i < file.length() - 1) {
                if (file.charAt(i) != ' ' && file.charAt(i) != '>' && file.charAt(i) != '\n' && file.charAt(i) != '\r') {
                    hasData = true;
                }
                if (hasData && file.charAt(i) != '\r' && file.charAt(i) != '\n') {
                    currentNode.data.append(file.charAt(i));
                }
                i++;
            }
            if (currentNode != null && file.charAt(i) == '<') {

                int k = currentNode.data.length() - 1;
                while (currentNode.data.charAt(k) == ' ' ||currentNode.data.charAt(k) == '\t') {
                    currentNode.data.deleteCharAt(k);
                    k--;

                }
                if (currentNode.data.charAt(currentNode.data.length()-1)!='"')
                    currentNode.data.append('"');
            }



            if(i<file.length()-1)
            { i++;}
            if (file.charAt(i)!='/'){

                StringBuilder s = new StringBuilder();
                while (file.charAt(i)!='>' &&i<file.length()-1){
                    s.append(file.charAt(i));
                    i++;

                }
                if(!s.isEmpty())
                    addNode(s,c);
                c++;
            }
            else if (file.charAt(i)=='/' && i<file.length()-1){
                // currentNode.data.append(data.toString());
                c--;
                while (file.charAt(i)!='>'){
                    i++;
                }


            }

        }


    }
    public void printTree(TreeNode ptr){
        System.out.println(ptr.getNodeName());
        if (ptr.children.size() ==0){
            return;
        }


        for (int i =0;i<ptr.getNumChildren();++i){
            printTree(ptr.children.get(i));


        }
    }
    public void getTest(){
        System.out.println(test);
    }
    public int get_Level(StringBuilder s,TreeNode ptr){
        int z=-1;
        if(ptr.getNodeName().toString().equals(s.toString())) {
            z = ptr.getLevel();
            return z;
        }
        else if (ptr.children.size() ==0){
            return -1;
        }
        for (int i =0;i<ptr.getNumChildren();++i){
            if(z!= -1){
                break;
            }
            z=get_Level(s,ptr.children.get(i));

        }
        return z;
    }
    public TreeNode get_Node(StringBuilder s,TreeNode ptr){
        if(ptr.getNodeName().toString().equals(s.toString())) {
            return ptr;
        }
        else if (ptr.children.size() ==0){
            return null;
        }
        for (int i =0;i<ptr.getNumChildren();++i){
            if(ptr!= null){
                break;
            }
            ptr=get_Node(s,ptr.children.get(i));

        }
        return ptr;
    }
}
