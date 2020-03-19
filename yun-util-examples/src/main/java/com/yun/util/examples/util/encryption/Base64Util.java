package com.yun.util.examples.util.encryption;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * 图片与BASE64编码互转工具类
 */
public class Base64Util {

    //
    // code characters for values 0..63
    //
    private static char[] alphabet = "AHIJfghijklKLMNOPQRSTUV2z01783456WXYZabcdemnopqrstBCDEFGuvwxy9+/="
            .toCharArray();
    private static byte[] codes = new byte[256];

    static {
        for (int i = 0; i < 256; i++) {
            codes[i] = -1;
            // LoggerUtil.debug(i + "&" + codes[i] + " ");
        }
        for (int i = 0; i < alphabet.length - 3; i++) {
            codes[alphabet[i]] = (byte) (i);
        }
        codes['+'] = 62;
        codes['/'] = 63;
    }

    public static void main(String[] args) {
        // 图片文件转换为Base64编码
        String base64Str =
                image2base64("/Users/caogw/Downloads/cert/apiclient_cert.p12");
        System.out.println(base64Str);
        // Base64编码 生成图片
        //        GenerateImage(base64Str, "/Users/caogw/Desktop/banner/banner3.jpg");
        // byte数组转换为Base64编码
        //        System.out.println(bytes2base64(new byte[1024]));
    }

    /**
     * 图片文件转换为Base64编码
     * @param imgFilePath 图片路径
     * @return String
     */
    public static String image2base64(String imgFilePath) {
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * Base64编码 生成图片
     * @param imgStr      Base64字符串
     * @param imgFilePath 生成图片保存路径
     * @return boolean
     */
    public static boolean GenerateImage(String imgStr, String imgFilePath) {
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 对Base64字符串进行Base64解码并生成图片
     * @param imgStr 图片字符串
     * @return byte[]
     */
    public static byte[] getStrToBytes(String imgStr) {
        if (imgStr == null) // 图像数据为空
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            return bytes;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 图片byte数组转换为Base64编码
     * @return String
     */
    public static String bytes2base64(byte[] data) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    /**
     * 功能：编码字符串
     * @param data 源字符串
     * @return String
     * @author 宋立君
     * @date 2014年07月03日
     */
    public static String encode(String data) {
        return new String(encode(data.getBytes()));
    }

    /**
     * 功能：解码字符串
     * @param data 源字符串
     * @return String
     * @author 宋立君
     * @date 2014年07月03日
     */
    public static String decode(String data) {
        return new String(decode(data.toCharArray()));
    }

    /**
     * 功能：编码byte[]
     * @param data 源
     * @return char[]
     * @author 宋立君
     * @date 2014年07月03日
     */
    public static char[] encode(byte[] data) {
        char[] out = new char[((data.length + 2) / 3) * 4];
        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
            boolean quad = false;
            boolean trip = false;

            int val = (0xFF & (int) data[i]);
            val <<= 8;
            if ((i + 1) < data.length) {
                val |= (0xFF & (int) data[i + 1]);
                trip = true;
            }
            val <<= 8;
            if ((i + 2) < data.length) {
                val |= (0xFF & (int) data[i + 2]);
                quad = true;
            }
            out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 1] = alphabet[val & 0x3F];
            val >>= 6;
            out[index + 0] = alphabet[val & 0x3F];
        }
        return out;
    }

    /**
     * 功能：解码
     * @param data 编码后的字符数组
     * @return byte[]
     * @author 宋立君
     * @date 2014年07月03日
     */
    public static byte[] decode(char[] data) {

        int tempLen = data.length;
        for (int ix = 0; ix < data.length; ix++) {
            if ((data[ix] > 255) || codes[data[ix]] < 0) {
                --tempLen; // ignore non-valid chars and padding
            }
        }
        // calculate required length:
        // -- 3 bytes for every 4 valid base64 chars
        // -- plus 2 bytes if there are 3 extra base64 chars,
        // or plus 1 byte if there are 2 extra.

        int len = (tempLen / 4) * 3;
        if ((tempLen % 4) == 3) {
            len += 2;
        }
        if ((tempLen % 4) == 2) {
            len += 1;

        }
        byte[] out = new byte[len];

        int shift = 0; // # of excess bits stored in accum
        int accum = 0; // excess bits
        int index = 0;

        // we now go through the entire array (NOT using the 'tempLen' value)
        for (int ix = 0; ix < data.length; ix++) {
            int value = (data[ix] > 255) ? -1 : codes[data[ix]];

            if (value >= 0) { // skip over non-code
                accum <<= 6; // bits shift up by 6 each time thru
                shift += 6; // loop, with new bits being put in
                accum |= value; // at the bottom.
                if (shift >= 8) { // whenever there are 8 or more shifted in,
                    shift -= 8; // write them out (from the top, leaving any
                    out[index++] = // excess at the bottom for next iteration.
                            (byte) ((accum >> shift) & 0xff);
                }
            }
        }

        // if there is STILL something wrong we just have to throw up now!
        if (index != out.length) {
            throw new Error("Miscalculated data length (wrote " + index
                    + " instead of " + out.length + ")");
        }

        return out;
    }

    /**
     * 功能：编码文件
     * @param file 源文件
     * @author 宋立君
     * @date 2014年07月03日
     */
    public static void encode(File file) throws IOException {
        if (!file.exists()) {
            System.exit(0);
        } else {
            byte[] decoded = readBytes(file);
            char[] encoded = encode(decoded);
            writeChars(file, encoded);
        }
        file = null;
    }

    /**
     * 功能：解码文件。
     * @param file 源文件
     * @throws IOException
     * @author 宋立君
     * @date 2014年07月03日
     */
    public static void decode(File file) throws IOException {
        if (!file.exists()) {
            System.exit(0);
        } else {
            char[] encoded = readChars(file);
            byte[] decoded = decode(encoded);
            writeBytes(file, decoded);
        }
        file = null;
    }

    private static byte[] readBytes(File file) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = null;
        InputStream fis = null;
        InputStream is = null;
        try {
            fis = new FileInputStream(file);
            is = new BufferedInputStream(fis);
            int count = 0;
            byte[] buf = new byte[16384];
            while ((count = is.read(buf)) != -1) {
                if (count > 0) {
                    baos.write(buf, 0, count);
                }
            }
            b = baos.toByteArray();

        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (is != null) {
                    is.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return b;
    }

    private static char[] readChars(File file) throws IOException {
        CharArrayWriter caw = new CharArrayWriter();
        Reader fr = null;
        Reader in = null;
        try {
            fr = new FileReader(file);
            in = new BufferedReader(fr);
            int count = 0;
            char[] buf = new char[16384];
            while ((count = in.read(buf)) != -1) {
                if (count > 0) {
                    caw.write(buf, 0, count);
                }
            }

        } finally {
            try {
                if (caw != null) {
                    caw.close();
                }
                if (in != null) {
                    in.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return caw.toCharArray();
    }

    private static void writeBytes(File file, byte[] data) throws IOException {
        OutputStream fos = null;
        OutputStream os = null;
        try {
            fos = new FileOutputStream(file);
            os = new BufferedOutputStream(fos);
            os.write(data);

        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private static void writeChars(File file, char[] data) throws IOException {
        Writer fos = null;
        Writer os = null;
        try {
            fos = new FileWriter(file);
            os = new BufferedWriter(fos);
            os.write(data);

        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}   
