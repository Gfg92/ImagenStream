import java.io.*;

public class TransformaImagen {

    File f = null;

    public TransformaImagen(File fEnt) {
        // Control de existencia del fichero y control de la extensión .bmp (sacar mensajes de error)
        if (fEnt.exists() && fEnt.getName().endsWith(".bmp")) {
            f = fEnt;
        } else {
            System.out.println("No existe o no es formato válido");
        }
    }


    public void transformaNegativo() throws IOException {
        // Transformar a negativo y guardar como *_n.bmp
        File file = new File("resource/" + getNombreSinExtension() + "_n.bmp");
        InputStream input = new FileInputStream(f); // Origen
        OutputStream output = new FileOutputStream(file); // Destino

        // Lee los primeros 54 bytes y los copia al fichero de salida
        byte[] metadata = input.readNBytes(54);
        output.write(metadata);

        // Recorrer byte a byte e invertir
        int b = input.read();
        while (b != -1) {
            int invertido = 255 - b;
            output.write(invertido);
            b = input.read();
        }
        input.close();
        output.close();
    }

    public void transformaOscuro() throws IOException {
        // Transformar a una imagen más oscura y guardar como *_o.bmp
        File file = new File("resource/" + getNombreSinExtension() + "_o.bmp");
        InputStream input = new FileInputStream(f); //Origen
        OutputStream output = new FileOutputStream(file); //Destino

        byte[] primerosBytes = input.readNBytes(54);
        output.write(primerosBytes); // Escribo los primeros 54 bytes

        int b = input.read();
        while (b != -1) {
            int oscuro = b / 2;
            output.write(oscuro);
            b = input.read();
        }
        input.close();
        output.close();
    }


    public void transformaBlancoNegro() throws IOException {
        // Transformar a una imagen en blanco y negro y guardar como *_bn.bmp


    }

    private String getNombreSinExtension() {

        //Devuelve el nombre del archivo f sin extensión
        String[] fields = f.getName().split("\\.");
        String nombre = fields[0];//devuelvo la parte del nombre dejando fuera la extension
        return nombre;

    }

}