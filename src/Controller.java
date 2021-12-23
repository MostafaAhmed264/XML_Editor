package xml.xmleditor;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private Label welcomeText;
    private String s;
    private String formattedString;
    @FXML
    private TextArea TALeft;
    @FXML
    private TextArea TARight;

    @FXML
    protected void onHelloButtonClick() {
        String s=Reader.Read();
        String z= Compress2.Compress(s);
        System.out.println(Compress2.Decompress(z));
        //System.out.println(z);

    }
    @FXML
    public void Browse()
    {
        s = Reader.Read();
        TALeft.setText(s);
    }
    public void Format()
    {
        formattedString = Formatter.format(s);
        TARight.clear();
        TARight.setText(formattedString);
        //System.out.println(s);
    }


    public void Compress()
    {
        s = Compress2.Compress(s);
        TARight.setText(s + "\nThe Compressed file is on the DESKTOP!");
        Writer.write(s);
    }
    public void Decompress()
    {
        s = Compress2.Decompress(s);
        TARight.setText(s);

    }
    public void ErrorCorrect()
    {
        s = ErrorCheckerImp.errorCheck(s);
        TARight.setText(s + "\n error number is: " + ErrorCheckerImp.errCount +"\n errors at lines" + ErrorCheckerImp.errorLines.toString());
        ErrorCheckerImp.errCount = 0;
        ErrorCheckerImp.errorLines.clear();
    }
    public void Minify()
    {
        s=Minify.Compress(s);
        TARight.setText(s);
    }
    public void Convert()
    {
        Tree t1 = new Tree();
        t1.constructTree(s);
        Converter x = new Converter(t1);
        StringBuilder y=  x.convert();
        TARight.setText(y.toString());
    }
}
