package sample;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Menu {

    @FXML
    Button nuevojuego;
    @FXML
    Button topscore;

    
    public void entrar(ActionEvent actionEvent) {


        Stage stage = (Stage) nuevojuego.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Aplicación");
            alerta.setContentText("Llame a Soporte.");
            alerta.showAndWait();
            Platform.exit();
        }
        FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void ver(ActionEvent actionEvent) {


            File archivo = new File( "score.txt");
            File archivo2 = new File("top.txt");
        File archivo3 = new File("bestscore.txt");


            try{

                FileReader fr = new FileReader (archivo);
                BufferedReader br = new BufferedReader (fr);
                String linea;

                ArrayList<Integer> rank = new ArrayList<>();
                ArrayList<String> name = new ArrayList<>();

                while ((linea = br.readLine()) != null){

                    String string  = (linea);
                    String[] parts = string.split("-");
                    String part1 = parts[0]; // 123
                    String part2 = parts[1]; // 654321


                    //agregar part1 a arralist name
                    name.add(part1);

                    // tomar el valor de part2 y convertirlo en a int y usarlo en arraylist rank
                    rank.add(Integer.parseInt(part2));


                }

                Collections.sort(rank);

                BufferedWriter bw2 = new BufferedWriter(new FileWriter(archivo3));
                int para2 = 1;

                for (int r : rank) {
                    if (para2 == 6) {
                        break;
                    }
                    bw2.write(r + "\n");
                    para2++;

                    System.out.println(r  + "\n");
                }

                bw2.close();

                Collections.reverse(rank);

                br.close ();

                BufferedWriter bw = new BufferedWriter(new FileWriter(archivo2));
                int para = 1;
                for (int r : rank) {
                    if (para == 6) {
                        break;
                    }
                    bw.write(r + "\n");
                    para++;

                    System.out.println(r + "\n");
                }



                bw.close();

            }catch (FileNotFoundException e) {
                System.out.println("Error leyendo el archivo...");
            } catch (IOException e) {
                System.out.println("Error con el archivo...");
            }

        Stage stage = (Stage) topscore.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("topscore.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Aplicación");
            alerta.setContentText("Llame a Soporte.");
            alerta.showAndWait();
            Platform.exit();
        }
        FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }




    //para que el boton salir te pregunte si quieres salir y despues cierre el programa
    public void salir(ActionEvent actionEvent) {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Salir");
        alerta.setHeaderText("");
        alerta.setContentText("¿Esta Seguro?");
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.get() == ButtonType.OK) {
            Platform.exit();
    }

    }

}
