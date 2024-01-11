package QRCodeGenerator;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the URL: ");
        String url = sc.nextLine();


        System.out.println("Enter 'ONLY' the name of the QR code image: ");
        String imageName = sc.nextLine();

        try {
            String data = url;

            String pathInProject = "src\\main\\java\\QRCodeGenerator\\";
            String path = pathInProject + imageName + ".png";


            String charset = "UTF-8";
            int height = 500;
            int width = 500;
            generatingQRCode(data, path, charset, height, width);
            System.out.println("QR Code Generated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void generatingQRCode(String data, String path, String charset, int height, int width) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));
    }
}
