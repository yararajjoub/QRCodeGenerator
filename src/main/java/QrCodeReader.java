import java.io.FileInputStream;

import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;


// Java code to read the QR code

public class QrCodeReader {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the path of the QR code image to be read: ");
        String qrPath = sc.nextLine();

        String res =  readQR(qrPath, "UTF-8");
        System.out.println("QR Code read successfully");
        System.out.println("The value of the QR code is: " + res);

    }
    // Function to read the QR code
    public static String readQR(String path, String charset)   {

        BinaryBitmap binaryBitmap = null;

        try {
            binaryBitmap = new BinaryBitmap(new HybridBinarizer( new BufferedImageLuminanceSource( ImageIO.read(
                            new FileInputStream(path)))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Result result = null;
        try {
            result = new MultiFormatReader().decode(binaryBitmap);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }

        return result.getText();
    }

}


