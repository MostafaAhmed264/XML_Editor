package xml.xmleditor;

public class Converter {
    StringBuilder json = new StringBuilder();
    int l;
    Tree t;


    public Converter(Tree t) {
        this.t = t;
    }


    public StringBuilder convert(){
        json.append("{\r\n");
        recConvert(t.root);
        json.append("\r\n}");
        return json;
    }

    public void tab(int l){
        for (int i =0;i<l+1;i++){
            json.append("\t");
        }
    }


    public void recConvert(TreeNode ptr) {
        int l = ptr.getLevel();
        boolean duplicated = false;
        if(ptr.getParent()!=null) {
            if(ptr.getParent().getNumChildren()>1)
                duplicated = ptr.getParent().children.get(0).getNodeName().toString().equals(ptr.getParent().children.get(1).getNodeName().toString());
        }
        if (l == 0) {
            tab(l);
            json.append("\"" + ptr.getNodeName() + "\"" + ":");
        }
        else if (ptr.getParent().getNumChildren() > 1) {
            if (duplicated) {
                if (ptr == ptr.getParent().children.get(0)) {
                    json.append(" [");
                    if (ptr.getNumChildren() > 0) {
                        json.append("\r\n");
                        tab(l);
                        json.append("{");
                    }
                }
                else if (ptr != ptr.getParent().children.get(0)) {
                    if (ptr.getNumChildren() > 0) {
                        json.append("\r\n");
                        tab(l);
                        json.append("{");
                    }
                }
            }
            else if (!duplicated) {
                json.append("\r\n");
                tab(l);
                json.append("\"" + ptr.getNodeName() + "\"" + ":");
                if (ptr.getNumChildren() > 1) {
                    if(ptr.children.get(0).getNodeName().toString().equals(ptr.children.get(1).getNodeName().toString())){}
                    else{json.append(" {");}
                }
                else if(ptr.getNumChildren() == 1)
                    json.append(" {");
            }
        }
        else if (ptr.getParent().getNumChildren() == 1) {
            json.append("\r\n");
            tab(l);
            json.append("\"" + ptr.getNodeName() + "\"" + ":");
            if (ptr.getNumChildren() > 0) {
                json.append(" {");
            }
            //else aktb data

            //System.out.println(ptr.getNodeName());
        }
        if (ptr.children.size() == 0) {
            if(ptr.getParent().children.get(ptr.getParent().children.size()-1) == ptr)
            {
                //System.out.println(ptr.getData());
                if(duplicated) {
                    tab(l);
                    json.append(ptr.getData());
                }
                else
                    json.append(ptr.getData());
            }

            else
            {
                //System.out.println(ptr.getData());
                if(duplicated) {
                    json.append("\r\n");
                    tab(l);
                    json.append(ptr.getData()).append(",\r\n");
                }
                else
                    json.append(ptr.getData()).append(",");
            }
            return;
        }


        for (int i = 0; i < ptr.getNumChildren(); ++i) {
            recConvert(ptr.children.get(i));

        }
        json.append("\r\n");
        tab(l);
        if(ptr.getNumChildren() > 1 && ptr.getParent()!=null)
        {
            if(ptr.children.get(0).getNodeName().toString().equals(ptr.children.get(1).getNodeName().toString()))
                json.append("\t"+"]");
            else
                json.append("\t"+"}");
            if(ptr!=ptr.getParent().children.get(ptr.getParent().children.size()-1))
                json.append(",");
        }
        else if (ptr.getParent()!=null)
        {
            json.append("\t"+"}");
            if(ptr!=ptr.getParent().children.get(ptr.getParent().children.size()-1))
                json.append(",");
        }
        else
        {
            if(ptr.getNumChildren() > 1)
                json.append("\t"+"]");
            else
                json.append("\t"+"}");
        }
    }
}
