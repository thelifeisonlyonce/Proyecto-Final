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
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {


    int c = -1;
    int p;

    String s, n ;


    @FXML
    Circle circulo;

    @FXML
    Button empezar;

    @FXML
    Button atras;

    //booleano para elegir estado
    boolean estado = false;

    //iniciar el cambio de color al apretar el boton empezar, no olvidar los  { }
    public void iniciar(ActionEvent actionEvent) {
        //el timer para el bucle
        {
            final Timer timer = new Timer();
            TimerTask task = new TimerTask() {

                @Override
                public void run() {
                    if (c == -1 ){
                        circulo.setFill(Color.GREEN);
                        c=1;
                    }else if (c==1){
                        circulo.setFill(Color.BLUE);
                        c=2;
                    }else if (c==2){
                        circulo.setFill(Color.GRAY);
                        c=3;
                    }else if (c==3){
                        circulo.setFill(Color.RED);
                        c=0;
                    }else if (c==0){
                        circulo.setFill(Color.SKYBLUE);
                        c=4;
                    }else if (c==4){
                        circulo.setFill(Color.BLUEVIOLET);
                        c=5;
                    }else if (c==5){
                        circulo.setFill(Color.DARKCYAN);
                        c=6;
                    }else if (c==6){
                        circulo.setFill(Color.RED);
                        c=-1;
                    }

                }
            };
            timer.schedule(task, 0, 1000); // delay de 0 y se ejecuta cada 2 seg
        }
    }

    //para que al clickear cambie de evento
    public void clickear(MouseEvent mouseEvent) {

        if (c==0 || c==-1) {

            p++;

        } else {

            //para que regrese al menu
            Stage stage = (Stage) atras.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
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

            //para crear ventana que pida el nombre de jugador al perder
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Ha perdido Jugador");
            dialog.setHeaderText("Puntuación: " + p);
            dialog.setContentText("Escribe Tu Nombre:");

        // Traditional way to get the response value. Para Obtener el nombre escrito en el dialogo
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){

                //para crear archivo score txt
                    String archivo = "score.txt";
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                    try {

                        FileWriter fw = new FileWriter(archivo,true);
                        BufferedWriter bw = new BufferedWriter(fw);

                        //convertir el valor int p a string s
                        s = String.valueOf(p);

                        //para que el valor obtenido del dialogo sea la variable string n
                        n= result.get ();

                        bw.write( n + "-" + s);
                        bw.write("\n");

                        bw.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Error leyendo el archivo...");
                    } catch (IOException e) {
                        System.out.println("Error con el archivo...");
                    }

            }


        }



        }


    public void fallar(MouseEvent mouseEvent) {

    }
    //accion boton regresar

    public void regresar(ActionEvent actionEvent) {
        Stage stage = (Stage) atras.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
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



}
