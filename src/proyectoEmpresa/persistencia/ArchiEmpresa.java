package proyectoEmpresa.persistencia;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import proyectoEmpresa.modelo.entidad.Empresa;

public class ArchiEmpresa {

    public void guardarEmpresa(Empresa x) throws FileNotFoundException, IOException {
        ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("empresa.obj"));
        salida.writeObject(x);
        salida.close();
    }

    public Empresa cargarEmpresa(String rutaArchivo) throws IOException, ClassNotFoundException {
        ObjectInputStream entrada = new ObjectInputStream(new FileInputStream("empresa.obj"));
        Empresa empresa = (Empresa) entrada.readObject();
        entrada.close();
        return empresa;
    }
}
