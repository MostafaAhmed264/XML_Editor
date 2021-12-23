package xml.xmleditor;

public class Compress1 {
    static String Compress(String File)
    {
        StringBuilder newFile = new StringBuilder();
        for (int i = 0; i<File.length();i++)
        {
            boolean hasData = false;
            newFile.append(File.charAt(i));
            if(i>= File.length()-1)
                break;
            if(File.charAt(i) == '>')
            {
                int j = i+1;
                while(File.charAt(j) != '<')
                {
                    if(File.charAt(j) == ' ' || File.charAt(j) == '\n' || File.charAt(j) == '\r')
                        j++;
                    else
                    {
                        hasData = true;
                        break;
                    }

                }
                if(!hasData)
                {
                    while(File.charAt(i) != '<')
                    {
                        i++;
                        if(File.charAt(i) == ' ')
                        {
                            continue;
                        }
                        newFile.append(File.charAt(i));
                    }
                }
                else
                {
                    i = j;
                    while(File.charAt(i) != '\r' && File.charAt(i) !='<')
                    {
                        newFile.append(File.charAt(i++));
                    }
                    while(File.charAt(i) != '<')
                    {
                        i++;
                    }
                    if(File.charAt(i) == '<')
                        newFile.append(File.charAt(i));

                }
            }
        }
        return newFile.toString();
    }
}
