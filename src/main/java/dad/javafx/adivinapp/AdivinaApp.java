package dad.javafx.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinaApp extends Application{
	
	private int num_intentos=0;
	private static int num_aleatorio;
	private TextField tx_numero;
	
	public void start (Stage primaryStage) throws Exception{
		//creamos la etiqueta
		Label et_descripcion = new Label();
		et_descripcion.setText("Introduce un numero del 1 al 100");
		et_descripcion.setAlignment(Pos.CENTER);
		et_descripcion.setLayoutX(20);
		et_descripcion.setLayoutY(20);
		
		//creamos el boton
		Button bt_comprobar = new Button();
		bt_comprobar.setText("Comprobar");
		bt_comprobar.setAlignment(Pos.CENTER);
		bt_comprobar.setLayoutX(20);
		bt_comprobar.setLayoutY(80);
		
		//creamos el textfield
		tx_numero = new TextField();
		tx_numero.setAlignment(Pos.CENTER);
		tx_numero.setMaxWidth(100);
		
		//evento lambda
		bt_comprobar.setOnAction( event -> {
				comprobarBoton(event);
		});
		
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(et_descripcion,tx_numero,bt_comprobar);
		
		Scene escena = new Scene(root,320,250);
		primaryStage.setScene(escena);
		primaryStage.setTitle("AdivinApp");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		generarAleatorio();
		launch(args);
	}
		
	private void comprobarBoton (ActionEvent e) {
		num_intentos++;	
		
		try {
			int numero = Integer.parseInt(tx_numero.getText());
			if(numero<num_aleatorio)
				fallo(numero);
			else if(numero>num_aleatorio)
				fallo(numero);
			else {
				acertado();
			}
		}catch (NumberFormatException err) {
			error();
		}
	}
	
	private static void generarAleatorio () {
		num_aleatorio = (int) (Math.random() * (100 - 0 + 1) + 0);	
	}
	
	private void fallo(int numero) {
		String mensaje;
		if(numero<num_aleatorio)
			mensaje="El número a adivinar es mayor que "+numero;
		else
			mensaje="El número a adivinar es menor que "+numero;
		
		Alert alerta_fallo = new Alert(AlertType.WARNING);
		alerta_fallo.setTitle("AdivinApp");
		alerta_fallo.setHeaderText("¡HAS FALLADO!");
		alerta_fallo.setContentText(mensaje+"\nVuelve a intentarlo.");
		alerta_fallo.showAndWait();
	}
	
	private void acertado() {
		Alert alerta_acierto = new Alert(AlertType.INFORMATION); //VENTANA EMERGENTE DE ALERTA DE TIPO INFORMATION (AZUL)
		alerta_acierto.setTitle("AdivinApp");				 //TITULO DE LA VENTANA
		alerta_acierto.setHeaderText("¡HAS GANADO!");			 //CONTENIDO DEL HEADER
		alerta_acierto.setContentText("Solo has necesitado "+num_intentos+" intentos"); //TEXTO DESCRIPTIVO DEL ERROR
		alerta_acierto.showAndWait();
	}
	
	private void error() {
		Alert alerta_error = new Alert(AlertType.ERROR); //VENTANA EMERGENTE DE ALERTA DE TIPO ERROR (ROJA)
		alerta_error.setTitle("AdivinApp");				 //TITULO DE LA VENTANA
		alerta_error.setHeaderText("ERROR");			 //CONTENIDO DEL HEADER
		alerta_error.setContentText("El número introducido no es válido"); //TEXTO DESCRIPTIVO DEL ERROR
		alerta_error.showAndWait();
	}
}
