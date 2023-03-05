package com.xingyuv.captcha.util;

import java.io.*;
import java.nio.file.Files;

public abstract class FileCopyUtils {
    public static final int BUFFER_SIZE = 4096;

    public FileCopyUtils() {
    }

    public static int copy(File in, File out) throws IOException {
        return copy(Files.newInputStream(in.toPath()), Files.newOutputStream(out.toPath()));
    }

    public static void copy(byte[] in, File out) throws IOException {
        copy((InputStream) (new ByteArrayInputStream(in)), (OutputStream) Files.newOutputStream(out.toPath()));
    }

    public static byte[] copyToByteArray(File in) throws IOException {
        return copyToByteArray(Files.newInputStream(in.toPath()));
    }

    public static int copy(InputStream in, OutputStream out) throws IOException {
        int var2;
        try {
            var2 = StreamUtils.copy(in, out);
        } finally {
            try {
                in.close();
            } catch (IOException ignored) {
            }
            try {
                out.close();
            } catch (IOException ignored) {
            }
        }
        return var2;
    }

    public static void copy(byte[] in, OutputStream out) throws IOException {
        try {
            out.write(in);
        } finally {
            try {
                out.close();
            } catch (IOException ignored) {
            }

        }

    }

    public static byte[] copyToByteArray(InputStream in) throws IOException {
        if (in == null) {
            return new byte[0];
        } else {
            ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
            copy((InputStream) in, (OutputStream) out);
            return out.toByteArray();
        }
    }

    public static int copy(Reader in, Writer out) throws IOException {
        try {
            int byteCount = 0;
            char[] buffer = new char[BUFFER_SIZE];

            int bytesRead;
            for (boolean var4 = true; (bytesRead = in.read(buffer)) != -1; byteCount += bytesRead) {
                out.write(buffer, 0, bytesRead);
            }

            out.flush();
            return byteCount;
        } finally {
            try {
                in.close();
            } catch (IOException ignored) {
            }

            try {
                out.close();
            } catch (IOException ignored) {
            }

        }
    }

    public static void copy(String in, Writer out) throws IOException {
        try {
            out.write(in);
        } finally {
            try {
                out.close();
            } catch (IOException ignored) {
            }

        }

    }

    public static String copyToString(Reader in) throws IOException {
        if (in == null) {
            return "";
        } else {
            StringWriter out = new StringWriter();
            copy((Reader) in, (Writer) out);
            return out.toString();
        }
    }
}

