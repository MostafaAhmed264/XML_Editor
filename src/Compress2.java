package xml.xmleditor;

import java.util.ArrayList;

public class Compress2 {
    static ArrayList<String> names = new ArrayList<>();
    static ArrayList<Character> nElements = new ArrayList<>();

    static String Compress(String File)
    {

        char n = '0';
        File = Compress1.Compress(File);
        StringBuilder newFile = new StringBuilder();
        for (int i =0; i < File.length(); i++)
        {
            StringBuilder tag= new StringBuilder();

            newFile.append(File.charAt(i));
            if(File.charAt(i) == '<')
            {
                while(File.charAt(i) != '>')
                {
                    i++;
                    if(File.charAt(i) == '/')
                    {
                        newFile.append(File.charAt(i));
                        continue;
                    }
                    if(File.charAt(i) =='>')
                        continue;

                    tag.append(File.charAt(i));

                }
                if(!names.contains(tag.toString()))
                {
                    names.add(tag.toString());
                    nElements.add(n++);
                }
                int index = names.indexOf(tag.toString());
                newFile.append(nElements.get(index));
                newFile.append(File.charAt(i));// append '>'
            }

        }
        return newFile.toString();
    }
    static String Decompress(String File)
    {
        StringBuilder newFile = new StringBuilder();
        for (int i =0; i < File.length(); i++)
        {
            StringBuilder tag= new StringBuilder();

            newFile.append(File.charAt(i));
            if(File.charAt(i) == '<')
            {
                while(File.charAt(i) != '>')
                {
                    i++;
                    if(File.charAt(i) == '/')
                    {
                        newFile.append(File.charAt(i));
                        continue;
                    }
                    if(File.charAt(i) =='>')
                        continue;

                    tag.append(File.charAt(i));

                }
                int index = nElements.indexOf(tag.toString().charAt(0));
                newFile.append(names.get(index));
                newFile.append(File.charAt(i));// append '>'
            }

        }
        //newFile =newFile.deleteCharAt(newFile.length()-2);
        System.out.println(newFile.charAt(newFile.length()-1));


        return newFile.toString();

    }


}

