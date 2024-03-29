package edu.aluismarte.diplomado.week4;

import edu.aluismarte.diplomado.model.week4.User;
import edu.aluismarte.diplomado.utils.DBSimulator;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Este código esta mal porque no sigue los principios SOLID y esta muy acoplado al proyecto
 * <p>
 * Que pasa cuando falla?
 * En que archivo escribe?
 * Cual fue el resultado?
 * <p>
 * Para pruebas deberia:
 * probar cambiar la fuente de datos
 * Redirigir a que archivo escribir el resultado
 * La función debe notificar si realizo bien o no la operación
 *
 * @author aluis on 3/27/2022.
 */
public class DifficultToCode {

    public void writeUserName(String id) {
        User user = DBSimulator.get().findUser(id); // Untestable line of code
        try (FileWriter writer = new FileWriter("user.txt")) {
            writer.write(user.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
