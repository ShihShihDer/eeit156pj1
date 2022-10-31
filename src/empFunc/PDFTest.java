package empFunc;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class PDFTest {


        /*File file = new File("D:\\javaSSD\\project1\\P1Data\\1110902PDF\\level\\7_2-111年直轄市山地原住民區長選舉候選人登記彙總表.pdf");
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
//新建一个PDF解析器对象
            PDFParser parser = new PDFParser(in);
//对PDF文件进行解析
            parser.parse();
//获取解析后得到的PDF文档对象
            PDDocument pdfdocument = parser.getPDDocument();
//新建一个PDF文本剥离器
            PDFTextStripper stripper = new PDFTextStripper();
//从PDF文档对象中剥离文本
            String result = stripper.getText(pdfdocument);
            System.out.println("PDF文件" + file.getAbsolutePath() + "的文本内容如下：");
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("读取PDF文件"+ file.getAbsolutePath() + "生失败！" + e);
            e.printStackTrace();
        } finally {
            if (in != null){
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
*/
    /*using (PdfReader reader = new PdfReader(fileName))
    {
        using (PdfDocument pdfDocument = new PdfDocument(reader))
        {
            for (int i = 1; i <= pdfDocument.GetNumberOfPages(); i++)
            {
                string pdfContentString = PdfTextExtractor.GetTextFromPage(pdfDocument.GetPage(i));
                MessageBox.Show(pdfContentString);
            }
        }
}*/
    /*public static void main(String[] args) throws IOException {
        File file = new File("D:\\javaSSD\\project1\\P1Data\\1110902PDF\\level\\7_2-111年直轄市山地原住民區長選舉候選人登記彙總表" +
                ".pdf");
        PdfReader pr = new PdfReader(file);
        PdfDocument pd = new PdfDocument(pr);
        for (int i = 1;i<= pd.getNumberOfPages(); i++){
            String pdf2text = PdfTextExtractor.getTextFromPage(pd.getPage(i).);
            System.out.println(pdf2text);
        }


    }*/


}
