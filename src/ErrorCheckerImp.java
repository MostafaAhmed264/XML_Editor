package xml.xmleditor;

import java.util.ArrayList;
import java.util.Stack;

public class ErrorCheckerImp {
    static Stack<String> stack = new Stack<>();
    public static int errCount = 0;
    public  static ArrayList<Integer> errorLines = new ArrayList<Integer>();


    public static String errorCheck(String file) {
        StringBuilder newFile = new StringBuilder();
        int file_indx = 0;
        boolean hasData = false;
        int lines = 0;

        while (file_indx < file.length() - 1) {


            while (file.charAt(file_indx) != '<')
            {
                if(file.charAt(file_indx) == '\n')
                    lines++;
                if(file.charAt(file_indx) != '>')
                    newFile.append(file.charAt(file_indx));
                if(file.charAt(file_indx) != '\r' && file.charAt(file_indx) != '\n' && file.charAt(file_indx) != ' ' && file.charAt(file_indx) != '>')
                    hasData = true;
                file_indx++;
            }
            int startOfTag = file_indx + 1;

            while (file.charAt(file_indx) != '>')
            {
                file_indx++;
            }
            int endOfTag = file_indx;

            String tag = file.substring(startOfTag, endOfTag);


            if (tag.charAt(0) == '/')
            {
                if (stack.isEmpty())
                {
                    errorLines.add(lines);
                    errCount++;
                    break;
                }
                else if (tag.substring(1).equals(stack.peek()))
                {
                    stack.pop();
                    newFile.append("<").append(tag).append(">");
                }
                else
                {
                    if(stack.search(tag.substring(1)) == -1)
                    {
                        newFile.append("</").append(stack.peek()).append(">");
                        stack.pop();
                    }
                    else
                    {
                        newFile.append("</").append(stack.peek()).append(">\n");
                        stack.pop();
                        while(!tag.substring(1).equals(stack.peek()))
                        {
                            newFile.append("</").append(stack.peek()).append(">\n");
                            stack.pop();
                        }
                        newFile.append("</").append(stack.peek()).append(">\n");
                        stack.pop();
                    }
                    errorLines.add(lines);
                    errCount++;
                }
            }
            else
            {
                if (stack.empty())
                {
                    stack.push(tag);
                    newFile.append("<").append(tag).append(">");
                }
                else if(hasData)
                {
                    newFile.append("</").append(stack.peek()).append(">\n");
                    stack.pop();
                    newFile.append("<").append(tag).append(">");
                    stack.push(tag);
                    hasData = false;
                    errorLines.add(lines);
                    errCount++;
                }
                else if (!stack.peek().equals(tag))
                {
                    stack.push(tag);
                    newFile.append("<").append(tag).append(">");
                }
                else
                {
                    newFile.append("</").append(tag).append(">");
                    newFile.append("<").append(tag).append(">");
                    errorLines.add(lines);
                    errCount++;
                }
            }
            hasData = false;
        }

        while (!stack.empty())
        {
            newFile.append("</").append(stack.peek()).append(">");
            stack.pop();
            errCount++;
        }

        //System.out.println(newFile);
        //System.out.println("number of errors is " + errCount);
        return newFile.toString();
    }

}
