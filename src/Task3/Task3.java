package Task3;

import java.io.*;

public class Task3 {
    public static void main(String[] args) throws IOException {
        int number = 42;
        boolean flag = true;
        String str = "UTF8 encoding: кодировка!";
        long l = 0xFFEEDDCC00112233L;
        ObjectClass object1 = new ObjectClass("String", 73L);
        ObjectClass object2 = new ObjectClass("OBJECT@@@@", 101012L);
        System.out.println("Start:");
        System.out.println("\tint:" + number);
        System.out.println("\tboolean:" + flag);
        System.out.println("\tString:" + str);
        System.out.println("\tlong:" + l);
        System.out.println("\tObject1:" + object1);
        System.out.println("\tObject2:" + object2);
        String path = System.getProperty("user.dir") + "/src/Task3/binaryFile.dat" ;
        try( ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream(path))) ) {
            out.writeInt( number );
            out.writeBoolean( flag);
            out.writeUTF( str );
            out.writeLong( l );
            out.writeObject( object1 );
            out.writeObject( object2 );
        }
        try( ObjectInputStream in = new ObjectInputStream(new BufferedInputStream( new FileInputStream(path))) ) {
            int  numRes = in.readInt();
            boolean flagRes = in.readBoolean();
            String strRes = in.readUTF();
            long lRes = in.readLong();
            ObjectClass object1Res;
            ObjectClass object2Res;
            try {
                object1Res = (ObjectClass) in.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                object2Res = (ObjectClass) in.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Result:");
            System.out.println("\tint:" + numRes);
            System.out.println("\tboolean:" + flagRes);
            System.out.println("\tString:" + strRes);
            System.out.println("\tlong:" + lRes);
            System.out.println("\tObject1:" + object1Res);
            System.out.println("\tObject2:" + object2Res);
        }
    }
    static class ObjectClass implements Serializable {
        String StringField;
        Long LongField;

        public ObjectClass(String str, Long longPar) {
            this.StringField = str;
            this.LongField = longPar;
        }

        @Override
        public String toString() {
            return "ObjectClass.StringField=" + StringField + ", ObjectClass.LongField=" + LongField ;
        }
    }
}
