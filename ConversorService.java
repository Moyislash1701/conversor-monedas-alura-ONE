import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConversorService {

    private static final String API_KEY = "b85a73dac2115b936564cfd0";

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public static void realizarConversion(int opcion,double cantidad){

       // System.out.println("Petición en desarrollo...");

        String monedaOrigen;
        String monedaDestino;

        switch (opcion){
            case 1:
                monedaOrigen = "USD";
                monedaDestino= "EUR";
                break;
            case 2:
                monedaOrigen = "EUR";
                monedaDestino= "USD";
                break;
            case 3:
                monedaOrigen = "USD";
                monedaDestino= "COP";
                break;
            case 4:
                monedaOrigen = "COP";
                monedaDestino= "USD";
                break;
            case 5:
                monedaOrigen = "USD";
                monedaDestino= "ARS";
                break;
            case 6:
                monedaOrigen = "ARS";
                monedaDestino= "USD";
                break;
            case 7:
                monedaOrigen = "USD";
                monedaDestino= "BRL";
                break;
            case 8:
                monedaOrigen = "BRL";
                monedaDestino= "USD";
                break;
            case 9:
                monedaOrigen = "USD";
                monedaDestino= "CLP";
                break;
            case 10:
                monedaOrigen = "CLP";
                monedaDestino= "USD";
                break;
            default:
                System.out.println("Opcion no valida!");
                return;

        }
        String url = "https://v6.exchangerate-api.com/v6/"+API_KEY+"/latest/"+monedaOrigen;

        URI uri = URI.create(url);
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode()!=200){
                System.out.println("Error: la API devolvio codigo "+response.statusCode());
                return;
            }

            RespuestaAPI datos = gson.fromJson(response.body(),RespuestaAPI.class);

            if (!"success".equals(datos.result())){
                System.out.println("la API tuvo un error: "+ datos.result());
            }

            double tasa = switch (monedaDestino){
                case "USD"-> datos.conversion_rates().USD();
                case "EUR"-> datos.conversion_rates().EUR();
                case "COP"-> datos.conversion_rates().COP();
                case "ARS"-> datos.conversion_rates().ARS();
                case "BRL"-> datos.conversion_rates().BRL();
                case "CLP"-> datos.conversion_rates().CLP();
                default -> 1.0;
            };
            double resultado = cantidad * tasa;

            System.out.println(cantidad +" "+ monedaOrigen+ "  =  "+resultado + " "+monedaDestino);

        }catch (IOException | InterruptedException e){
            System.out.println("Error al conectar con la API");
        }catch (Exception e){
            System.out.println("Algo salió mal .");
        }
    }
}
