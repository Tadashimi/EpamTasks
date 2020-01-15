package task14_transportation_serialization.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationHelper {

    public static byte[] getByteArrayFromObject(Object object) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(object);
            oos.flush();
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getObjectFromByteArray(byte[] byteArray) {
        if (byteArray != null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
                 ObjectInputStream ois = new ObjectInputStream(bis)) {
                return ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
